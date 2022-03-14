package electionPJT.core.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Rating {

    @Id @GeneratedValue
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private String name;

    private double rate;

}
