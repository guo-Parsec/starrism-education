package nom.edu.starrism.core.helper;

import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.data.component.SpringBean;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;
import nom.edu.starrism.data.mapper.CoreMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * <p>批处理辅助类</p>
 *
 * @author guocq
 * @since 2022/11/23
 **/
public class BatchHelper {
    public static final Integer NUMBER_OF_SLICES = 5000;

    /**
     * <p>批量新增处理</p>
     *
     * @param mapperClass mapper类对象
     * @param list        新增的集合
     * @return int
     * @author guocq
     * @date 2022/11/23 10:18
     */
    public static <E extends AbstractDataEntity> int batchInsert(Class<? extends CoreMapper<E>> mapperClass, List<E> list) {
        SqlSessionFactory sqlSessionFactory = SpringBean.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        CoreMapper<E> mapper = sqlSession.getMapper(mapperClass);
        List<List<E>> lists = CollectionUtil.subList(list, NUMBER_OF_SLICES);
        int batchSuccessNum = 0;
        for (List<E> es : lists) {
            batchSuccessNum += es.stream().map(mapper::insert).mapToInt(Integer::intValue).sum();
        }
        sqlSession.commit();
        sqlSession.close();
        return batchSuccessNum;
    }
}
