package forum.security;

import forum.security.model.Role;
import forum.security.model.User;
import forum.security.model.UserCredentials;
import forum.security.repository.UserRepository;
import forum.security.service.RoleService;
import forum.security.service.UserHelper;
import forum.security.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserHelper userHelper;

    @Mock
    BCryptPasswordEncoder bcryptEncoder;

    @Mock
    RoleService roleService;

    @InjectMocks
    UserService userService;

    User user;
    UserCredentials userCredentials;
    Role role;
    String password;
    User user1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        role = new Role(3L, "ROLE_USER", "User Role");
        user = new User(1L, "name", "password", role);
        userCredentials = new UserCredentials("name", "pd");
        user1 = new User();
    }


    @Test
    public void getAuthority_Test() {
        assertAll(() -> assertNotNull(userService.getAuthority(user)),
                () -> assertEquals(user.getRoles().getName(), "ROLE_USER"));
    }

    @Test
    public void getAuthorityFailUserWithoutRole_Test() {
        assertThrows(NullPointerException.class, () -> {
            userService.getAuthority(user1);
        });
    }

    @Test
    public void loadUser_Test() {
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        assertNotNull(userService.loadUserByUsername("exampleUsername"));
    }

    @Test
    public void loadUserFail_Test() {
        when(userRepository.findByUsername(anyString())).thenReturn(user1);

        assertThrows(NullPointerException.class, () -> {
            userService.loadUserByUsername("exampleUsername");
        });
    }
}
