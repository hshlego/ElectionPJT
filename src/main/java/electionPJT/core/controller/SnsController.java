package electionPJT.core.controller;

import electionPJT.core.service.SnsService;
import electionPJT.core.service.dto.sns.SnsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SnsController {

    private final SnsService snsService;

    @GetMapping("/sns/{candidateId}")
    public String list(@PathVariable("candidateId") Long candidateId,
                       Model model) {
        List<SnsResponseDto> snsList = snsService.findSnsList(candidateId);
        model.addAttribute("snsList", snsList);
        return "sns/snsList";
    }
}
