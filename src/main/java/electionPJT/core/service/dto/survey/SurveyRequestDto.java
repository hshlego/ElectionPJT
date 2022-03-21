package electionPJT.core.service.dto.survey;

import electionPJT.core.domain.City;
import electionPJT.core.domain.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
public class SurveyRequestDto {

    private Long cityId;
    private LocalDateTime surveyDate;
    private String executor;
    private String requester;
    private List<String> names;
    private List<Double> rates;

    public Survey toEntity(City city) {
        return Survey.builder()
                .city(city)
                .surveyDate(surveyDate)
                .executor(executor)
                .requester(requester)
                .build();
    }
}
