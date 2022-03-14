package electionPJT.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class District {

    private String SGG_1;
    private String SGG_2;
    private String SGG_3;

    public District(String SGG_1, String SGG_2, String SGG_3) {
        this.SGG_1 = SGG_1;
        this.SGG_2 = SGG_2;
        this.SGG_3 = SGG_3;
    }
}
