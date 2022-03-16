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

    public List<Sns> findAll() {
        return em.createQuery("select s from Sns s", Sns.class)
                .getResultList();
    }
}
