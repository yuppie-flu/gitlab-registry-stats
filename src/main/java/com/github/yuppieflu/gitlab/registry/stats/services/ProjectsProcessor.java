package com.github.yuppieflu.gitlab.registry.stats.services;

import com.github.yuppieflu.gitlab.registry.stats.domain.Project;
import com.github.yuppieflu.gitlab.registry.stats.domain.RegistryInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProjectsProcessor {

    private final TraversingRestTemplate<Project> projectsRestTemplate = new TraversingRestTemplate<>();
    private final UrlBuilder urlBuilder;
    private final RegistryProcessor registryProcessor;
    private final RegistryInfoProcessor registryInfoProcessor;

    private int numberOfProjects = 0;
    private int currentProject = 0;

    public void process() {
        List<Project> allProjects = projectsRestTemplate.getList(urlBuilder::buildProjectsUri);

        numberOfProjects = allProjects.size();

        allProjects.stream()
                   .peek(p -> {
                       currentProject++;
                       log.info("Processing project {}: {} out of {} ...", p.getName(), currentProject, numberOfProjects);
                   })
                   .map(registryProcessor::processProject)
                   .filter(Optional::isPresent)
                   .map(Optional::get)
                   .map(registryInfoProcessor::registryInfo)
                   .sorted(Comparator.comparingLong(RegistryInfo::getTotalSizeBytes).reversed())
                   .forEach(info -> log.info("[{}]", info));
    }
}
