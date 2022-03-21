package electionPJT.core.service.dto.sns.facebook;

import electionPJT.core.domain.sns.Facebook;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class FacebookResponseDto {

    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private int likes;
    private int comments;
    private int shares;

    @Builder
    public FacebookResponseDto(Facebook facebook) {
        this.content = facebook.getContent();
        this.url = facebook.getUrl();
        this.uploadDate = facebook.getUploadDate();
        this.likes = facebook.getLikes();
        this.comments = facebook.getComments();
        this.shares = facebook.getShares();
    }
}
