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

    public Candidate findById(Long id) {
        return em.find(Candidate.class, id);
    }

    public List<Candidate> findByCityIdAndNumber(City city, int number) {
        return em.createQuery("select c from Candidate c where c.city = :city and c.number = :number")
                .setParameter("city", city)
                .setParameter("number", number)
                .getResultList();
    }
}
