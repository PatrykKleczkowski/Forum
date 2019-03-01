package forum.security.model;

public class Constant {
    public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    public static final String SIGNING_KEY = "forumApplicationloreipsumloremipsumloremsipmu";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    private Constant() {
    }
}
