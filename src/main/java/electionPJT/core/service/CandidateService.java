package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.dto.candidate.CandidateRequestDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.CityRepository;
import electionPJT.core.repository.SnsRepository;
import electionPJT.core.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CandidateService {

    private final CityRepository cityRepository;
    private final CandidateRepository candidateRepository;
    private final SnsRepository snsRepository;
    private final YoutubeRepository youtubeRepository;

    @Transactional
    public Long join(CandidateRequestDto candidateRequestDto) {
        Long cityId = candidateRequestDto.getCityId();
        City city = cityRepository.findOne(cityId);

        Candidate candidate = candidateRequestDto.toEntity(city);

        validateDuplicateCandidate(candidate);
        candidateRepository.save(candidate);
        return candidate.getId();
    }

    @Transactional
    public void delete(Long candidateId) {
        Candidate candidate = candidateRepository.findOne(candidateId);
        candidateRepository.remove(candidate);
    }

    public Candidate findCandidate(Long candidateId) {
        return candidateRepository.findOne(candidateId);
    }

    public void validateDuplicateCandidate(Candidate candidate) {
        List<Candidate> candidateList = candidateRepository.findByCityAndNumber(candidate.getCity(), candidate.getNumber());
        if(!candidateList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 후보 번호입니다");
        }
    }
}


