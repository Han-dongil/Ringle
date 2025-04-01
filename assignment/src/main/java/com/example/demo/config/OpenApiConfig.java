package com.example.demo.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import static io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER;
@Configuration

public class OpenApiConfig {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("전체 API")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi tutorApi() {
        return GroupedOpenApi.builder() 
                .group("tutor")
                .pathsToMatch("/api/v1/tutor/**")
                .build(); 
    }
    
    @Bean 
    public GroupedOpenApi studentApi() { 
    	return GroupedOpenApi.builder()
    			.group("student")
    			.pathsToMatch("/api/v1/student/**") 
    			.build(); 
    }
 
//	@Bean
//	public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
//		Info info = new Info().title("타이틀 입력").version(springdocVersion).description("API에 대한 설명 부분");
//		SecurityScheme auth = new SecurityScheme()
//			      .type(SecurityScheme.Type.APIKEY)
//			      .in(SecurityScheme.In.COOKIE).name("JSESSIONID");
//		SecurityRequirement securityRequirement = new SecurityRequirement()
//				.addList("basicAuth");
//		return new OpenAPI()
//				.info(info)
//				.components(new Components().addSecuritySchemes("basicAuth", auth))
//			    .addSecurityItem(securityRequirement);
//	}
//	
    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
    	Info info = new Info().title("링글 수강신청").version(springdocVersion).description("수강신청 과제입니다.<br> 튜터의 id : tutor , tutor2 <br> 학생의 id : student , student2<br>password :1234  <br> 로그인을 하게되면 jwt 토큰이 반환됩니다.  반환된 토큰은 각 api 오른쪽 상단에 있는 자물쇠 버튼을 눌러 입력하게되면 모든 api 요청의 헤더에 Authorization : Bearer 토큰값이 추가되어 로그인인증을 받을수있습니다. <br> 포스트맨등의 툴을 이용해서 사용할떄는 해당 헤더값을 추가해주셔야 사용이 가능합니다.<br> /api/v1/student로 시작하는 api는 학생만 사용이 가능합니다. <br> /api/v1/tutor로 시작하는 api는 튜터만 사용이 가능합니다.<br>로그인이 되지않은 상태에서는 /api/v1/login api만 사용이 가능합니다.");
    	SecurityScheme auth = new SecurityScheme()
    			.type(SecurityScheme.Type.APIKEY)
    			.in(SecurityScheme.In.COOKIE).name("JSESSIONID");
    	SecurityRequirement securityRequirement = new SecurityRequirement()
    			.addList("basicAuth");
        return new OpenAPI()
            .info(info)
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
            .components(new Components()
                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                    .name("Authorization")
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(HEADER)))
            .addSecurityItem(securityRequirement);
    }
    
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/swagger-ui/index.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
//		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
//		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
////		registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + path).setCachePeriod(3600);
//	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//        				.allowedOrigins("*");
//
//	}
}