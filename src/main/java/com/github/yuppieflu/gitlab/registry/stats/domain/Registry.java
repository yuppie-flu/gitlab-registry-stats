package com.github.yuppieflu.gitlab.registry.stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Registry {
    @JsonProperty("tags_path")
    private String tagsPath;
    private String path;
}