package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.domain.sns.Facebook;
import electionPJT.core.domain.sns.Sns;
import electionPJT.core.dto.candidate.CandidateRequestDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.FacebookRepository;
import electionPJT.core.repository.SnsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CandidateServiceTest {

    @Autowired CandidateRepository candidateRepository;
    @Autowired CandidateService candidateService;
    @Autowired CityService cityService;
    @Autowired SnsRepository snsRepository;
    @Autowired FacebookRepository facebookRepository;

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

    @Test
    @Rollback(value = false)
    public void 영속성전이_삭제검증() throws Exception {
        //given
        City city = createCity();
        Candidate candidate = new Candidate(1, "Sam", city);
        candidateRepository.save(candidate);

        Facebook facebook1 = new Facebook("content2", "url", LocalDateTime.now(), 1, 1, 1);
        Facebook facebook2 = new Facebook("content2", "url", LocalDateTime.now(), 2, 1, 1);
        Facebook facebook3 = new Facebook("content2", "url", LocalDateTime.now(), 3, 1, 1);

        candidate.addSns(facebook1);
        candidate.addSns(facebook2);
        candidate.addSns(facebook3);

        //when
//        candidateService.delete(candidate.getId());

        //then


    }

    private City createCity() {
        District district = new District("서울", "서울특별시 종로구", "서울 종로");
        City city = new City(district);
        cityService.join(city);
        return city;
    }
}