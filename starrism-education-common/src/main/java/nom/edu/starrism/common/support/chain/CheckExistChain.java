package nom.edu.starrism.common.support.chain;

import java.util.function.Predicate;

/**
 * <p>判断是否存在链式</p>
 *
 * @author guocq
 * @since 2022/11/15
 **/
@FunctionalInterface
public interface CheckExistChain<T, R> {
    /**
     * <p>是否存在处理器</p>
     *
     * @param condition 条件
     * @param t 信息
     * @return R
     * @author guocq
     * @date 2022/11/15 16:56
     */
    R process(Predicate<T> condition, T t);

//     /**
//      * <p>是否存在处理器</p>
//      *
//      * @param existProcess    存在处理器
//      * @param notExistProcess 不存在处理器
//      * @return R 返回结果
//      * @author guocq
//      * @date 2022/11/15 16:31
//      */
//     R process(Predicate<Boolean> existProcess, Predicate<Boolean> notExistProcess);
//
//     /**
//      * <p>存在处理器</p>
//      *
//      * @param existProcess 存在处理器
//      * @return R 返回结果
//      * @author guocq
//      * @date 2022/11/15 16:32
//      */
//     default R exist(Supplier<R> existProcess) {
//
//         return process(existProcess, null);
//     }
//
//     /**
//      * <p>不存在处理器</p>
//      *
//      * @param notExistProcess 不存在处理器
//      * @return R 返回结果
//      * @author guocq
//      * @date 2022/11/15 16:32
//      */
//     default R notExist(Supplier<R> notExistProcess) {
//         return process(null, notExistProcess);
//     }
}
