package electionPJT.core.repository;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CandidateRepository {

    private final EntityManager em;

    public void save(Candidate candidate) {
        em.persist(candidate);
    }

    public void remove(Candidate candidate) {
        em.remove(candidate);
    }

    public Candidate findOne(Long id) {
        return em.find(Candidate.class, id);
    }

    public List<Candidate> findAllByCity(Long cityId) {
        return em.createQuery("select c from Candidate c where c.city.id = :cityId", Candidate.class)
                .setParameter("cityId", cityId)
                .getResultList();
    }

    public List<Candidate> findByCityAndNumber(Long cityId, int number) {
        return em.createQuery("select c from Candidate c where c.city.id = :cityId and c.number = :number", Candidate.class)
                .setParameter("cityId", cityId)
                .setParameter("number", number)
                .getResultList();
    }
}
