package electionPJT.core.repository;

import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Optional<City> findByDistrict(District district) {
        List<City> cityList = em.createQuery("select c from City c where c.district = :district", City.class)
                .setParameter("district", district)
                .getResultList();
        if(cityList.size() > 1) {
            throw new IllegalStateException("중복되는 도시가 존재합니다.");
        }

        Optional<City> cityOptional = Optional.ofNullable(cityList.get(0));
        return cityOptional;
    }
}
