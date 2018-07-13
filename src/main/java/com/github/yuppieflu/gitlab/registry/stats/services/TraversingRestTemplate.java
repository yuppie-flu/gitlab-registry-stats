package com.github.yuppieflu.gitlab.registry.stats.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
public class TraversingRestTemplate<T> {

    private static final String TOTAL_PAGES_HEADER = "X-Total-Pages";

    private final RestTemplate restTemplate = new RestTemplate();

    private final ParameterizedTypeReference<List<T>> typeReference;

    public List<T> getList(IntFunction<String> pageUrlProvider) {
        ResponseEntity<List<T>> response = restTemplate.exchange(
                pageUrlProvider.apply(0), HttpMethod.HEAD, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<T>>() {});

        HttpHeaders headers = response.getHeaders();

        int pages = getIntHeader(TOTAL_PAGES_HEADER, headers);

        return IntStream.rangeClosed(1, pages)
                        .peek(p -> log.info("Processing page {} out of {} ...", p, pages))
                        .mapToObj(pageUrlProvider)
                        .map(this::processPage)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .flatMap(List::stream)
                        .collect(toList());
    }

    private Optional<List<T>> processPage(String pageUrl) {
        ResponseEntity<List<T>> pageResp = restTemplate.exchange(
                pageUrl, HttpMethod.GET, HttpEntity.EMPTY, typeReference);
        return Optional.ofNullable(pageResp.getBody());
    }

    private int getIntHeader(String headerName, HttpHeaders headers) {
        try {
            List<String> headerValues = headers.get(headerName);
            if (headerValues != null && !headerValues.isEmpty()) {
                return Integer.parseInt(headerValues.get(0));
            } else {
                throw new RuntimeException(
                        "The response from a paginated endpoint does not contain header [" + headerName + "]");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Header [" + headerName + "] must be an integer!", e);
        }

    }

}
