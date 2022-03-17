package electionPJT.core.repository;

import electionPJT.core.domain.sns.Facebook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacebookRepository {

    private final EntityManager em;

    public void save(Facebook facebook) {
        em.persist(facebook);
    }

    public void remove(Facebook facebook) {
        em.remove(facebook);
    }

    public Facebook findById(Long id) {
        return em.find(Facebook.class, id);
    }

    public List<Facebook> findAllByCandidateId(Long candidateId) {
        return em.createQuery("select f from Facebook f" +
                        " where f.candidate.id = :candidateId", Facebook.class)
                .setParameter("candidateId", candidateId)
                .getResultList();
    }
}
