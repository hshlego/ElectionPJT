package electionPJT.core.repository;

import electionPJT.core.domain.sns.Instagram;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstagramRepository {

    private final EntityManager em;

    public void save(Instagram instagram) {
        em.persist(instagram);
    }

    public void remove(Instagram instagram) {
        em.remove(instagram);
    }

    public Instagram findById(Long id) {
        return em.find(Instagram.class, id);
    }

    public List<Instagram> findAllByCandidateId(Long candidateId) {
        return em.createQuery("select i from Instagram i" +
                        " where i.candidate.id = :candidateId", Instagram.class)
                .setParameter("candidateId", candidateId)
                .getResultList();
    }
}
