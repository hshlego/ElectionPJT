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

    private final CityRepository cityRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public Long join(Candidate candidate) {
        validateDuplicateCandidate(candidate.getCity(), candidate.getNumber());
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
