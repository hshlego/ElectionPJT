package electionPJT.core.service.dto.sns.twitter;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Twitter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class TwitterRequestDto {

    private Long candidateId;
    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private int likes;
    private int comments;
    private int retweets;

    public Twitter toEntity(Candidate candidate) {
        return Twitter.builder()
                .candidate(candidate)
                .content(content)
                .url(url)
                .uploadDate(uploadDate)
                .likes(likes)
                .comments(comments)
                .retweets(retweets)
                .build();
    }
}
