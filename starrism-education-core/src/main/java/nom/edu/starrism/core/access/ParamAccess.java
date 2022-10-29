package nom.edu.starrism.core.access;

import nom.edu.starrism.core.domain.vo.SysParamVo;

/**
 * <p>系统参数查询接口</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
public interface ParamAccess {
    /**
    * <p>根据参数码获取参数</p>
    * @param paramCode 参数码
    * @return {@link SysParamVo}
    * @author guocq
    * @date 2022/10/24 15:54
    */
    SysParamVo findByParamCode(String paramCode);
}
