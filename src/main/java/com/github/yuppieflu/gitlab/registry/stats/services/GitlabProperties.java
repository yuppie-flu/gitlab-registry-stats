package com.github.yuppieflu.gitlab.registry.stats.services;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("gitlab")
@Data
public class GitlabProperties {
    private String host;
    private int port = 443;
    private String scheme = "https";
    private String token;
    private int projectsPerPage = 25;
}