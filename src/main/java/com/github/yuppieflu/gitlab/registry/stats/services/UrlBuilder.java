package com.github.yuppieflu.gitlab.registry.stats.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class UrlBuilder {

    private static final String API_PROJECTS_PATH = "/api/v4/projects";

    private static final String TOKEN_QUERY_PARAM = "private_token";
    private static final String PAGE_QUERY_PARAM = "page";
    private static final String PER_PAGE_QUERY_PARAM = "per_page";

    private static final String CONTAINER_REGISTRY_PATH_SEGMENT = "container_registry.json";

    private final GitlabProperties gitlabProperties;

    public String buildProjectsUri(int page) {
        return UriComponentsBuilder.newInstance()
                                   .scheme(gitlabProperties.getScheme())
                                   .host(gitlabProperties.getHost())
                                   .port(gitlabProperties.getPort())
                                   .path(API_PROJECTS_PATH)
                                   .queryParam(TOKEN_QUERY_PARAM, gitlabProperties.getToken())
                                   .queryParam(PAGE_QUERY_PARAM, page)
                                   .queryParam(PER_PAGE_QUERY_PARAM, gitlabProperties.getProjectsPerPage())
                                   .build()
                                   .toUriString();
    }

    public String buildContainerRegistryUri(String projectUrl) {
         return UriComponentsBuilder.fromUriString(projectUrl)
                                   .pathSegment(CONTAINER_REGISTRY_PATH_SEGMENT)
                                   .queryParam(TOKEN_QUERY_PARAM, gitlabProperties.getToken())
                                   .build()
                                   .toUriString();
    }

    public String buildRegistryTagsUri(String tagsPathSegment, int page) {
         return UriComponentsBuilder.fromUriString(tagsPathSegment)
                                    .scheme(gitlabProperties.getScheme())
                                    .host(gitlabProperties.getHost())
                                    .port(gitlabProperties.getPort())
                                    .queryParam(TOKEN_QUERY_PARAM, gitlabProperties.getToken())
                                    .queryParam(PAGE_QUERY_PARAM, page)
                                    .build()
                                    .toUriString();
    }
}
