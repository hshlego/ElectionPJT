package electionPJT.core.service.dto.youtube;

import electionPJT.core.domain.Youtube;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class YoutubeResponseDto {

    private String url;
    private String title;
    private String thumbnail;
    private LocalDateTime runtime;
    private String description;
    private int views;
    private int comments;
    private LocalDateTime uploadDate;

    public YoutubeResponseDto(Youtube youtube) {
        this.url = youtube.getUrl();
        this.title = youtube.getTitle();
        this.thumbnail = youtube.getThumbnail();
        this.runtime = youtube.getRuntime();
        this.description = youtube.getDescription();
        this.views = youtube.getViews();
        this.comments = youtube.getComments();
        this.uploadDate = youtube.getUploadDate();
    }
}
