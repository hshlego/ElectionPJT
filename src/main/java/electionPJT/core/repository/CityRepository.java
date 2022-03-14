package electionPJT.core.repository;

import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CityRepository {

    private final EntityManager em;

    public void save(City city) {
        em.persist(city);
    }

    public City findOne(Long id) {
        return em.find(City.class, id);
    }

    public List<City> findAll() {
        return em.createQuery("select c from City c", City.class).
                getResultList();
    }

    public City findByDistrict(District district) {
        List<City> cityList = em.createQuery("select c from City c where c.district = :district", City.class)
                .setParameter("district", district)
                .getResultList();
        if(cityList.size() > 1) {
            throw new IllegalStateException("중복되는 도시가 존재합니다.");
        }
        return cityList.get(0);
    }
}
