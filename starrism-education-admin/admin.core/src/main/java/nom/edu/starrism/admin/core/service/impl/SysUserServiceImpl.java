package nom.edu.starrism.admin.core.service.impl;

import nom.edu.starrism.admin.core.domain.entity.SysUser;
import nom.edu.starrism.admin.core.mapper.SysUserMapper;
import nom.edu.starrism.admin.core.service.SysUserService;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.core.domain.vo.SeUser;
import nom.edu.starrism.core.strategy.SePasswordStrategy;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>用户业务接口实现类</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Service
public class SysUserServiceImpl implements SysUserService {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SysUserServiceImpl.class);
    @Resource
    private SysUserMapper sysUserMapper;

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
    @Override
    public SeUser findUserByAccount(String account, String password, SePasswordStrategy passwordStrategy) {
        SysUser sysUser = sysUserMapper.findUserByAccount(account);
        if (AbstractDataEntity.isEmpty(sysUser)) {
            LOGGER.info("无法找到账户为{}的用户", account);
            return SeUser.empty();
        }
        SeUser seUser = sysUser.toSeUser();
        SePasswordStrategy.Option option = new SePasswordStrategy.Option();
        option.setExist(true);
        option.setSalt(sysUser.getAccount());
        seUser.setAuthenticated(passwordStrategy.match(password, sysUser.getPassword(), option));
        // todo roles permissions
        return seUser;
    }
}
