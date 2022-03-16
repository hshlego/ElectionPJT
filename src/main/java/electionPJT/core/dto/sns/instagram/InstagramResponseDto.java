package electionPJT.core.dto.sns.instagram;

import electionPJT.core.domain.sns.Instagram;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class InstagramResponseDto {

    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private int likes;
    private int comments;

    @Builder
    public InstagramResponseDto(Instagram instagram) {
        this.content = instagram.getContent();
        this.url = instagram.getUrl();
        this.uploadDate = instagram.getUploadDate();
        this.likes = instagram.getLikes();
        this.comments = instagram.getComments();
    }
}
