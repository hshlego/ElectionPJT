package electionPJT.core.service.dto.youtube;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.Youtube;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class YoutubeRequestDto {

    private Long candidateId;
    private String url;
    private String title;
    private String thumbnail;
    private LocalDateTime runtime;
    private String description;
    private int views;
    private int comments;
    private LocalDateTime uploadDate;

    public Youtube toEntity(Candidate candidate) {
        return Youtube.builder()
                .candidate(candidate)
                .url(url)
                .title(title)
                .thumbnail(thumbnail)
                .runtime(runtime)
                .description(description)
                .views(views)
                .comments(comments)
                .uploadDate(uploadDate)
                .build();
    }
}
