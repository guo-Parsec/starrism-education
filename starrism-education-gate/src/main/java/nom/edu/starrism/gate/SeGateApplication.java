package nom.edu.starrism.gate;

import nom.edu.starrism.data.component.SpringBean;
import nom.edu.starrism.data.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * <p>网关微服务启动类</p>
 *
 * @author guocq
 * @since 2022/10/26
 **/
@EnableDiscoveryClient
@SpringBootApplication
@Import(value = {RedisConfig.class, SpringBean.class})
public class SeGateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeGateApplication.class, args);
    }
}
