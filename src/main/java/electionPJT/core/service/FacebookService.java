package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Facebook;
import electionPJT.core.dto.sns.facebook.FacebookResponseDto;
import electionPJT.core.dto.sns.facebook.FacebookUpdateDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.FacebookRepository;
import electionPJT.core.dto.sns.facebook.FacebookRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FacebookService {

    private final FacebookRepository facebookRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public Long join(FacebookRequestDto facebookRequestDto) {
        Long candidateId = facebookRequestDto.getCandidateId();
        Candidate candidate = candidateRepository.findById(candidateId);

        Facebook facebook = facebookRequestDto.toEntity(candidate);
        facebookRepository.save(facebook);

        return facebook.getId();
    }

    @Transactional
    public void delete(Long facebookId) {
        Facebook facebook = facebookRepository.findById(facebookId);
        facebookRepository.remove(facebook);
    }

    @Transactional
    public void update(Long facebookId, FacebookUpdateDto facebookUpdateDto) {
        Facebook facebook = facebookRepository.findById(facebookId);
        facebook.change(
                facebookUpdateDto.getLikes(),
                facebookUpdateDto.getComments(),
                facebookUpdateDto.getShares()
        );
    }

    public FacebookResponseDto findFacebook(Long facebookId) {
        Facebook facebook = facebookRepository.findById(facebookId);
        return new FacebookResponseDto(facebook);
    }

    public List<FacebookResponseDto> findFacebookList() {
        return facebookRepository.findAll().stream()
                .map(FacebookResponseDto::new)
                .collect(Collectors.toList());
    }
}
