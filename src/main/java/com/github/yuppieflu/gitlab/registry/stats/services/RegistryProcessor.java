package com.github.yuppieflu.gitlab.registry.stats.services;

import com.github.yuppieflu.gitlab.registry.stats.domain.Project;
import com.github.yuppieflu.gitlab.registry.stats.domain.Registry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistryProcessor {

    private final SimpleListRestTemplate<Registry> registryRestTemplate = new SimpleListRestTemplate<>();

    private final UrlBuilder urlBuilder;

    public Optional<Registry> processProject(Project project) {
        try {
            String url = urlBuilder.buildContainerRegistryUri(project.getUrl());
            return registryRestTemplate.getList(url)
                                       .filter(l -> !l.isEmpty())
                                       .map(l -> l.get(0));
        } catch (HttpClientErrorException e) {
            log.warn("Can't get container registry info for {}. Exception: {}", project, e.getMessage());
            return Optional.empty();
        }
    }
}
