package com.github.yuppieflu.gitlab.registry.stats.services;

import com.github.yuppieflu.gitlab.registry.stats.domain.Image;
import com.github.yuppieflu.gitlab.registry.stats.domain.Registry;
import com.github.yuppieflu.gitlab.registry.stats.domain.RegistryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegistryInfoProcessor {

    private final UrlBuilder urlBuilder;
    private final TraversingRestTemplate<Image> restTemplate = new TraversingRestTemplate<>();

    public RegistryInfo registryInfo(Registry registry) {
        List<Image> images = restTemplate.getList(page -> urlBuilder.buildRegistryTagsUri(registry.getTagsPath(), page));

        int totalNumber = images.size();
        long totalSize = images.stream().mapToLong(Image::getSizeBytes).sum();

        return new RegistryInfo(registry.getPath(), totalNumber, totalSize);
    }
}
