package com.github.yuppieflu.gitlab.registry.stats.jpa.entities;

import com.github.yuppieflu.gitlab.registry.stats.domain.RegistryInfo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "project_registry_info",
        indexes =  {
            @Index(name = "unique_name", columnList = "name", unique = true)
        })
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

    public RegInfo setData(RegInfo other) {
        this.images = other.images;
        this.totalSizeBytes = other.totalSizeBytes;
        return this;
    }
}
