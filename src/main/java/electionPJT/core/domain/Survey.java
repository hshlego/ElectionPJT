package electionPJT.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {

    @Id @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private LocalDateTime surveyDate;

    private String executor;

    private String requester;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    public Survey(LocalDateTime surveyDate, String executor, String requester) {
        this.surveyDate = surveyDate;
        this.executor = executor;
        this.requester = requester;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
