package electionPJT.core.dto.sns.facebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@AllArgsConstructor
public class FacebookUpdateDto {

    private int likes;
    private int comments;
    private int shares;

}
