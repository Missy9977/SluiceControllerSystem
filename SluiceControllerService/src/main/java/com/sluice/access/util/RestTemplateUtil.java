package com.sluice.access.util;

import com.sluice.access.converter.ExtMappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

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
