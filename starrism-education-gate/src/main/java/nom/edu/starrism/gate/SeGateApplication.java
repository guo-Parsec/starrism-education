package nom.edu.starrism.gate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>网关微服务启动类</p>
 *
 * @author guocq
 * @since 2022/10/26
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class SeGateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeGateApplication.class, args);
    }
}
