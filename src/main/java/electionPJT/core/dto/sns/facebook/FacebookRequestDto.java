package electionPJT.core.dto.sns.facebook;

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
public class FacebookRequestDto {

    private Long candidateId;
    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private int likes;
    private int comments;
    private int shares;

    public Facebook toEntity() {
        return Facebook.builder()
                .content(content)
                .url(url)
                .uploadDate(uploadDate)
                .likes(likes)
                .comments(comments)
                .shares(shares)
                .build();
    }

}
