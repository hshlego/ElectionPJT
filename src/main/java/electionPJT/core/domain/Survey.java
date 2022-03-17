package electionPJT.core.domain;

import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    private LocalDateTime surveyDate;

    private String executor;

    private String requester;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @Builder
    public Survey(City city, LocalDateTime surveyDate, String executor, String requester) {
        this.city = city;
        this.surveyDate = surveyDate;
        this.executor = executor;
        this.requester = requester;
    }

    //== 연관관계 편의 메서드 ==//
    public void addRating(Rating rating) {
        ratings.add(rating);
        rating.setSurvey(this);
    }
}
