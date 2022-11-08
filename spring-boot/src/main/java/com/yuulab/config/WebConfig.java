package com.yuulab.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yuulab.api.converter.PlainTextHttpMessageConverter;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		messageConverters.add(new PlainTextHttpMessageConverter());
	}

}
