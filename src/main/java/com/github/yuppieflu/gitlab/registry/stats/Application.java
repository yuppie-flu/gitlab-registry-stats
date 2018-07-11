package com.github.yuppieflu.gitlab.registry.stats;

import com.github.yuppieflu.gitlab.registry.stats.services.ProjectsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ProjectsProcessor projectsProcessor;

    @Override
    public void run(String... args) {
        projectsProcessor.process();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
