package electionPJT.core.service;

import electionPJT.core.domain.sns.Sns;
import electionPJT.core.repository.SnsRepository;
import electionPJT.core.service.dto.sns.SnsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SnsService {

    private final SnsRepository snsRepository;

    public List<SnsResponseDto> findSnsList(Long candidateId) {
        List<Sns> snsList = snsRepository.findAllByCandidateId(candidateId);
        return snsList.stream()
                .map(SnsResponseDto::new)
                .collect(Collectors.toList());
    }
}
