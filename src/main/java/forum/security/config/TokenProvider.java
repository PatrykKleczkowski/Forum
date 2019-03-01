package forum.security.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import static forum.security.model.Constant.*;

public class TokenProvider {

    private TokenProvider() {
    }

    public static String generateToken(String username, String userRole) {
        Key secretKey = generateSecretKey();
        Map<String, Object> authorityClaims = new HashMap<>();
        authorityClaims.put(AUTHORITIES_KEY, userRole);
        String token = Jwts.builder().setClaims(authorityClaims)
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return TOKEN_PREFIX + " " + token;
    }

    public static Key generateSecretKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SIGNING_KEY);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public static void addTokenToResponse(HttpServletResponse res, String token) {
        res.addHeader(HEADER_STRING, token);
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (isTokenValid(token)) {
            String user = getSubjectOfToken(token);
            Claims tokenClaims = getTokenClaims(token);
            Collection<? extends GrantedAuthority> tokenAuthorities = getTokenAuthorities(tokenClaims);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, tokenAuthorities);
            }
        }
        return null;
    }

    private static boolean isTokenValid(String token) {
        return token != null && token.toLowerCase().contains(TOKEN_PREFIX.toLowerCase());
    }

    private static String getSubjectOfToken(String token) {
        return getTokenClaims(token)
                .getSubject();
    }

    private static Claims getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SIGNING_KEY))
                .parseClaimsJws(getTokenWithoutBearerPrefix(token))
                .getBody();
    }

    public static String getTokenWithoutBearerPrefix(String token) {
        return token.replace(TOKEN_PREFIX, "");
    }

    private static Collection<? extends GrantedAuthority> getTokenAuthorities(Claims claims) {
        return Arrays.asList(claims
                .get(AUTHORITIES_KEY)
                .toString()
                .split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}