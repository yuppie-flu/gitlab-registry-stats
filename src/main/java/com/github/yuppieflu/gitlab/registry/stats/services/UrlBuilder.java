package com.github.yuppieflu.gitlab.registry.stats.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UrlBuilder {

    private static final String API_PROJECTS_PATH = "/api/v4/projects";

    private static final String TOKEN_QUERY_PARAM = "private_token";
    private static final String PAGE_QUERY_PARAM = "page";
    private static final String PER_PAGE_QUERY_PARAM = "per_page";

    private static final String CONTAINER_REGISTRY_PATH_SEGMENT = "container_registry.json";

    private final String host;
    private final int port;
    private final String scheme;
    private final String token;
    private final int projectsPerPage;

    public UrlBuilder(
            @Value("${gitlab.host}") String host,
            @Value("${gitlab.port:443}") int port,
            @Value("${gitlab.scheme:https}") String scheme,
            @Value("${gitlab.token}") String token,
            @Value("${gitlab.projects-per-page:25}") int projectsPerPage
    ) {
        this.host = host;
        this.port = port;
        this.scheme = scheme;
        this.token = token;
        this.projectsPerPage = projectsPerPage;
    }

    public String buildProjectsUri(int page) {
        return UriComponentsBuilder.newInstance()
                                   .scheme(scheme)
                                   .host(host)
                                   .port(port)
                                   .path(API_PROJECTS_PATH)
                                   .queryParam(TOKEN_QUERY_PARAM, token)
                                   .queryParam(PAGE_QUERY_PARAM, page)
                                   .queryParam(PER_PAGE_QUERY_PARAM, projectsPerPage)
                                   .build()
                                   .toUriString();
    }

    public String buildContainerRegistryUri(String projectUrl) {
         return UriComponentsBuilder.fromUriString(projectUrl)
                                   .pathSegment(CONTAINER_REGISTRY_PATH_SEGMENT)
                                   .queryParam(TOKEN_QUERY_PARAM, token)
                                   .build()
                                   .toUriString();
    }

    public String buildRegistryTagsUri(String tagsPathSegment, int page) {
         return UriComponentsBuilder.fromUriString(tagsPathSegment)
                                   .scheme(scheme)
                                   .host(host)
                                   .port(port)
                                   .queryParam(TOKEN_QUERY_PARAM, token)
                                    .queryParam(PAGE_QUERY_PARAM, page)
                                   .build()
                                   .toUriString();
    }
}
