package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.domain.Rating;
import electionPJT.core.domain.Survey;
import electionPJT.core.service.dto.survey.SurveyRequestDto;
import electionPJT.core.service.dto.survey.SurveyResponseDto;
import electionPJT.core.repository.CityRepository;
import electionPJT.core.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyService {

    private final CityRepository cityRepository;
    private final SurveyRepository surveyRepository;

    @Transactional
    public Long join(SurveyRequestDto surveyRequestDto) {
        Long cityId = surveyRequestDto.getCityId();
        City city = cityRepository.findOne(cityId);

        Survey survey = surveyRequestDto.toEntity(city);
        List<String> names = surveyRequestDto.getNames();
        List<Double> rates = surveyRequestDto.getRates();

        for (int i = 0; i < surveyRequestDto.getNames().size(); i++) {
            Rating rating = new Rating(i+1, names.get(i), rates.get(i));
            survey.addRating(rating);
        }

        surveyRepository.save(survey);
        return survey.getId();
    }

    @Transactional
    public void delete(Long surveyId) {
        Survey survey = surveyRepository.findOne(surveyId);
        surveyRepository.remove(survey);
    }

    public SurveyResponseDto findSurvey(Long surveyId) {
        Survey survey = surveyRepository.findOne(surveyId);
        return new SurveyResponseDto(survey);
    }

    public List<SurveyResponseDto> findSurveyList(Long cityId) {
        List<Survey> surveyList = surveyRepository.findAllByCityId(cityId);
        return surveyList.stream()
                .map(SurveyResponseDto::new)
                .collect(Collectors.toList());
    }

}
