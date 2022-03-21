package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Twitter;
import electionPJT.core.service.dto.sns.twitter.TwitterRequestDto;
import electionPJT.core.service.dto.sns.twitter.TwitterResponseDto;
import electionPJT.core.service.dto.sns.twitter.TwitterUpdateDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.TwitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TwitterService {

    private final TwitterRepository twitterRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public Long join(TwitterRequestDto twitterRequestDto) {
        Long candidateId = twitterRequestDto.getCandidateId();
        Candidate candidate = candidateRepository.findOne(candidateId);

        Twitter twitter = twitterRequestDto.toEntity(candidate);
        twitterRepository.save(twitter);

        return twitter.getId();
    }

    @Transactional
    public void delete(Long twitterId) {
        Twitter twitter = twitterRepository.findById(twitterId);
        twitterRepository.remove(twitter);
    }

    @Transactional
    public void update(Long twitterId, TwitterUpdateDto twitterUpdateDto) {
        Twitter twitter = twitterRepository.findById(twitterId);
        twitter.change(
                twitterUpdateDto.getLikes(),
                twitterUpdateDto.getComments(),
                twitterUpdateDto.getRetweets()
        );
    }

    public TwitterResponseDto findTwitter(Long twitterId) {
        Twitter twitter = twitterRepository.findById(twitterId);
        return new TwitterResponseDto(twitter);
    }

    public List<TwitterResponseDto> findTwitterList(Long candidateId) {
        return twitterRepository.findAllByCandidateId(candidateId).stream()
                .map(TwitterResponseDto::new)
                .collect(Collectors.toList());
    }
}
