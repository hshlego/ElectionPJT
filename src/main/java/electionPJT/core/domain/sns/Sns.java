package electionPJT.core.domain.sns;

import electionPJT.core.domain.Candidate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Sns {

    @Id @GeneratedValue
    @Column(name = "sns_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(columnDefinition = "Text")
    private String content;

    private String url;

    private LocalDateTime uploadDate;

    public Sns(String content, String url, LocalDateTime uploadDate) {
        this.content = content;
        this.url = url;
        this.uploadDate = uploadDate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
