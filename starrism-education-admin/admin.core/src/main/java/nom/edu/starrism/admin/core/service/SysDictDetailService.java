package nom.edu.starrism.admin.core.service;

import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.admin.core.mapper.SysDictDetailMapper;
import nom.edu.starrism.core.domain.entity.SysDictDetail;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.service.MainService;

/**
 * <p>字典详情Service</p>
 *
 * @author hedwing
 * @since 2022/11/11
 **/
public interface SysDictDetailService extends MainService<SysDictDetail, SysDictDetailVo, SysDictDetailParam, SysDictDetailMapper> {
}
