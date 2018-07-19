package com.fang.alpha;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@SpringBootApplication
public class AlphaApplication {

//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverter(){
//		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//		FastJsonConfig config = new FastJsonConfig();
//		config.setSerializerFeatures(SerializerFeature.PrettyFormat);
//		converter.setFastJsonConfig(config);
//		return new HttpMessageConverters((HttpMessageConverter)converter);
//	}

//	@Bean
//	public MultipartConfigElement multipartConfigElement() {
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		//单个文件最大
//		factory.setMaxFileSize("5120MB"); //KB,MB
//		/// 设置总上传数据总大小
//		factory.setMaxRequestSize("5120MB");
//		return factory.createMultipartConfig();
//	}

	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);
	}
}
