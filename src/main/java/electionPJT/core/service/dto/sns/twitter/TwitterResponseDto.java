package electionPJT.core.service.dto.sns.twitter;

import electionPJT.core.domain.sns.Twitter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TwitterResponseDto {

    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private int likes;
    private int comments;
    private int retweets;

    @Builder
    public TwitterResponseDto(Twitter twitter) {
        this.content = twitter.getContent();
        this.url = twitter.getUrl();
        this.uploadDate = twitter.getUploadDate();
        this.likes = twitter.getLikes();
        this.comments = twitter.getComments();
        this.retweets = twitter.getRetweets();
    }
}
