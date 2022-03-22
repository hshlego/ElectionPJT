package electionPJT.core.controller;

import electionPJT.core.service.CandidateService;
import electionPJT.core.service.dto.candidate.CandidateRequestDto;
import electionPJT.core.service.dto.candidate.CandidateResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("cityId", cityId);

        return "candidate/candidateList";
    }

    @GetMapping("/candidates/register/{cityId}")
    public String candidateForm(@PathVariable("cityId") Long cityId, Model model) {
        model.addAttribute("cityId", cityId);
        return "candidate/candidateForm";
    }

    @PostMapping("candidates/register/{cityId}")
    public String create(@PathVariable("cityId") Long cityId,
                         @RequestParam("number") int number,
                         @RequestParam("name") String name) {

        CandidateRequestDto candidateRequestDto = new CandidateRequestDto(number, name, cityId);
        try {
            candidateService.join(candidateRequestDto);
        } catch (IllegalStateException e) {
            log.info("이미 존재하는 후보 번호로 등록 요청");
        }
            return "redirect:/";
    }

    @PostMapping("candidates/remove/{candidateId}")
    public String remove(@PathVariable("candidateId") Long candidateId) {
        candidateService.delete(candidateId);

        return "redirect:/";
    }

}
