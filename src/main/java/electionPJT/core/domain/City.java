package electionPJT.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class City {

    @Id @GeneratedValue
    @Column(name = "city_id")
    private Long id;

    @Embedded
    private District district;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Survey> surveys = new ArrayList<>();

    public City(District district) {
        this.district = district;
    }

}
