package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.exception.DuplicateCandidateException;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.CityRepository;
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
        District district = new District("1", "1", "1");
        City city1 = new City(district);
        cityService.join(city1);

        //when
        Candidate candidate1 = new Candidate(city1, 1, "Jake");
        Long candidateId = candidateService.join(candidate1);

        //then
        assertEquals(candidate1, candidateRepository.findById(candidateId));
    }

    @Test
    public void 후보_중복_검증() throws Exception {
        //given
        District district = new District("1", "1", "1");
        City city1 = new City(district);
        cityService.join(city1);

        //when
        Candidate candidate1 = new Candidate(city1, 1, "Jake");
        candidateService.join(candidate1);

        Candidate candidate2 = new Candidate(city1, 1, "Sam");

        //then
        try {
            candidateService.join(candidate2);
        } catch (DuplicateCandidateException e) {
            return;
        }
        Assertions.fail();
    }
}