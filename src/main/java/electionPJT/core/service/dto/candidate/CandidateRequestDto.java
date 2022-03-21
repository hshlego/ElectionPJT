package electionPJT.core.service.dto.candidate;

import electionPJT.core.domain.Candidate;
import electionPJT.core.domain.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CandidateRequestDto {

    private int number;
    private String name;
    private Long cityId;

    @Builder
    public CandidateRequestDto(int number, String name, Long cityId) {
        this.number = number;
        this.name = name;
        this.cityId = cityId;
    }

    public Candidate toEntity(City city) {
        return Candidate.builder()
                .number(number)
                .name(name)
                .city(city)
                .build();
    }
}
