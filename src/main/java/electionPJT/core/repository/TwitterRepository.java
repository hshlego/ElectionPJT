package electionPJT.core.repository;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Instagram;
import electionPJT.core.domain.sns.Twitter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TwitterRepository {

    private final EntityManager em;

    public void save(Twitter twitter) {
        em.persist(twitter);
    }

    public void remove(Twitter twitter) {
        em.remove(twitter);
    }

    public Twitter findById(Long id) {
        return em.find(Twitter.class, id);
    }

    public List<Twitter> findAllByCandidateId(Long candidateId) {
        return em.createQuery("select t from Twitter t" +
                        " where t.candidate.id = :candidateId", Twitter.class)
                .setParameter("candidateId", candidateId)
                .getResultList();
    }
}
