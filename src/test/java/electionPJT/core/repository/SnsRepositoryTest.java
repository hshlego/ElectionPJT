package electionPJT.core.repository;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.domain.sns.Facebook;
import electionPJT.core.domain.sns.Sns;
import electionPJT.core.dto.sns.facebook.FacebookRequestDto;
import electionPJT.core.service.FacebookService;
import electionPJT.core.service.InstagramService;
import electionPJT.core.service.SnsService;
import electionPJT.core.service.TwitterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class SnsRepositoryTest {

    @Autowired EntityManager em;
    @Autowired CityRepository cityRepository;
    @Autowired CandidateRepository candidateRepository;
    @Autowired SnsService snsService;
    @Autowired SnsRepository snsRepository;
    @Autowired FacebookRepository facebookRepository;
    @Autowired FacebookService facebookService;
    @Autowired InstagramService instagramService;
    @Autowired TwitterService twitterService;

    @Test
    public void 영속성전이_persist() throws Exception {
        //given
        Candidate candidate = createCandidate();
        Facebook facebook = new Facebook("content1", "url", LocalDateTime.now(), 1, 1, 1);
        candidate.addSns(facebook);

        //when
        candidateRepository.save(candidate);
        Sns sns = (Sns) facebook;

        //then
        Assertions.assertEquals(facebook, em.find(Facebook.class, facebook.getId()));
        Assertions.assertEquals(sns, em.find(Sns.class, sns.getId()));
    }

    @Test
    public void 영속성전이_remove() throws Exception {
        //given
        Candidate candidate = createCandidate();
        Facebook facebook = new Facebook("content1", "url", LocalDateTime.now(), 1, 1, 1);
        candidate.addSns(facebook);
        candidateRepository.save(candidate);

        //when
        em.remove(facebook);
        em.flush();

        //then
        Assertions.assertFalse(em.contains(facebook));
    }

    @Test
    public void sns_삭제() throws Exception {
        //given
        Candidate candidate = createCandidate();
        Facebook facebook = new FacebookRequestDto(candidate.getId(), "content1", "url", LocalDateTime.now(), 1, 1, 1).toEntity();
        candidate.addSns(facebook);
        candidateRepository.save(candidate);


        em.flush();
        em.clear();

        //when
        Long snsId = facebook.getId();
        Sns sns = snsRepository.findById(snsId);
        snsRepository.remove(sns);

        //then
        Assertions.assertEquals(0, snsRepository.findAllByCandidateId(candidate.getId()).size());

    }

    private City createCity() {
        District district = new District("서울", "서울특별시 종로구", "서울 종로");
        City city = new City(district);
        cityRepository.save(city);
        return city;
    }

    private Candidate createCandidate() {
        City city = createCity();
        Candidate candidate = new Candidate(1, "Jake", city);
        return candidate;
    }
}
