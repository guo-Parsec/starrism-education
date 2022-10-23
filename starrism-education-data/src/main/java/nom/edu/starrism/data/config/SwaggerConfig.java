package nom.edu.starrism.data.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.common.pool.AuthPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.singletonList;

/**
 * <p>swagger配置</p>
 *
 * @author hedwing
 * @since 2022/8/27
 **/
@Configuration
@EnableOpenApi
@EnableWebMvc
public class SwaggerConfig {
    @Value("${swagger.enable: true}")
    private boolean swaggerEnable;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(swaggerEnable)
                .securitySchemes(singletonList(tokenScheme()))
                .securityContexts(singletonList(tokenContext()))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();

    }

    private HttpAuthenticationScheme tokenScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(AuthPool.JWT_TOKEN_HEADER).build();
    }

    private SecurityContext tokenContext() {
        return SecurityContext.builder()
                .securityReferences(singletonList(SecurityReference.builder()
                        .scopes(new AuthorizationScope[0])
                        .reference(AuthPool.JWT_TOKEN_HEADER)
                        .build()))
                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("starrism-education服务接口文档")
                .description("starrism-education服务接口文档")
                .contact(new Contact("Hedwing", "", "epicParsec@outlook.com"))
                .version("1.0")
                .build();
    }
}
