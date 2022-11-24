package nom.edu.starrism.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import nom.edu.starrism.common.enums.BaseRequest;
import nom.edu.starrism.common.exception.CoreException;
import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.Quatra;
import nom.edu.starrism.core.annotation.crud.PageQuery;
import nom.edu.starrism.core.service.MainService;
import nom.edu.starrism.core.util.PageUtil;
import nom.edu.starrism.data.component.SpringBean;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.domain.param.AbstractParam;
import nom.edu.starrism.data.domain.vo.AbstractVo;
import nom.edu.starrism.data.mapper.MainMapper;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>核心Service实现类</p>
 *
 * @author guocq
 * @since 2022/11/24
 **/
public class MainServiceImpl<E extends AbstractDataEntity, V extends AbstractVo, P extends AbstractParam, M extends MainMapper<E, P>>
        implements MainService<E, V, P, M> {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(MainServiceImpl.class);

    private Quatra<Class<E>, Class<V>, Class<P>, Class<M>> quatra;

    private M mapper;

    @SuppressWarnings("unchecked")
    public M getMapper() {
        try {
            if (mapper == null) {
                Class<M> mapperClass = getQuatra().getLeft();
                mapper = SpringBean.getBean(mapperClass);
            }
            return mapper;
        } catch (Exception e) {
            LOGGER.error("获取mapper失败");
            throw new CoreException(e, BaseRequest.FAILED, "获取mapper失败");
        }
    }

    @SuppressWarnings("unchecked")
    public Quatra<Class<E>, Class<V>, Class<P>, Class<M>> getQuatra() {
        try {
            if (this.quatra == null) {
                ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
                Class<E> entityClass = (Class<E>) parameterizedType.getActualTypeArguments()[0];
                Class<V> voClass = (Class<V>) parameterizedType.getActualTypeArguments()[1];
                Class<P> paramClass = (Class<P>) parameterizedType.getActualTypeArguments()[2];
                Class<M> mapperClass = (Class<M>) parameterizedType.getActualTypeArguments()[2];
                this.quatra = Quatra.of(entityClass, voClass, paramClass, mapperClass);
            }
            return this.quatra;
        } catch (Exception e) {
            LOGGER.error("获取entity类对象失败");
            throw new CoreException(e, BaseRequest.FAILED, "获取entity类对象失败");
        }
    }

    /**
     * <p>数据新增</p>
     *
     * @param param param
     * @return 新增后的数据
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    public V create(P param) {
        M mapper = getMapper();
        E e = convertToEntity(param);
        mapper.insert(e);
        return convertToVo(e);
    }

    /**
     * <p>数据编辑</p>
     *
     * @param param param
     * @return V 结果数据
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    public V update(P param) {
        M mapper = getMapper();
        E e = convertToEntity(param);
        mapper.update(e);
        return convertToVo(e);
    }

    /**
     * <p>数据删除</p>
     *
     * @param id 主键
     * @return V 结果数据
     * @author hedwing
     * @since 2022/11/12
     */
    @Override
    public V delete(Long id) {
        M mapper = getMapper();
        E e = mapper.selectById(id);
        if (AbstractDataEntity.isEmpty(e)) {
            String errorMessage = "不存在id为{}的数据[mainService]";
            CodeHelper.throwError(LOGGER, BaseRequest.DATA_NOT_EXIST, errorMessage, id);
        }
        mapper.delete(id);
        return convertToVo(e);
    }

    /**
     * <p>分页查询</p>
     *
     * @param param param
     * @return {@link PageInfo <V>}
     * @author hedwing
     * @since 2022/11/12
     */
    @PageQuery
    @Override
    public PageInfo<V> pageQuery(P param) {
        LOGGER.debug("根据参数{}条件查询分页数据[mainService]", param);
        Page<E> pageData = getMapper().paginationQuery(param);
        List<V> vos = pageData.stream().map(this::convertToVo).collect(Collectors.toList());
        return PageUtil.toVoPage(vos, new PageInfo<>(pageData));
    }

    /**
     * <p>查询数据(不分页)</p>
     *
     * @param param 查询参数
     * @return java.util.List<V>
     * @author guocq
     * @date 2022/11/16 9:41
     */
    @Override
    public List<V> listQuery(P param) {
        LOGGER.debug("根据参数{}条件查询数据(不进行分页)[mainService]", param);
        List<E> list = getMapper().listQuery(param);
        return list.stream().map(this::convertToVo).collect(Collectors.toList());
    }

    private E convertToEntity(P p) {
        return getQuatra().getTop().cast(p.toEntity());
    }

    private V convertToVo(E e) {
        return getQuatra().getRight().cast(e.toVo());
    }
}
