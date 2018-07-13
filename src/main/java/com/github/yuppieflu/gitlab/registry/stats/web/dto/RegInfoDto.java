package com.github.yuppieflu.gitlab.registry.stats.web.dto;

import com.github.yuppieflu.gitlab.registry.stats.jpa.entities.RegInfo;
import lombok.Data;
import org.apache.commons.io.FileUtils;

@Data
public class RegInfoDto {

    private final String name;
    private final int imagesNumber;
    private final String totalSize;

    public RegInfoDto(RegInfo regInfo) {
        this.name = regInfo.getName();
        this.imagesNumber = regInfo.getImages();
        this.totalSize = FileUtils.byteCountToDisplaySize(regInfo.getTotalSizeBytes());
    }
}
