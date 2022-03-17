package electionPJT.core.service;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.Youtube;
import electionPJT.core.dto.youtube.YoutubeRequestDto;
import electionPJT.core.dto.youtube.YoutubeResponseDto;
import electionPJT.core.dto.youtube.YoutubeUpdateDto;
import electionPJT.core.repository.CandidateRepository;
import electionPJT.core.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class YoutubeService {

    private final CandidateRepository candidateRepository;
    private final YoutubeRepository youtubeRepository;

    @Transactional
    public Long join(YoutubeRequestDto youtubeRequestDto) {
        Long candidateId = youtubeRequestDto.getCandidateId();
        Candidate candidate = candidateRepository.findById(candidateId);

        Youtube youtube = youtubeRequestDto.toEntity(candidate);
        youtubeRepository.save(youtube);

        return youtube.getId();
    }

    @Transactional
    public void delete(Long youtubeId) {
        Youtube youtube = youtubeRepository.findYoutube(youtubeId);
        youtubeRepository.remove(youtube);
    }

    @Transactional
    public void update(Long youtubeId, YoutubeUpdateDto youtubeUpdateDto) {
        Youtube youtube = youtubeRepository.findYoutube(youtubeId);
        youtube.change(
                youtubeUpdateDto.getRuntime(),
                youtubeUpdateDto.getViews(),
                youtubeUpdateDto.getComments()
        );
    }

    public YoutubeResponseDto findYoutube(Long youtubeId) {
        Youtube youtube = youtubeRepository.findYoutube(youtubeId);
        return new YoutubeResponseDto(youtube);
    }

    public List<YoutubeResponseDto> findYoutubeList(Long candidateId) {
        List<Youtube> youtubeList = youtubeRepository.findYoutubeList(candidateId);
        return youtubeList.stream()
                .map(YoutubeResponseDto::new)
                .collect(Collectors.toList());
    }
}
