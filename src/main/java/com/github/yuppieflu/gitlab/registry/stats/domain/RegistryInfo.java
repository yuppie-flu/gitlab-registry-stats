package com.github.yuppieflu.gitlab.registry.stats.domain;

import lombok.Data;
import org.apache.commons.io.FileUtils;

@Data
public class RegistryInfo {
    private final String name;
    private final int numberOfImages;
    private final long totalSizeBytes;
    private final String sizeHumanReadable;

    public RegistryInfo(String name, int numberOfImages, long totalSizeBytes) {
        this.name = name;
        this.numberOfImages = numberOfImages;
        this.totalSizeBytes = totalSizeBytes;
        this.sizeHumanReadable = FileUtils.byteCountToDisplaySize(totalSizeBytes);
    }
}