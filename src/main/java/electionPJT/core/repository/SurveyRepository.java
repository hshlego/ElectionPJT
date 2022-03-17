package electionPJT.core.repository;

import electionPJT.core.domain.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SurveyRepository {

    private final EntityManager em;

    public void save(Survey survey) {
        em.persist(survey);
    }

    public void remove(Survey survey) {
        em.remove(survey);
    }

    public Survey findOne(Long id) {
        return em.createQuery("select s from Survey s" +
                        " join fetch s.ratings r" +
                        " where s.id = :id", Survey.class)
                .setParameter("id", id)
                .getResultList()
                .get(0);
    }

    public List<Survey> findAllByCityId(Long cityId) {
        return em.createQuery("select s from Survey s" +
                " join fetch s.ratings r" +
                " where s.city.id = :cityId order by s.surveyDate desc, r.candidateNumber", Survey.class)
                .setParameter("cityId", cityId)
                .getResultList();
    }
}
