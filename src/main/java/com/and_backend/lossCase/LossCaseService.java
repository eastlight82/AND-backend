package com.and_backend.lossCase;

import com.and_backend.repository.LossCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LossCaseService {
    private final LossCaseRepository repo;

    public LossCase create(LossCase lc) { return repo.save(lc); }
    public LossCase get(Long id) { return repo.findById(id).orElseThrow(); }
    public List<LossCase> list() { return repo.findAll(); }
    public LossCase update(Long id, LossCase in) {
        LossCase lc = get(id);
        lc.setSubject(in.getSubject());
        lc.setWithTime(in.getWithTime());
        lc.setLossDate(in.getLossDate());
        lc.setLossReason(in.getLossReason());
        lc.setCopeWay(in.getCopeWay());
        lc.setPhoto(in.getPhoto());
        lc.setUsers(in.getUsers());
        return repo.save(lc);
    }
    public void delete(Long id) { repo.deleteById(id); }
}
