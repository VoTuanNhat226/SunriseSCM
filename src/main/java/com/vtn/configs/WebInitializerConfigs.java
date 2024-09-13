package com.vtn.configs;

import com.vtn.components.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WebInitializerConfigs {

    @Autowired
    private GlobalService globalService;

    @PostConstruct
    public void initializer() {
        if (!this.globalService.isFirstRun()) {
            return;
        }
       
        this.globalService.saveFirstRun();
    }
}
