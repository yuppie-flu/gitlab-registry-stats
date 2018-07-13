package com.github.yuppieflu.gitlab.registry.stats.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SimpleListRestTemplate<T> {

    private final RestTemplate restTemplate = new RestTemplate();

    private final ParameterizedTypeReference<List<T>> typeReference;

    public Optional<List<T>> getList(String url) {
        ResponseEntity<List<T>> response = restTemplate.exchange(
                url, HttpMethod.GET, HttpEntity.EMPTY, typeReference);
        return Optional.ofNullable(response.getBody());
    }
}
