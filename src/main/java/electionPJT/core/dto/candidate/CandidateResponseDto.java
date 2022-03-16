package electionPJT.core.dto.candidate;

import electionPJT.core.domain.Candidate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidateResponseDto {

    private int number;
    private String name;

    @Builder
    public CandidateResponseDto(Candidate candidate) {
        this.number = candidate.getNumber();
        this.name = candidate.getName();
    }
}
