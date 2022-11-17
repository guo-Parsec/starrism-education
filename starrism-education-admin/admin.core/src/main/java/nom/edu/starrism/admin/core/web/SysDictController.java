package nom.edu.starrism.admin.core.web;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nom.edu.starrism.admin.core.domain.param.SysDictCategoryParam;
import nom.edu.starrism.admin.core.domain.param.SysDictDetailParam;
import nom.edu.starrism.admin.core.service.SysDictCategoryService;
import nom.edu.starrism.admin.core.service.SysDictDetailService;
import nom.edu.starrism.common.pool.UrlPool;
import nom.edu.starrism.common.support.SeResultCarrier;
import nom.edu.starrism.core.annotation.log.LogWrite;
import nom.edu.starrism.core.domain.vo.SysDictCategoryVo;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
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
@LogWrite
@RequestMapping(value = UrlPool.ADMIN_DICT_PREFIX)
public class SysDictController {
    private SysDictCategoryService dictCategoryService;

    private SysDictDetailService dictDetailService;


    @ApiOperation(value = "字典类别数据新增")
    @PostMapping(value = "/category/create.do")
    public SeResultCarrier<SysDictCategoryVo> addSysDictCategory(@Validated @RequestBody SysDictCategoryParam param) {
        return SeResultCarrier.success(dictCategoryService.create(param));
    }

    @ApiOperation(value = "字典类别数据更新")
    @PutMapping(value = "/category/put.do")
    public SeResultCarrier<SysDictCategoryVo> updateSysDictCategory(@Validated(CrudValidGroup.Update.class) @RequestBody SysDictCategoryParam param) {
        return SeResultCarrier.success(dictCategoryService.update(param));
    }

    @ApiOperation(value = "字典类别数据删除")
    @DeleteMapping(value = "/category/delete.do")
    public SeResultCarrier<SysDictCategoryVo> deleteSysDictCategory(@RequestBody Long id) {
        return SeResultCarrier.success(dictCategoryService.delete(id));
    }

    @ApiOperation(value = "字典详情数据新增")
    @PostMapping(value = "/detail/create.do")
    public SeResultCarrier<SysDictDetailVo> addSysDictDetail(@Validated(CrudValidGroup.Create.class) @RequestBody SysDictDetailParam param) {
        return SeResultCarrier.success(dictDetailService.create(param));
    }

    @ApiOperation(value = "字典详情数据更新")
    @PutMapping(value = "/detail/put.do")
    public SeResultCarrier<SysDictDetailVo> updateSysDictDetail(@Validated(CrudValidGroup.Update.class) @RequestBody SysDictDetailParam param) {
        return SeResultCarrier.success(dictDetailService.update(param));
    }

    @ApiOperation(value = "字典详情数据删除")
    @DeleteMapping(value = "/detail/delete.do")
    public SeResultCarrier<SysDictDetailVo> deleteSysDictDetail(@RequestBody Long id) {
        return SeResultCarrier.success(dictDetailService.delete(id));
    }

    @ApiOperation(value = "字典详情数据分页查询")
    @GetMapping(value = "/detail/page")
    public SeResultCarrier<PageInfo<SysDictDetailVo>> pageDetail(@Validated(CrudValidGroup.PageQuery.class) SysDictDetailParam param) {
        return SeResultCarrier.success(dictDetailService.pageQuery(param));
    }

    @ApiOperation(value = "字典详情数据非分页查询")
    @GetMapping(value = {"/detail", "/detail/list"})
    public SeResultCarrier<List<SysDictDetailVo>> listDetail(@Validated(CrudValidGroup.Query.class) SysDictDetailParam param) {
        return SeResultCarrier.success(dictDetailService.listQuery(param));
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
