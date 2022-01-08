package com.sluice.access.util;

import org.springframework.web.client.RestTemplate;

import com.sluice.access.converter.ExtMappingJackson2HttpMessageConverter;

/**
 * @author : missy
 * @date : 2022-01-06 19:40
 */
public class RestTemplateUtil {

    public static RestTemplate newInstance() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new ExtMappingJackson2HttpMessageConverter());
        return restTemplate;
    }
}
