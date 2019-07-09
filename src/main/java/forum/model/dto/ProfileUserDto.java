package forum.model.dto;

import forum.model.Rank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileUserDto {
    private String username;
    private Rank rank;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date registered;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date lastLogin;
    private int points;
}
