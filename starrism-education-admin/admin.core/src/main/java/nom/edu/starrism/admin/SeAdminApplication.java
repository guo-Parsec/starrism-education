package nom.edu.starrism.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>业务管理微服务启动类</p>
 *
 * @author guocq
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@ComponentScan({
        "nom.edu.starrism.common",
        "nom.edu.starrism.data",
        "nom.edu.starrism.core",
        "nom.edu.starrism.admin"
})
public class SeAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeAdminApplication.class, args);
    }
}
