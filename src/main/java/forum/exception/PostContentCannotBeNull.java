package forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT)
public class PostContentCannotBeNull extends RuntimeException {
    public PostContentCannotBeNull() {
        super("Post content cannot be null");
    }
}

