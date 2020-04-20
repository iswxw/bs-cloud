package com.wxw.cloud.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Author: wxw
 * @create: 2020-04-20-18:43
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "wxw.filter")
public class FilterProperties {

    private List<String> allowPaths;
}
