package electionPJT.core;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.domain.sns.Facebook;
import electionPJT.core.domain.sns.Instagram;
import electionPJT.core.domain.sns.Twitter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            City city1 = new City(new District("서울", "서울특별시 종로구", "서울 종로"));
            em.persist(city1);

            Candidate candidate1 = new Candidate(1, "종하나", city1);
            Candidate candidate2 = new Candidate(2, "종둘", city1);
            Candidate candidate3 = new Candidate(3, "종셋", city1);
            em.persist(candidate1);
            em.persist(candidate2);
            em.persist(candidate3);

            Facebook facebook = new Facebook(candidate1, "facebook content", "https://facebook.com", LocalDateTime.now(), 1, 1, 1);
            Instagram instagram = new Instagram(candidate1, "instagram content", "https://instagram.com", LocalDateTime.now(), 1, 1);
            Twitter twitter = new Twitter(candidate1, "twitter content", "https://twitter.com", LocalDateTime.now(), 1, 1, 1);
            em.persist(facebook);
            em.persist(instagram);
            em.persist(twitter);


            City city2 = new City(new District("경기", "경기도 성남시수정구", "서울 성남수정"));
            em.persist(city2);

            Candidate candidate4 = new Candidate(1, "성하나", city2);
            Candidate candidate5 = new Candidate(2, "성둘", city2);
            Candidate candidate6 = new Candidate(3, "성셋", city2);
            em.persist(candidate4);
            em.persist(candidate5);
            em.persist(candidate6);
        }

    }
}
