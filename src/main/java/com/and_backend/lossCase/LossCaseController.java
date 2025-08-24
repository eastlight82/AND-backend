package com.and_backend.lossCase;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loss-cases") @RequiredArgsConstructor
public class LossCaseController {
    private final LossCaseService service;

    @PostMapping
    public LossCase create(@RequestBody LossCase lc) { return service.create(lc); }

    @GetMapping("/{id}")
    public LossCase get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<LossCase> list() { return service.list(); }

    @PutMapping("/{id}")
    public LossCase update(@PathVariable Long id, @RequestBody LossCase lc) { return service.update(id, lc); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}