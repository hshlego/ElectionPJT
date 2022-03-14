package electionPJT.core.service;

import electionPJT.core.domain.City;
import electionPJT.core.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    @Transactional
    public Long join(City city) {
        cityRepository.save(city);
        return city.getId();
    }
}
