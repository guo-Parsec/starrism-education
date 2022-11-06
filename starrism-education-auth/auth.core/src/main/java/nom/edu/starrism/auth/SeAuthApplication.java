package nom.edu.starrism.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>认证中心微服务启动类</p>
 *
 * @author hedwing
 * @since 2022/11/6
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = "nom.edu.starrism.admin.api")
@ComponentScan({
        "nom.edu.starrism.common",
        "nom.edu.starrism.data",
        "nom.edu.starrism.core",
        "nom.edu.starrism.auth",
        "nom.edu.starrism.admin.api"
})
public class SeAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeAuthApplication.class, args);
    }
}
