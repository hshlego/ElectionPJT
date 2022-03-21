package electionPJT.core.service.dto.survey;

import electionPJT.core.domain.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
public class SurveyResponseDto {

    private LocalDateTime surveyDate;
    private String executor;
    private String requester;
    private List<String> names = new ArrayList<>();
    private List<Double> rates = new ArrayList<>();

    public SurveyResponseDto(Survey survey) {
        this.surveyDate = survey.getSurveyDate();
        this.executor = survey.getExecutor();
        this.requester = survey.getRequester();

        for(int i = 0; i < survey.getRatings().size(); i++) {
            names.add(survey.getRatings().get(i).getCandidateName());
            rates.add(survey.getRatings().get(i).getRate());
        }
    }
}
