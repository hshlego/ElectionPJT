package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.service.dto.survey.SurveyRequestDto;
import electionPJT.core.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class SurveyServiceTest {

    @Autowired CityRepository cityRepository;
    @Autowired SurveyService surveyService;

    @Test
    public void 설문조사_추가() throws Exception {
        //given
        City city = createCity();

        List<String> names = new ArrayList<>();
        names.add("candidate1"); names.add("candidate2"); names.add("candidate3");
        List<Double> rates = new ArrayList<>();
        rates.add(11.1); rates.add(22.2); rates.add(33.3);

        SurveyRequestDto surveyRequestDto = SurveyRequestDto.builder()
                                                .cityId(city.getId())
                                                .surveyDate(LocalDateTime.now())
                                                .executor("executor")
                                                .requester("requester")
                                                .names(names)
                                                .rates(rates)
                                                .build();

        //when
        Long surveyId = surveyService.join(surveyRequestDto);

        //then
        Assertions.assertEquals(3, surveyService.findSurvey(surveyId).getNames().size());
        Assertions.assertEquals(11.1, surveyService.findSurvey(surveyId).getRates().get(0));

    }

    @Test
    public void 설문조사_삭제() throws Exception {
        //given
        City city = createCity();

        List<String> names = new ArrayList<>();
        names.add("candidate1"); names.add("candidate2"); names.add("candidate3");
        List<Double> rates = new ArrayList<>();
        rates.add(11.1); rates.add(22.2); rates.add(33.3);

        SurveyRequestDto surveyRequestDto = SurveyRequestDto.builder()
                .cityId(city.getId())
                .surveyDate(LocalDateTime.now())
                .executor("executor")
                .requester("requester")
                .names(names)
                .rates(rates)
                .build();

        Long surveyId = surveyService.join(surveyRequestDto);

        //when
        surveyService.delete(surveyId);

        //then
        try {
            surveyService.findSurvey(surveyId);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        Assertions.fail();
    }

    private City createCity() {
        District district = new District("서울", "서울특별시 종로구", "서울 종로");
        City city = new City(district);
        cityRepository.save(city);
        return city;
    }
}