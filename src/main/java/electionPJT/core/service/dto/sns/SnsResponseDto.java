package electionPJT.core.service.dto.sns;

import electionPJT.core.domain.sns.Sns;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import java.time.LocalDateTime;

@Getter @Setter
public class SnsResponseDto {

    private String content;
    private String url;
    private LocalDateTime uploadDate;
    private String discriminatorValue;

    @Builder
    public SnsResponseDto(Sns sns) {
        this.content = sns.getContent();
        this.url = sns.getUrl();
        this.uploadDate = sns.getUploadDate();
        this.discriminatorValue = sns.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}
