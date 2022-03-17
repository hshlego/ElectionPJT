package electionPJT.core.repository;

import electionPJT.core.domain.sns.Sns;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SnsRepository {

    private final EntityManager em;

    public void remove(Sns sns) {
        em.remove(sns);
    }

    public Sns findById(Long id) {
        return em.find(Sns.class, id);
    }

    public List<Sns> findAllByCandidateId(Long candidateId) {
        return em.createQuery("select s from Sns s" +
                        " where s.candidate.id = :candidateId", Sns.class)
                .setParameter("candidateId", candidateId)
                .getResultList();
    }
}
