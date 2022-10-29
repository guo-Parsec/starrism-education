package nom.edu.starrism.gate.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>白名单URL配置类</p>
 *
 * @author guocq
 * @since 2022/10/26
 **/
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "starrism.gateway.ignore")
public class IgnoreUrlProperties {
    /**
     * 放行URL配置
     */
    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
