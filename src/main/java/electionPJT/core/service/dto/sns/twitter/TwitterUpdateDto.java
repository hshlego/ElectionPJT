package electionPJT.core.service.dto.sns.twitter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class TwitterUpdateDto {

    private int likes;
    private int comments;
    private int retweets;

}
