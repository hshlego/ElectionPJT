package electionPJT.core.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Youtube {

    @Id @GeneratedValue
    @Column(name = "youtube_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Candidate candidate;

    private String url;

    private String title;

    private String thumbnail;

    private LocalDateTime runtime;

    private String description;

    private int views;

    private int comments;

    private LocalDateTime uploadDate;

    @Builder
    public Youtube(Candidate candidate, String url, String title, String thumbnail, LocalDateTime runtime, String description, int views, int comments, LocalDateTime uploadDate) {
        this.candidate = candidate;
        this.url = url;
        this.title = title;
        this.thumbnail = thumbnail;
        this.runtime = runtime;
        this.description = description;
        this.views = views;
        this.comments = comments;
        this.uploadDate = uploadDate;
    }

    public void change(LocalDateTime runtime, int views, int comments) {
        this.runtime = runtime;
        this.views = views;
        this.comments = comments;
    }
}
