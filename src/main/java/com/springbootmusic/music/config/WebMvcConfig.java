package com.springbootmusic.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 解决跨域问题
 * */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600
                );
    }

    /**
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/song/**").addResourceLocations("file:/music/static/song/");
       registry.addResourceHandler("/singerPic/**").addResourceLocations("file:/music/static/img/singerPic/");
       registry.addResourceHandler("/songPic/**").addResourceLocations("file:/music/static/img/songPic/");
       registry.addResourceHandler("/songList/**").addResourceLocations("file:/music/static/img/songList/");
       registry.addResourceHandler("/userAvator/**").addResourceLocations("file:/music/static/img/userAvator/");
    }
}
