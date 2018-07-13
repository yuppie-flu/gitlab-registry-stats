package com.github.yuppieflu.gitlab.registry.stats.jpa.repository;

import com.github.yuppieflu.gitlab.registry.stats.jpa.entities.RegInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RegInfoRepository extends PagingAndSortingRepository<RegInfo, Long> {
    Optional<RegInfo> findByName(String name);
}