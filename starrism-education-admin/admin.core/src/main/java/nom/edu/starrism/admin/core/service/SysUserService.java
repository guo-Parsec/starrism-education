package nom.edu.starrism.admin.core.service;

import nom.edu.starrism.core.domain.vo.SeUser;
import nom.edu.starrism.core.strategy.SePasswordStrategy;

/**
 * <p>用户业务接口</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
public interface SysUserService {
    /**
     * <p>根据账户查询用户信息</p>
     *
     * @param account  账户
     * @param password 密码
     * @return {@link SeUser}
     * @author hedwing
     * @since 2022/10/24
     */
    SeUser findUserByAccount(String account, String password);

    /**
     * <p>根据账户查询用户信息</p>
     *
     * @param account          账户
     * @param password         密码
     * @param passwordStrategy 密码策略器
     * @return {@link SeUser}
     * @author hedwing
     * @since 2022/10/23
     */
    SeUser findUserByAccount(String account, String password, SePasswordStrategy passwordStrategy);
}
