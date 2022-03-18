package electionPJT.core.repository;

import electionPJT.core.domain.Youtube;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class YoutubeRepository {

    private final EntityManager em;

    public void save(Youtube youtube) {
        em.persist(youtube);
    }

    public void remove(Youtube youtube) {
        em.remove(youtube);
    }

    public void removeAllByCandidateId(Long candidateId) {
        em.createQuery("delete from Youtube y" +
                        " where y.candidate.id = :candidateId")
                .setParameter("candidateId", candidateId);
    }

    public Youtube findOne(Long id) {
        return em.find(Youtube.class, id);
    }

    public List<Youtube> findAllByCandidateId(Long candidateId) {
        return em.createQuery("select y from Youtube y" +
                " where y.candidate.id = :candidateId", Youtube.class)
                .setParameter("candidateId", candidateId)
                .getResultList();
    }
}
