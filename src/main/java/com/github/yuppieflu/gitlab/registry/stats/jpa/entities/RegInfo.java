package com.github.yuppieflu.gitlab.registry.stats.jpa.entities;

import com.github.yuppieflu.gitlab.registry.stats.domain.RegistryInfo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_registry_info")
@Data
public class RegInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int images;
    private long totalSizeBytes;

    protected RegInfo() {}

    public RegInfo(RegistryInfo registryInfo) {
        this.name = registryInfo.getName();
        this.images = registryInfo.getNumberOfImages();
        this.totalSizeBytes = registryInfo.getTotalSizeBytes();
    }
}
