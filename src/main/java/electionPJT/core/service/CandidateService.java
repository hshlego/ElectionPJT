package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.domain.District;
import electionPJT.core.exception.DuplicateCandidateException;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CandidateService {

    @Autowired EntityManager em;
    private final CityRepository cityRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public Long addCandidate(String GSS_1, String GSS_2, String GSS_3, int number, String name) {
        District district = new District(GSS_1, GSS_2, GSS_3);
        City city = cityRepository.findByDistrict(district);

        validateDuplicateCandidate(city, number);

        Candidate candidate = new Candidate(number, name, city);
        candidateRepository.save(candidate);

        return candidate.getId();
    }

    public void validateDuplicateCandidate(City city, int number) {
        List<Candidate> candidateList = candidateRepository.findByCityIdAndNumber(city, number);
        if(!candidateList.isEmpty()) {
            throw new DuplicateCandidateException("이미 존재하는 후보 번호입니다");
        }
    }
}
