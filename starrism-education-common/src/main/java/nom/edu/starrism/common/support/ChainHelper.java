package nom.edu.starrism.common.support;

import nom.edu.starrism.common.enums.SeCommonResultCode;
import nom.edu.starrism.common.exception.SeException;
import nom.edu.starrism.common.support.chain.BranchChain;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * <p>链式辅助类</p>
 *
 * @author guocq
 * @since 2022/11/15
 **/
public class ChainHelper {
    /**
     * <p>处理链式分支</p>
     *
     * @param condition 条件
     * @return {@link BranchChain}
     * @author guocq
     * @date 2022/11/15 15:53
     */
    public static BranchChain condition(boolean condition) {
        return (trueProcess, falseProcess) -> {
            if (condition && Objects.nonNull(trueProcess)) {
                trueProcess.run();
                return;
            }
            if (!condition && Objects.nonNull(falseProcess)) {
                falseProcess.run();
            }
        };
    }
}
