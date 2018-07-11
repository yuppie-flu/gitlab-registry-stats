package com.github.yuppieflu.gitlab.registry.stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Project {
    @JsonProperty("path_with_namespace")
    private String name;
    @JsonProperty("web_url")
    private String url;
}