package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import electionPJT.core.service.dto.candidate.CandidateRequestDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.CityRepository;
import electionPJT.core.repository.SnsRepository;
import electionPJT.core.repository.YoutubeRepository;
import electionPJT.core.service.dto.candidate.CandidateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        validateDuplicateCandidate(cityId, candidate.getNumber());
        candidateRepository.save(candidate);
        return candidate.getId();
    }

    @Transactional
    public void delete(Long candidateId) {
        Candidate candidate = candidateRepository.findOne(candidateId);
        candidateRepository.remove(candidate);
    }

    public CandidateResponseDto findCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findOne(candidateId);
        return new CandidateResponseDto(candidate);
    }

    public List<CandidateResponseDto> findCandidateListByCityId(Long cityId) {
        List<Candidate> candidateList = candidateRepository.findAllByCity(cityId);
        return candidateList.stream()
                .map(CandidateResponseDto::new)
                .collect(Collectors.toList());
    }

    public void validateDuplicateCandidate(Long cityId, int candidateNumber) {
        List<Candidate> candidateList = candidateRepository.findByCityAndNumber(cityId, candidateNumber);
        if(!candidateList.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 후보 번호입니다");
        }
    }
}


