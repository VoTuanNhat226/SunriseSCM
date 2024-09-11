package com.vtn.configs;

import com.vtn.formatters.UnitFormatter;
import com.vtn.formatters.DateConverter;
import com.vtn.formatters.TaxFormatter;
import com.vtn.formatters.ShipperFormatter;
import com.vtn.formatters.UserFormatter;
import com.vtn.formatters.CategoryFormatter;
import com.vtn.formatters.WarehouseFormatter;
import com.vtn.formatters.SupplierFormatter;
import com.vtn.formatters.TagFormatter;
import com.vtn.formatters.ScheduleFormatter;
import com.vtn.formatters.OrderFormatter;
import com.vtn.formatters.LocalDateConveter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Order(1)
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.vtn.components",
        "com.vtn.controllers",
        "com.vtn.enums",
        "com.vtn.repository",
        "com.vtn.services",
})
public class WebApplicationContextConfigs implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(@NotNull DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        commonsMultipartResolver.setMaxUploadSize(20971520);
        commonsMultipartResolver.setMaxInMemorySize(1048576);

        return commonsMultipartResolver;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        return filter;
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("messages");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");

        return resourceBundleMessageSource;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(this.messageSource());

        return localValidatorFactoryBean;
    }

    @Override
    public Validator getValidator() {
        return this.validator();
    }

    @Override
    public void addFormatters(@NotNull FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
        registry.addConverter(new LocalDateConveter());
        registry.addFormatter(new CategoryFormatter());
        registry.addFormatter(new OrderFormatter());
        registry.addFormatter(new ScheduleFormatter());
        registry.addFormatter(new ShipperFormatter());
        registry.addFormatter(new SupplierFormatter());
        registry.addFormatter(new TagFormatter());
        registry.addFormatter(new TaxFormatter());
        registry.addFormatter(new UnitFormatter());
        registry.addFormatter(new UserFormatter());
        registry.addFormatter(new WarehouseFormatter());
    }

    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
    }
}
