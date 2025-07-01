package com.danilomr.todosimples.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfing implements WebMvcConfigurer {

    public  void addCorsMapping(CorsRegistry registry) {
        registry.addMapping("/**"); //esse codigo serve para libera qualquer mepiamento, get,pos, put e del
    }                                         //mas se eu quiser codificar qualquer segurança é so coloca um "."
}                                             //depos do Ex: {("/**"). }
