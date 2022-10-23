package nom.edu.starrism.data.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import nom.edu.starrism.data.persistent.injector.SeMySqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>mybatisPlus配置</p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
@Configuration
@MapperScan({
        "com.baomidou.mybatisplus.samples.quickstart.mapper",
        "nom.edu.starrism.**.mapper",
        "nom.edu.starrism.**.repository"
})
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 防止全表更新与删除：攻击SQL阻断解析器、加入解析链，防止恶意进行delete、update全表操作
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 自动分页
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        pageInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(pageInterceptor);
        return interceptor;
    }

    @Bean
    public SeMySqlInjector mySqlInjector() {
        return new SeMySqlInjector();
    }
}
