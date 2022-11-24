package nom.edu.starrism.admin.core.web;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.domain.param.SysDictCategoryParam;
import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.admin.core.service.SysDictCategoryService;
import nom.edu.starrism.admin.core.service.SysDictDetailService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.Carrier;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.core.annotation.log.LogWrite;
import nom.edu.starrism.core.domain.vo.SysDictCategoryVo;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.type.AppTypes;
import nom.edu.starrism.data.valid.CrudValidGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @LogWrite
    @ApiOperation(value = "字典类别数据新增")
    @PostMapping(value = "/category/create.do")
    @ApiResource(value = "admin:dict-category:write", name = "字典类别数据新增", app = AppTypes.ADMIN)
    public Carrier<SysDictCategoryVo> addSysDictCategory(@Validated @RequestBody SysDictCategoryParam param) {
        return Carrier.success(dictCategoryService.create(param));
    }

    @LogWrite
    @ApiOperation(value = "字典类别数据更新")
    @PutMapping(value = "/category/put.do")
    @ApiResource(value = "admin:dict-category:write", name = "字典类别数据更新", app = AppTypes.ADMIN)
    public Carrier<SysDictCategoryVo> updateSysDictCategory(@Validated(CrudValidGroup.Update.class) @RequestBody SysDictCategoryParam param) {
        return Carrier.success(dictCategoryService.update(param));
    }

    @LogWrite
    @ApiOperation(value = "字典类别数据删除")
    @DeleteMapping(value = "/category/delete.do")
    @ApiResource(value = "admin:dict-category:write", name = "字典类别数据删除", app = AppTypes.ADMIN)
    public Carrier<SysDictCategoryVo> deleteSysDictCategory(@RequestBody Long id) {
        return Carrier.success(dictCategoryService.delete(id));
    }

    @LogWrite
    @ApiOperation(value = "字典详情数据新增")
    @PostMapping(value = "/detail/create.do")
    @ApiResource(value = "admin:dict-detail:write", name = "字典详情数据新增", app = AppTypes.ADMIN)
    public Carrier<SysDictDetailVo> addSysDictDetail(@Validated(CrudValidGroup.Create.class) @RequestBody SysDictDetailParam param) {
        return Carrier.success(dictDetailService.create(param));
    }

    @LogWrite
    @ApiOperation(value = "字典详情数据更新")
    @PutMapping(value = "/detail/put.do")
    @ApiResource(value = "admin:dict-detail:write", name = "字典详情数据更新", app = AppTypes.ADMIN)
    public Carrier<SysDictDetailVo> updateSysDictDetail(@Validated(CrudValidGroup.Update.class) @RequestBody SysDictDetailParam param) {
        return Carrier.success(dictDetailService.update(param));
    }

    @LogWrite
    @ApiOperation(value = "字典详情数据删除")
    @DeleteMapping(value = "/detail/delete.do")
    @ApiResource(value = "admin:dict-detail:write", name = "字典详情数据删除", app = AppTypes.ADMIN)
    public Carrier<SysDictDetailVo> deleteSysDictDetail(@RequestBody Long id) {
        return Carrier.success(dictDetailService.delete(id));
    }

    @ApiOperation(value = "字典详情数据分页查询")
    @GetMapping(value = "/detail/page")
    @ApiResource(value = "admin:dict-detail:query-page", name = "字典详情数据分页查询", app = AppTypes.ADMIN)
    public Carrier<PageInfo<SysDictDetailVo>> pageDetail(@Validated(CrudValidGroup.PageQuery.class) SysDictDetailParam param) {
        return Carrier.success(dictDetailService.pageQuery(param));
    }

    @ApiOperation(value = "字典详情数据非分页查询")
    @GetMapping(value = "/detail/list")
    @ApiResource(value = "admin:dict-detail:query-list", name = "字典详情数据非分页查询", app = AppTypes.ADMIN)
    public Carrier<List<SysDictDetailVo>> listDetail(@Validated(CrudValidGroup.Query.class) SysDictDetailParam param) {
        return Carrier.success(dictDetailService.listQuery(param));
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
