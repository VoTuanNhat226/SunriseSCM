package com.vtn.configs;

import com.vtn.service.InitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class WebInitializerConfig {
    private final InitializerService service;

    @PostConstruct
    public void initialize() {
        this.service.createAdminUser();
    }
}
