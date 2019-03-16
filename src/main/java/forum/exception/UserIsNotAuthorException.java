package forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserIsNotAuthorException extends RuntimeException {
public UserIsNotAuthorException(){
    super("User is not author");
}
}
