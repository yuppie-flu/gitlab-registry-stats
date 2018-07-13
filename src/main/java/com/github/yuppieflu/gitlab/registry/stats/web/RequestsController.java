package com.github.yuppieflu.gitlab.registry.stats.web;

import com.github.yuppieflu.gitlab.registry.stats.services.ProjectsProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequiredArgsConstructor
public class RequestsController {

    private final ProjectsProcessor projectsProcessor;

    @RequestMapping("/index")
    public void index() {
        ForkJoinPool.commonPool().execute(projectsProcessor::process);
    }
}
