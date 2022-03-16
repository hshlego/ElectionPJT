package electionPJT.core.dto.sns.instagram;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.sns.Instagram;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@AllArgsConstructor
public class InstagramRequestDto {

    private Long candidateId;
    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private int likes;
    private int comments;

    public Instagram toEntity(Candidate candidate) {
        return Instagram.builder()
                .candidate(candidate)
                .content(content)
                .url(url)
                .uploadDate(uploadDate)
                .likes(likes)
                .comments(comments)
                .build();
    }


}
