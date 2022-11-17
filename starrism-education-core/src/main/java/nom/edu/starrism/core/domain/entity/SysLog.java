package nom.edu.starrism.core.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import nom.edu.starrism.core.domain.vo.SeUser;
import nom.edu.starrism.data.domain.entity.AbstractEntity;

/**
 * <p>日志记录表</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
@Setter
@Getter
@TableName("sys_log")
public class SysLog extends AbstractEntity {
    private static final long serialVersionUID = 585011004468534520L;
    /**
     * 请求路径
     */
    private String requestPath;

    /**
     * 耗时时间(ms)
     */
    private Long timeConsuming;

    /**
     * 请求类型
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求结果
     */
    private String requestResult;

    /**
     * 错误码
     */
    private Long errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 请求是否成功 1-成功 2-失败
     */
    private Integer success;

    public static final Integer SUCCESS = 1;

    public static final Integer FAILED = 2;

    /**
     * 添加时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 操作人id
     */
    private Long opUserId;

    /**
     * 操作用户账户
     */
    private String opUserAccount;
}