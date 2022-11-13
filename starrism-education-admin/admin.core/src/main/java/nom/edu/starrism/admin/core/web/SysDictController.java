package nom.edu.starrism.admin.core.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.domain.param.SysDictCategoryParam;
import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.admin.core.service.SysDictCategoryService;
import nom.edu.starrism.admin.core.service.SysDictDetailService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.valid.CrudValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>系统字典管理Web</p>
 *
 * @author hedwing
 * @since 2022/11/12
 **/
@Api(value = "系统字典管理Web", tags = "系统字典管理接口")
@RestController
@RequestMapping(value = UrlPool.ADMIN_DICT_PREFIX)
public class SysDictController {
    private SysDictCategoryService dictCategoryService;

    private SysDictDetailService dictDetailService;


    @ApiOperation(value = "字典类别数据新增")
    @PostMapping(value = "/category/save.do")
    public SeResultCarrier<Void> addSysDictCategory(@Validated @RequestBody SysDictCategoryParam param) {
        dictCategoryService.addSysDictCategory(param);
        return SeResultCarrier.success();
    }

    @ApiOperation(value = "字典详情数据新增")
    @PostMapping(value = "/detail/save.do")
    public SeResultCarrier<Void> addSysDictDetail(@Validated(CrudValidGroup.Create.class) @RequestBody SysDictDetailParam param) {
        dictDetailService.create(param);
        return SeResultCarrier.success();
    }

    @ApiOperation(value = "字典详情数据更新")
    @PutMapping(value = "/detail/put.do")
    public SeResultCarrier<Void> updateSysDictDetail(@Validated(CrudValidGroup.Update.class) @RequestBody SysDictDetailParam param) {
        dictDetailService.update(param);
        return SeResultCarrier.success();
    }

    @ApiOperation(value = "字典详情数据删除")
    @DeleteMapping(value = "/detail/delete.do")
    public SeResultCarrier<Void> deleteSysDictDetail(@Validated(CrudValidGroup.Delete.class) @RequestBody SysDictDetailParam param) {
        dictDetailService.delete(param);
        return SeResultCarrier.success();
    }

    @Autowired
    public void setDictCategoryService(SysDictCategoryService dictCategoryService) {
        this.dictCategoryService = dictCategoryService;
    }

    @Autowired
    public void setDictDetailService(SysDictDetailService dictDetailService) {
        this.dictDetailService = dictDetailService;
    }
}
