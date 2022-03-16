package electionPJT.core.dto.sns.instagram;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Facebook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class InstagramUpdateDto {

    private int likes;
    private int comments;

}
