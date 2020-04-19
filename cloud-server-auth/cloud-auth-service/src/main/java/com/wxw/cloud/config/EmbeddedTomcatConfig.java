package com.wxw.cloud.config;

import org.apache.catalina.Context;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

/**
 * 解决切入式tomcat限制cookie的问题
 * @Author: wxw
 * @create: 2020-04-19-13:47
 */
@Component
public class EmbeddedTomcatConfig implements WebServerFactoryCustomizer {
    @Override
    public void customize(WebServerFactory factory) {
        ((TomcatServletWebServerFactory)factory).addContextCustomizers(new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
                context.setCookieProcessor(new LegacyCookieProcessor());
            }
        });
    }
}