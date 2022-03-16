package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Instagram;
import electionPJT.core.dto.sns.instagram.InstagramRequestDto;
import electionPJT.core.dto.sns.instagram.InstagramResponseDto;
import electionPJT.core.dto.sns.instagram.InstagramUpdateDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.InstagramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InstagramService {

    private final InstagramRepository instagramRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public Long join(InstagramRequestDto instagramRequestDto) {
        Long candidateId = instagramRequestDto.getCandidateId();
        Candidate candidate = candidateRepository.findById(candidateId);

        Instagram instagram = instagramRequestDto.toEntity(candidate);
        instagramRepository.save(instagram);

        return instagram.getId();
    }

    @Transactional
    public void delete(Long instagramId) {
        Instagram instagram = instagramRepository.findById(instagramId);
        instagramRepository.remove(instagram);
    }

    @Transactional
    public void update(Long instagramId, InstagramUpdateDto instagramUpdateDto) {
        Instagram instagram = instagramRepository.findById(instagramId);
        instagram.change(
                instagramUpdateDto.getLikes(),
                instagramUpdateDto.getComments()
        );
    }

    public InstagramResponseDto findInstagram(Long instagramId) {
        Instagram instagram = instagramRepository.findById(instagramId);
        return new InstagramResponseDto(instagram);
    }

    public List<InstagramResponseDto> findInstagramList() {
        return instagramRepository.findAll().stream()
                .map(InstagramResponseDto::new)
                .collect(Collectors.toList());
    }
}
