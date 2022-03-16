package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.repository.CityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        City city = new City(district);

        //when
        Long cityId = cityService.join(city);

        //then
        assertEquals(city, cityRepository.findOne(cityId));
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
        assertEquals(city1, cityRepository.findByDistrict(district).get(0));
    }

    /**
     * 중복 도시
     */
    @Test
    public void 도시_중복_확인() throws Exception {
        //given
        District district = new District("s1", "s2", "s3");
        City city1 = new City(district);
        cityService.join(city1);

        //when
        City city2 = new City(district);
        try{
            cityService.join(city2);
        } catch (IllegalStateException e) {
            return;
        }

        //then
        Assertions.fail();


    }
}