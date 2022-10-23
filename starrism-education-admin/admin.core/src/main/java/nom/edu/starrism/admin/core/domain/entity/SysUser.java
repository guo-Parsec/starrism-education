package nom.edu.starrism.admin.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.admin.api.domain.vo.SysUserVo;
import nom.edu.starrism.core.domain.vo.SeUser;
import nom.edu.starrism.data.domain.entity.AbstractDataEntity;

import java.time.LocalDate;

/**
 * <p>系统用户表</p>
 *
 * @author hedwing
 * @since 2022/10/23
 **/
@Getter
@Setter
@TableName(value = "sys_user")
public class SysUser extends AbstractDataEntity {
    private static final long serialVersionUID = 3846436703083969078L;
    /**
     * 账户信息
     */
    @TableField(value = "`account`")
    private String account;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SysUserVo}
     * @author hedwing
     * @since 2022/10/22
     */
    @Override
    public SysUserVo toVo() {
        SysUserVo vo = new SysUserVo();
        vo.setId(this.id);
        vo.setAccount(this.account);
        vo.setAvatarUrl(this.nickname);
        vo.setSex(this.sex);
        vo.setAvatarUrl(this.avatarUrl);
        vo.setBirthday(this.birthday);
        vo.setUserType(this.userType);
        vo.setDataStatus(this.dataStatus);
        vo.setGmtCreate(this.gmtCreate);
        vo.setGmtCreate(this.gmtModify);
        return vo;
    }

    /**
     * <p>数据展示层转换</p>
     *
     * @return {@link SeUser}
     * @author hedwing
     * @since 2022/10/22
     */
    public SeUser toSeUser() {
        SeUser seUser = SeUser.empty();
        seUser.setId(this.id);
        seUser.setAccount(this.account);
        seUser.setAvatarUrl(this.nickname);
        seUser.setSex(this.sex);
        seUser.setAvatarUrl(this.avatarUrl);
        seUser.setBirthday(this.birthday);
        seUser.setUserType(this.userType);
        seUser.setDataStatus(this.dataStatus);
        seUser.setGmtCreate(this.gmtCreate);
        seUser.setGmtCreate(this.gmtModify);
        return seUser;
    }
}