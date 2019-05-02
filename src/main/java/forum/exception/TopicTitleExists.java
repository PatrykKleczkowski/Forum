package forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT)
public class TopicTitleExists extends RuntimeException {
    public TopicTitleExists() {
        super("Topic title exists, choose another one");
    }
}
