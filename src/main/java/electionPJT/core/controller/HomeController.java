package electionPJT.core.controller;

import electionPJT.core.domain.City;
import electionPJT.core.service.CityService;
import electionPJT.core.service.dto.city.CityResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final CityService cityService;

    @GetMapping("/")
    public String home(Model model) {
        log.info("home controller");
        List<CityResponseDto> cities = cityService.findCityList();
        model.addAttribute("cities", cities);
        return "home";
    }
}
