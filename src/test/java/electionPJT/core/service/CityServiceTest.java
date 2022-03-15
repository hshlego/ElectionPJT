package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CityServiceTest {

    @Autowired CityRepository cityRepository;
    @Autowired CityService cityService;

    /**
     * 도시 추가
     */
    @Test
    public void join() throws Exception {
        //given
        District district = new District("s1", "s2", "s3");
        City city1 = new City(district);

        //when
        Long cityId = cityService.join(city1);

        //then
        assertEquals(city1, cityRepository.findOne(cityId));
    }

    /**
     * 도시 찾기
     */
    @Test
    public void 구역으로_찾기() throws Exception {
        //given
        District district = new District("s1", "s2", "s3");
        City city1 = new City(district);

        //when
        cityService.join(city1);

        //then
        assertEquals(city1, cityRepository.findByDistrict(district).get());
    }
}