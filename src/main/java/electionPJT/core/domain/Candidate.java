package electionPJT.core.domain;

import electionPJT.core.domain.sns.Sns;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Candidate {

    @Id @GeneratedValue
    @Column(name = "candidate_id")
    private Long id;

    private int number;

    @Column(name = "candidate_name")
    private String name;

    @Column(name = "candidate_likes")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Builder
    public Candidate(int number, String name, City city) {
        this.number = number;
        this.name = name;
        this.likes = 0;
        this.city = city;
    }
}
