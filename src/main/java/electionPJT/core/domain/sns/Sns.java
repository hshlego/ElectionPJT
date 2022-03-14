package electionPJT.core.domain.sns;

import electionPJT.core.domain.Candidate;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Sns {

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
}
