package electionPJT.core.domain.sns;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
@Getter
public class Facebook extends Sns{

    private int likes;
    private int comments;
    private int shares;

}
