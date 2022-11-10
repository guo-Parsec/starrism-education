package nom.edu.starrism.admin.core.runner;

import nom.edu.starrism.admin.core.service.SysPermissionService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.pool.AuthPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author guocq
 * @since 2022/11/10
 **/
@Component
@Order(value = 0)
public class SePermissionInitRunner implements ApplicationRunner {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SePermissionInitRunner.class);

    private SysPermissionService sysPermissionService;

    private void cacheAnonUrls() {
        LOGGER.debug("匿名访问url加载");
        sysPermissionService.findPermissionUrlByCategory(AuthPool.PERMISSION_CATEGORY_ANONYMOUS);
    }

    private void cacheAuthCommonUrls() {
        LOGGER.debug("认证公共访问url加载");
        sysPermissionService.findPermissionUrlByCategory(AuthPool.PERMISSION_CATEGORY_AUTHENTICATED_COMMON);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cacheAnonUrls();
        cacheAuthCommonUrls();
    }

    @Autowired
    public void setSysPermissionService(SysPermissionService sysPermissionService) {
        this.sysPermissionService = sysPermissionService;
    }
}
