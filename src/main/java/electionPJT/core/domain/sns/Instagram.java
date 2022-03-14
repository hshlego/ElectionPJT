package electionPJT.core.domain.sns;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
@Getter
public class Instagram extends Sns{

    private int likes;
    private int comments;

}
