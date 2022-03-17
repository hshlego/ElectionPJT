package electionPJT.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rating {

    @Id @GeneratedValue
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private int candidateNumber;

    private String candidateName;

    private double rate;

    //== 생성자 ==//
    public Rating(int candidateNumber, String candidateName, double rate) {
        this.candidateNumber = candidateNumber;
        this.candidateName = candidateName;
        this.rate = rate;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }



}
