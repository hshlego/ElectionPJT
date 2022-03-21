package electionPJT.core.controller;

import electionPJT.core.service.SnsService;
import electionPJT.core.service.YoutubeService;
import electionPJT.core.service.dto.sns.SnsResponseDto;
import electionPJT.core.service.dto.youtube.YoutubeResponseDto;
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
public class YoutubeController {

    private final YoutubeService youtubeService;

    @GetMapping("/youtube/{candidateId}")
    public String list(@PathVariable("candidateId") Long candidateId,
                       Model model) {
        List<YoutubeResponseDto> youtubeList = youtubeService.findYoutubeList(candidateId);
        model.addAttribute("youtubeList", youtubeList);
        return "youtube/youtubeList";
    }
}
