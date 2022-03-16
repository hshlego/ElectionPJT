package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.dto.candidate.CandidateRequestDto;
import electionPJT.core.repository.CandidateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CandidateServiceTest {

    @Autowired CandidateRepository candidateRepository;
    @Autowired CandidateService candidateService;
    @Autowired CityService cityService;

    @Test
    public void 후보_추가() throws Exception {
        //given
        City city = createCity();

        //when
        CandidateRequestDto candidateRequestDto1 = new CandidateRequestDto(1, "Jake", city.getId());
        CandidateRequestDto candidateRequestDto2 = new CandidateRequestDto(2, "Sam", city.getId());
        candidateService.join(candidateRequestDto1);
        candidateService.join(candidateRequestDto2);

        //then
        assertEquals(2, candidateRepository.findAllByCity(city).size());
    }

    @Test
    public void 후보_삭제() throws Exception {
        //given
        City city = createCity();

        CandidateRequestDto candidateRequestDto = new CandidateRequestDto(1, "Jake", city.getId());
        Long candidateId = candidateService.join(candidateRequestDto);

        //when
        candidateService.delete(candidateId);

        //then
        assertEquals(null, candidateRepository.findById(candidateId));

    }

    @Test
    public void 후보_중복_검증() throws Exception {
        //given
        City city = createCity();

        //when
        CandidateRequestDto candidateRequestDto = new CandidateRequestDto(1, "Jake", city.getId());
        candidateService.join(candidateRequestDto);

        CandidateRequestDto candidateRequestDto2 = new CandidateRequestDto(1, "Sam", city.getId());

        //then
        try {
            candidateService.join(candidateRequestDto2);
        } catch (IllegalStateException e) {
            return;
        }
        Assertions.fail();
    }

    private City createCity() {
        District district = new District("서울", "서울특별시 종로구", "서울 종로");
        City city = new City(district);
        cityService.join(city);
        return city;
    }
}