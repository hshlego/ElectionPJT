package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.domain.Youtube;
import electionPJT.core.domain.sns.Facebook;
import electionPJT.core.domain.sns.Sns;
import electionPJT.core.dto.candidate.CandidateRequestDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.FacebookRepository;
import electionPJT.core.repository.SnsRepository;
import electionPJT.core.repository.YoutubeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CandidateServiceTest {

    @Autowired EntityManager em;
    @Autowired CandidateRepository candidateRepository;
    @Autowired CandidateService candidateService;
    @Autowired CityService cityService;
    @Autowired SnsRepository snsRepository;
    @Autowired FacebookRepository facebookRepository;
    @Autowired YoutubeRepository youtubeRepository;

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
    public void 후보_삭제() throws Exception {
        //given
        City city = createCity();

        CandidateRequestDto candidateRequestDto = new CandidateRequestDto(1, "Jake", city.getId());
        Long candidateId = candidateService.join(candidateRequestDto);

        //when
        candidateService.delete(candidateId);

        //then
        assertEquals(null, candidateRepository.findOne(candidateId));

    }

    @Test
    public void 후보_삭제_ON_DELETE_CASCADE() throws Exception {
        //given
        City city = createCity();
        Candidate candidate = new Candidate(1, "Jake", city);
        candidateRepository.save(candidate);
        Facebook facebook = new Facebook(candidate, "content1", "url", LocalDateTime.now(), 1, 1, 1);
        facebookRepository.save(facebook);
        Youtube youtube = new Youtube(candidate, "url", "title", "thumbnail", LocalDateTime.now(), "description", 1, 1, LocalDateTime.now());
        youtubeRepository.save(youtube);

        //when
        Long candidateId = candidate.getId();
        candidateService.delete(candidateId);

        //then
        assertEquals(0, snsRepository.findAllByCandidateId(candidateId).size());
        assertEquals(0, youtubeRepository.findAllByCandidateId(candidateId).size());

    }


    private City createCity() {
        District district = new District("서울", "서울특별시 종로구", "서울 종로");
        City city = new City(district);
        cityService.join(city);
        return city;
    }
}