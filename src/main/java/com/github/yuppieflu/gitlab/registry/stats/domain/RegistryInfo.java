package com.github.yuppieflu.gitlab.registry.stats.domain;

import lombok.Data;

@Data
public class RegistryInfo {
    private final String name;
    private final int numberOfImages;
    private final long totalSizeBytes;
}