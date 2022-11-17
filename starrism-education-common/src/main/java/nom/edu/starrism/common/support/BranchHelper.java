package nom.edu.starrism.common.support;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>分支辅助类</p>
 *
 * @author guocq
 * @since 2022/11/16
 **/
public class BranchHelper {
    private Boolean condition;

    private Boolean isSingleExe;

    private Boolean isContinue;

    private AtomicInteger currIndex = new AtomicInteger(-1);

    private List<Boolean> execCondition = Lists.newArrayList();

    public static BranchHelper build(boolean condition) {
        BranchHelper branchHelper = new BranchHelper();
        branchHelper.isContinue = Boolean.TRUE;
        branchHelper.isSingleExe = Boolean.TRUE;
        branchHelper.currIndex.decrementAndGet();
        return branchHelper.condition(condition);
    }

    public static BranchHelper build(boolean condition, boolean isSingleExe) {
        BranchHelper branchHelper = new BranchHelper();
        branchHelper.isContinue = Boolean.TRUE;
        branchHelper.isSingleExe = isSingleExe;
        return branchHelper.condition(condition);
    }

    private void offExe() {
        if (this.isSingleExe) {
            this.isContinue = Boolean.FALSE;
        }
    }


    public BranchHelper condition(boolean condition) {
        this.condition = condition;
        execCondition.add(condition);
        return this;
    }

    public BranchHelper then(Runnable runnable) {
        if (condition && isContinue) {
            runnable.run();
            this.offExe();
        }
        return this;
    }

    public void other(Runnable runnable) {
        if (!condition && isContinue) {
            boolean b = this.execCondition.stream().anyMatch(ele -> ele);
            if (!b) {
                runnable.run();
            }
            this.offExe();
        }
    }
}
