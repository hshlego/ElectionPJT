package electionPJT.core.domain;

import electionPJT.core.domain.sns.Sns;
import lombok.AccessLevel;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Sns> snsList = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<Youtube> youtubeList = new ArrayList<>();

    public Candidate(int number, String name, City city) {
        this.number = number;
        this.name = name;
        this.city = city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
