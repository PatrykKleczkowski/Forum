package forum.security;


import static forum.security.config.TokenProvider.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.security.Key;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

public class TokenProviderTest {

    final static String userRole = "ROLE_USER";
    final static String username = "username";
    final String TOKEN_PREFIX = "Bearer";
    final String HEADER_STRING = "Authorization";
    String token;

    MockHttpServletResponse httpServletResponse;
    MockHttpServletRequest httpServletRequest;


    @BeforeEach
    void setUp() {
        httpServletResponse = new MockHttpServletResponse();
        httpServletRequest = new MockHttpServletRequest();

        token = generateToken(username, userRole);

        httpServletResponse.addHeader(HEADER_STRING, token);

    }

    @Test
    public void GetAuthentication_Test() {
        String baererToken = httpServletResponse.getHeader(HEADER_STRING);
        httpServletRequest.addHeader(HEADER_STRING,baererToken);
        Authentication resultAuthentication = getAuthentication(httpServletRequest);

        String resultAuthRole = resultAuthentication
                .getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority();

        assertAll(
                () -> assertNotNull(resultAuthentication),
                () ->  assertEquals(userRole,  resultAuthRole)
        );
    }
    @Test
    public void generateToken_Test() throws Exception {
        assertAll(
                () -> assertNotNull(token),
                () -> assertTrue(token.startsWith(TOKEN_PREFIX))
        );
    }

    @Test
    public void addBearerPrefixToAuthenticationHeader_Test() throws Exception {
        String expectedPrefix = TOKEN_PREFIX;
        String resultPrefix = httpServletResponse.getHeader(HEADER_STRING).substring(0, 6);

        assertEquals(expectedPrefix, resultPrefix);
    }
    @Test
    public void getTokenWithoutBearerPrefix_Test(){
        assertEquals(token.substring(6),getTokenWithoutBearerPrefix(token));;
    }
    @Test
    public void addAuthenticationtoResponse_Test() {
        String result = httpServletResponse.getHeader(HEADER_STRING);

        assertNotNull(result);
    }

    @Test
    public void generateSecretKey_Test(){
        Key key = generateSecretKey();

        assertAll(
                () -> assertNotNull(key),
                () -> assertEquals("HmacSHA256",key.getAlgorithm())
        );
    }


    @Nested
    public class GetAuthenticationInvalid {

        @Test
        public void GetAuthenticationWhenTokenIsMissed_Test() {
            Authentication nullAuthentication = getAuthentication(httpServletRequest);

            assertNull(nullAuthentication);
        }

        @Test
        public void getAuthenticationWhenBearerPrefixMissed_Test() throws Exception {
            String bearerTokenWithoutBearerPrefix = TOKEN_PREFIX.substring(6);
            httpServletRequest.addHeader(HEADER_STRING, bearerTokenWithoutBearerPrefix);
            Authentication nullAuthentication = getAuthentication(httpServletRequest);

            assertNull(nullAuthentication);
        }

    }
}