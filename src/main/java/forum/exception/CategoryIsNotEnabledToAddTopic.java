package forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CategoryIsNotEnabledToAddTopic extends RuntimeException {
    public CategoryIsNotEnabledToAddTopic() {
        super("Category is not enabled to add topic by user");
    }
}