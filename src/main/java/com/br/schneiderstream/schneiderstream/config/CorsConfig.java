package com.br.schneiderstream.schneiderstream.config;

// import java.util.Arrays;
// import org.springframework.beans.BeansException;
// import org.springframework.beans.factory.config.BeanPostProcessor;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.web.firewall.StrictHttpFirewall;
// import org.springframework.web.servlet.DispatcherServlet;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //             .allowedOrigins("*")
    //             .allowedMethods("GET", "POST", "PUT", "DELETE")
    //             .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization")
    //             .maxAge(3600);
    // }

    // @Bean
    // StrictHttpFirewall httpFirewall() {
    //     StrictHttpFirewall firewall = new StrictHttpFirewall();
    //     firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "OPTIONS", "FOO"));
    //     return firewall;
    // }

    // @Bean
    // public DispatcherServletBeanPostProcessor dispatcherServletBeanPostProcessor() {
    //     return new DispatcherServletBeanPostProcessor();
    // }

    // public static class DispatcherServletBeanPostProcessor implements BeanPostProcessor {
    //     @Override
    //     public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    //         if (bean instanceof DispatcherServlet) {
    //             ((DispatcherServlet) bean).setDispatchOptionsRequest(true);
    //         }
    //         return bean;
    //     }

    //     @Override
    //     public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    //         return bean;
    //     }
    // }
}