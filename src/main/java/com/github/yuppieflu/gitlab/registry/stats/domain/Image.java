package com.github.yuppieflu.gitlab.registry.stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Image {
    @JsonProperty("total_size")
    private long sizeBytes;
}