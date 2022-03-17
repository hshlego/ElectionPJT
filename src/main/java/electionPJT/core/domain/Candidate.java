package electionPJT.core.domain;

import electionPJT.core.domain.sns.Sns;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Sns> snsList = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Youtube> youtubeList = new ArrayList<>();

    @Builder
    public Candidate(int number, String name, City city) {
        this.number = number;
        this.name = name;
        this.likes = 0;
        this.city = city;
    }

    //== 연관관계 편의 메서드 ==//
    public void addSns(Sns sns) {
        snsList.add(sns);
        sns.setCandidate(this);
    }
}
