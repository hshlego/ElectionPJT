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
@DiscriminatorValue("I")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Instagram extends Sns{

    private int likes;
    private int comments;

    @Builder
    public Instagram(Candidate candidate, String content, String url, LocalDateTime uploadDate, int likes, int comments) {
        super(candidate, content, url, uploadDate);
        this.likes = likes;
        this.comments = comments;
    }

    public void change(int likes, int comments) {
        this.likes = likes;
        this.comments = comments;
    }
}
