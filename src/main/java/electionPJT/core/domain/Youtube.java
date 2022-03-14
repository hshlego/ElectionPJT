package electionPJT.core.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Youtube {

    @Id @GeneratedValue
    @Column(name = "youtube_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private String url;

    private String thumbnail;

    private LocalDateTime runtime;

    private String description;

    private int views;

    private int comments;

    private LocalDateTime uploadDate;
}
