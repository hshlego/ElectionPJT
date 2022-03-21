package electionPJT.core.service.dto.candidate;

import electionPJT.core.domain.Candidate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidateResponseDto {

    private Long id;
    private int number;
    private String name;

    @Builder
    public CandidateResponseDto(Candidate candidate) {
        this.id = candidate.getId();
        this.number = candidate.getNumber();
        this.name = candidate.getName();
    }
}
