package nom.edu.starrism.core.access.impl;

import com.google.common.collect.Lists;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.core.access.ParamAccess;
import nom.edu.starrism.core.domain.entity.SysParam;
import nom.edu.starrism.core.domain.vo.SysParamVo;
import nom.edu.starrism.core.pool.BeanPool;
import nom.edu.starrism.core.repository.SysParamRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>参数访问实体</p>
 *
 * @author hedwing
 * @since 2022/11/7
 **/
@Service(value = BeanPool.PARAM_ACCESS_BEAN_NAME)
public class ParamAccessImpl implements ParamAccess {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(ParamAccessImpl.class);
    @Resource
    private SysParamRepository sysParamRepository;

    /**
     * <p>根据参数码获取参数</p>
     *
     * @param paramCode 参数码
     * @return {@link SysParamVo}
     * @author guocq
     * @date 2022/10/24 15:54
     */
    @Override
    @Cacheable(key = "#paramCode", cacheNames = "param:paramCode", unless = "#result == null")
    public SysParamVo findByParamCode(String paramCode) {
        SysParam param = sysParamRepository.findByParamCode(paramCode);
        if (SysParam.isEmpty(param)) {
            LOGGER.warn("根据[paramCode:{}]查询到的参数为空", paramCode);
            return null;
        }
        return param.toVo();
    }

    /**
     * <p>根据组别码获取参数</p>
     *
     * @param groupCode 组别码
     * @param paramCode 参数码
     * @return {@link List <SysParamVo>}
     * @author guocq
     * @date 2022/11/11 13:51
     */
    @Override
    @Cacheable(key = "#groupCode+'-'+#paramCode", cacheNames = "param:group", unless = "#result == null || #result.isEmpty()")
    public List<SysParamVo> findByGroupCode(String groupCode, String paramCode) {
        List<SysParam> params = sysParamRepository.findByGroupCode(groupCode, paramCode);
        if (CollectionUtil.isEmpty(params)) {
            LOGGER.warn("根据[groupCode:{}, paramCode:{}]查询到的参数为空", groupCode, paramCode);
            return Lists.newArrayList();
        }
        return params.stream().map(SysParam::toVo).collect(Collectors.toList());
    }

    /**
     * <p>根据组别码获取参数</p>
     *
     * @param groupCode 组别码
     * @return {@link List<SysParamVo>}
     * @author guocq
     * @date 2022/11/11 13:51
     */
    @Override
    public List<SysParamVo> findByGroupCode(String groupCode) {
        return this.findByGroupCode(groupCode, null);
    }
}
