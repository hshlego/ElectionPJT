package electionPJT.core.domain.sns;

import electionPJT.core.domain.Candidate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("T")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Twitter extends Sns{

    private int likes;
    private int comments;
    private int retweets;

    @Builder
    public Twitter(Candidate candidate, String content, String url, LocalDateTime uploadDate, int likes, int comments, int retweets) {
        super(candidate, content, url, uploadDate);
        this.likes = likes;
        this.comments = comments;
        this.retweets = retweets;
    }

    public void change(int likes, int comments, int retweets) {
        this.likes = likes;
        this.comments = comments;
        this.retweets = retweets;
    }
}
