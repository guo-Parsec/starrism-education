package nom.edu.starrism.common.support.chain;

/**
 * <p>分支链式处理</p>
 *
 * @author guocq
 * @since 2022/11/15
 **/
@FunctionalInterface
public interface BranchChain {
    /**
     * <p>链式处理</p>
     *
     * @param trueProcess  条件为true的处理
     * @param falseProcess 条件为false的处理
     * @author guocq
     * @date 2022/11/15 15:48
     */
    void process(Runnable trueProcess, Runnable falseProcess);

    /**
     * <p>链式处理 只处理成功条件</p>
     *
     * @param trueProcess 条件为true的处理
     * @return 当前对象
     * @author guocq
     * @date 2022/11/15 15:57
     */
    default BranchChain success(Runnable trueProcess) {
        process(trueProcess, null);
        return this;
    }

    /**
     * <p>链式处理 只处理失败条件</p>
     *
     * @param falseProcess 条件为false的处理
     * @return 当前对象
     * @author guocq
     * @date 2022/11/15 15:57
     */
    default BranchChain fail(Runnable falseProcess) {
        process(null, falseProcess);
        return this;
    }
}
