package forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TopicIsNotEnabledToAddPost extends RuntimeException {
    public TopicIsNotEnabledToAddPost(){
        super("Topic is not enabled to add post by user");
    }
}
