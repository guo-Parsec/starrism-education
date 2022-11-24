package nom.edu.starrism.common.helper;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.CommonConverts;
import nom.edu.starrism.common.util.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>文本辅助类</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public class TextHelper {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(TextHelper.class);
    /**
     * 非携带变量填充占位符
     */
    public static final String EMPTY_VAR_PLACEHOLDER = "{}";
    /**
     * 非携带变量填充正则模板 e.g. "this is a {} {name}" 将填充 "{}"并且忽略"{name}"
     */
    private static final String TEMPLATE_EMPTY_VAR_REGEX = "\\{\\}";

    /**
     * 携带变量填充正则模板 e.g. "this is a {} {name}" 将填充 "{name}"并且忽略"{}"
     */
    private static final String TEMPLATE_VAR_REGEX = "\\{\\w+}";

    private static final Map<FormatType, Pattern> MAPPINGS = new HashMap<>(4);

    static {
        MAPPINGS.put(FormatType.EMPTY_VAR_MAP, Pattern.compile(FormatType.EMPTY_VAR_MAP.getValue()));
        MAPPINGS.put(FormatType.VAR_MAP, Pattern.compile(FormatType.VAR_MAP.getValue()));
    }

    private final FormatType formatType;

    private TextHelper(FormatType formatType) {
        this.formatType = formatType;
    }

    public static TextHelper build() {
        return build(null);
    }

    /**
     * <p>构建当前类</p>
     *
     * @param formatType 格式化类型
     * @return {@link TextHelper}
     * @author guocq
     * @date 2022/11/17 9:46
     */
    public static TextHelper build(FormatType formatType) {
        return new TextHelper(formatType == null ? FormatType.EMPTY_VAR_MAP : formatType);
    }

    /**
     * <p>使用动态参数数组格式化字符串模板 适用于</p>
     *
     * @param template  模板
     * @param arguments 填充参数
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/17 9:33
     */
    public String format(String template, Object... arguments) {
        if (FormatType.VAR_MAP.equals(this.formatType)) {
            LOGGER.error("text `{}` template format with formatType `{}` error, cause the current operation cannot satisfy", template, this.formatType.name);
            return template;
        }
        StringBuffer buffer = null;
        try {
            Matcher matcher = MAPPINGS.get(this.formatType).matcher(template);
            buffer = new StringBuffer();
            int i = 0;
            while (matcher.find()) {
                Object value = arguments[i++];
                matcher.appendReplacement(buffer, value == null ? "" : value.toString());
            }
            matcher.appendTail(buffer);
            return buffer.toString();
        } catch (Exception e) {
            LOGGER.error("text `{}` template format with formatType `{}` error, cause {}", template, this.formatType.name, e);
            return template;
        }
    }

    /**
     * <p>使用参数map格式化字符串模板</p>
     *
     * @param template 模板
     * @param varMap   参数map
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/17 9:34
     */
    public String format(String template, Map<String, Object> varMap) {
        if (FormatType.EMPTY_VAR_MAP.equals(this.formatType)) {
            return this.format(template, varMap.values().toArray());
        }
        StringBuffer buffer = null;
        try {
            Matcher matcher = MAPPINGS.get(this.formatType).matcher(template);
            buffer = new StringBuffer();
            while (matcher.find()) {
                String param = matcher.group();
                Object value = varMap.get(param.substring(1, param.length() - 1));
                String convertValue = CommonConverts.toStr().convert(value, StringUtil.EMPTY);
                matcher.appendReplacement(buffer, convertValue);
            }
            matcher.appendTail(buffer);
        } catch (Exception e) {
            LOGGER.error("text `{}` template format with formatType `{}` error, cause {}", template, this.formatType.name, e);
            return template;
        }
        return buffer.toString();
    }

    public static enum FormatType {
        /**
         * 填充模板含变量名
         */
        VAR_MAP("{}", TEMPLATE_VAR_REGEX),

        /**
         * 填充模板不含变量名
         */
        EMPTY_VAR_MAP("{variable}", TEMPLATE_EMPTY_VAR_REGEX);

        private final String name;

        private final String value;

        FormatType(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}
