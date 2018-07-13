package com.github.yuppieflu.gitlab.registry.stats.web;

import com.github.yuppieflu.gitlab.registry.stats.jpa.repository.RegInfoRepository;
import com.github.yuppieflu.gitlab.registry.stats.web.dto.RegInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RegInfoController {
    private final RegInfoRepository repository;

    @RequestMapping("/regInfos")
    public List<RegInfoDto> regInfos(Pageable pageable) {
        return repository.findAll(pageable).stream().map(RegInfoDto::new).collect(Collectors.toList());
    }
}
