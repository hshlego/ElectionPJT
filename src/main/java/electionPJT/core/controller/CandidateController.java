package electionPJT.core.controller;

import electionPJT.core.service.CandidateService;
import electionPJT.core.service.dto.candidate.CandidateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping("/candidates")
    public String list(@RequestParam("cityId") Long cityId,
                       Model model) {
        log.info("candidate listing");
        List<CandidateResponseDto> candidateList = candidateService.findCandidateListByCityId(cityId);
        model.addAttribute("candidates", candidateList);

        return "candidate/candidateList";
    }
}
