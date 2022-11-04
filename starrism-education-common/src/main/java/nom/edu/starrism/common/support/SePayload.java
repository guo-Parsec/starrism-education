package nom.edu.starrism.common.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>信息实体类</p>
 *
 * @author hedwing
 * @since 2022/11/2
 **/
@Setter
@Getter
@Builder
public class SePayload {
    /**
     * JWT ID
     */
    private String jwtId;

    /**
     * 主题
     */
    private String subject;

    /**
     * 签发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime iat;

    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime exp;

    /**
     * 账户
     */
    private String account;

    /**
     * 权限
     */
    private Set<String> authorities;

}
