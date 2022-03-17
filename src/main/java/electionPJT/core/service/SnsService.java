package electionPJT.core.service;

import electionPJT.core.domain.sns.Sns;
import electionPJT.core.repository.SnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SnsService {

    private final SnsRepository snsRepository;

    public List<Sns> findSnsList(Long candidateId) {
        return snsRepository.findAllByCandidateId(candidateId);
    }
}
