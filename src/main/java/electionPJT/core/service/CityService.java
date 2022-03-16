package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional
    public Long join(City city) {
        validateDuplicateCity(city);
        cityRepository.save(city);
        return city.getId();
    }

    public void validateDuplicateCity(City city) {
        List<City> cityList = cityRepository.findByDistrict(city.getDistrict());
        if(!cityList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 도시입니다.");
        }
    }
}
