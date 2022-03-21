package electionPJT.core.service.dto.city;

import electionPJT.core.domain.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CityResponseDto {

    private Long id;
    private String SSG_2;

    @Builder
    public CityResponseDto(City city) {
        this.id = city.getId();
        this.SSG_2 = city.getDistrict().getSGG_2();
    }

}
