package nom.edu.starrism.data.config;

import nom.edu.starrism.common.config.CommonRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/10/21
 **/
@EnableCaching
@Configuration
public class RedisConfig extends CommonRedisConfig {

}
