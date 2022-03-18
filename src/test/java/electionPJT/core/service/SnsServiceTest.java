package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.domain.sns.Sns;
import electionPJT.core.dto.sns.facebook.FacebookRequestDto;
import electionPJT.core.dto.sns.facebook.FacebookResponseDto;
import electionPJT.core.dto.sns.facebook.FacebookUpdateDto;
import electionPJT.core.dto.sns.instagram.InstagramRequestDto;
import electionPJT.core.dto.sns.twitter.TwitterRequestDto;
import electionPJT.core.dto.sns.twitter.TwitterResponseDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SnsServiceTest {

    @Autowired CityRepository cityRepository;
    @Autowired CandidateRepository candidateRepository;
    @Autowired SnsService snsService;
    @Autowired FacebookService facebookService;
    @Autowired InstagramService instagramService;
    @Autowired TwitterService twitterService;

    @Test
    public void sns_추가() throws Exception {
        //given
        Candidate candidate = createCandidate();
        FacebookRequestDto facebookRequestDto = new FacebookRequestDto(candidate.getId(), "content1", "url", LocalDateTime.now(), 1, 1, 1);
        InstagramRequestDto instagramRequestDto = new InstagramRequestDto(candidate.getId(), "content1", "url", LocalDateTime.now(), 1, 1);
        TwitterRequestDto twitterRequestDto = new TwitterRequestDto(candidate.getId(), "content1", "url", LocalDateTime.now(), 1, 1, 1);

        //when
        facebookService.join(facebookRequestDto);
        instagramService.join(instagramRequestDto);
        twitterService.join(twitterRequestDto);

        //then
        List<Sns> snsList = snsService.findSnsList(candidate.getId());
        List<TwitterResponseDto> twitterList = twitterService.findTwitterList(candidate.getId());

        assertEquals(3, snsList.size());
        assertEquals(1, twitterList.size());
    }

    @Test
    public void sns_삭제() throws Exception {
        //given
        Candidate candidate = createCandidate();
        FacebookRequestDto facebookRequestDto = new FacebookRequestDto(candidate.getId(), "content1", "url", LocalDateTime.now(), 1, 1, 1);
        Long facebookId = facebookService.join(facebookRequestDto);

        //when
        facebookService.delete(facebookId);

        //then
        List<FacebookResponseDto> facebookList = facebookService.findFacebookList(candidate.getId());
        assertEquals(0, facebookList.size());

    }

    @Test
    public void sns_수정() throws Exception {
        //given
        Candidate candidate = createCandidate();
        FacebookRequestDto facebookRequestDto = new FacebookRequestDto(candidate.getId(), "content1", "url", LocalDateTime.now(), 1, 1, 1);
        Long facebookId = facebookService.join(facebookRequestDto);

        FacebookUpdateDto facebookUpdateDto = FacebookUpdateDto.builder()
            .likes(2)
            .comments(1)
            .shares(1)
            .build();

        //when
        facebookService.update(facebookId, facebookUpdateDto);

        //then
        assertEquals(2, facebookService.findFacebook(facebookId).getLikes());
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
        candidateRepository.save(candidate);
        return candidate;
    }
}
