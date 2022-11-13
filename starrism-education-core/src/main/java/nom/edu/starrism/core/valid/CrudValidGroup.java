package nom.edu.starrism.core.valid;

/**
 * <p>增删改查校验组</p>
 *
 * @author hedwing
 * @since 2022/11/12
 **/
public interface CrudValidGroup extends ValidGroup {
    interface Create extends CrudValidGroup {
    }

    interface Update extends CrudValidGroup {
    }

    interface Query extends CrudValidGroup {
    }

    interface Delete extends CrudValidGroup {
    }
}
