package nom.edu.starrism.core.access;

import nom.edu.starrism.core.domain.vo.SysParamVo;

import java.util.List;

/**
 * <p>系统参数查询接口</p>
 *
 * @author guocq
 * @since 2022/10/24
 **/
public interface ParamAccess {
    /**
     * <p>根据参数码获取参数</p>
     *
     * @param paramCode 参数码
     * @return {@link SysParamVo}
     * @author guocq
     * @date 2022/10/24 15:54
     */
    SysParamVo findByParamCode(String paramCode);

    /**
     * <p>根据组别码获取参数</p>
     *
     * @param groupCode 组别码
     * @param paramCode 参数码
     * @return {@link List<SysParamVo>}
     * @author guocq
     * @date 2022/11/11 13:51
     */
    List<SysParamVo> findByGroupCode(String groupCode, String paramCode);

    /**
     * <p>根据组别码获取参数</p>
     *
     * @param groupCode 组别码
     * @return {@link List<SysParamVo>}
     * @author guocq
     * @date 2022/11/11 13:51
     */
    List<SysParamVo> findByGroupCode(String groupCode);
}
