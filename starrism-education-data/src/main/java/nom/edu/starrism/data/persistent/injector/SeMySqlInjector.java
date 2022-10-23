package nom.edu.starrism.data.persistent.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import nom.edu.starrism.data.persistent.injector.methods.SeMySqlInsertAllBatch;

import java.util.List;

/**
 * <p>mysql注入</p>
 *
 * @author hedwing
 * @since 2022/8/25
 **/
public class SeMySqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
        methodList.add(new SeMySqlInsertAllBatch());
        return methodList;
    }
}
