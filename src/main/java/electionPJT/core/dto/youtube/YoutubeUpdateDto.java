package electionPJT.core.dto.youtube;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class YoutubeUpdateDto {

    private LocalDateTime runtime;
    private int views;
    private int comments;

}
