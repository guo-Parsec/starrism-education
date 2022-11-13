package nom.edu.starrism.core.context;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.CommonConverts;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.core.access.DictAccess;
import nom.edu.starrism.core.domain.vo.SysDictDetailVo;
import nom.edu.starrism.core.pool.BeanPool;
import nom.edu.starrism.data.component.SpringBean;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

/**
 * <p>数据字典上下文</p>
 *
 * @author hedwing
 * @see nom.edu.starrism.core.domain.entity.SysDictCategory
 * @see nom.edu.starrism.core.domain.entity.SysDictDetail
 * @since 2022/10/23
 **/
public class SeDictContext {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(SeDictContext.class);

    /**
     * <p>字典转换</p>
     *
     * @param categoryCode 类别码
     * @param dictCode     字典码
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    public static String convert(String categoryCode, Integer dictCode) {
        return convert(categoryCode, CommonConverts.toStr().convert(dictCode, StringUtil.EMPTY));
    }

    /**
     * <p>字典转换</p>
     *
     * @param categoryCode 类别码
     * @param dictCode     字典码
     * @return {@link String}
     * @author hedwing
     * @since 2022/10/23
     */
    public static String convert(String categoryCode, String dictCode) {
        if (StringUtil.isBlank(categoryCode) || StringUtil.isBlank(dictCode)) {
            LOGGER.error("categoryCode与dictCode不能为空");
            return null;
        }
        ApplicationContext context = SpringBean.getApplicationContext();
        DictAccess dictAccess = context.getBean(BeanPool.DICT_ACCESS_BEAN_NAME, DictAccess.class);
        SysDictDetailVo detailVo = dictAccess.findDictByCategoryCodeAndDictValue(categoryCode, dictCode);
        if (Objects.isNull(detailVo)) {
            return null;
        }
        return detailVo.getDictName();
    }
}
