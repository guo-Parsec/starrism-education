package nom.edu.starrism.common.util;

import nom.edu.starrism.common.logger.SeLogger;
import nom.edu.starrism.common.logger.SeLoggerFactory;
import nom.edu.starrism.common.support.CommonConverts;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>字符串格式化</p>
 *
 * @author hedwing
 * @since 2022/9/17
 **/
public final class TextFormat {
    private static final SeLogger LOGGER = SeLoggerFactory.getLogger(TextFormat.class);
    private final static String TEMPLATE_FIELD_NAME_PATTERN_REGEX = "\\{\\w+}";

    private final static Pattern TEMPLATE_FIELD_NAME_PATTERN = Pattern.compile(TEMPLATE_FIELD_NAME_PATTERN_REGEX);

    private static Matcher matcher = null;

    /**
     * <p>格式化</p>
     *
     * @param template template
     * @param valueMap valueMap
     * @return java.lang.String
     * @author hedwing
     * @since 2022/9/17
     */
    public static <T> String format(String template, Map<String, T> valueMap) {
        try {
            matcher = TEMPLATE_FIELD_NAME_PATTERN.matcher(template);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String param = matcher.group();
                T value = valueMap.get(param.substring(1, param.length() - 1));
                String convertValue = CommonConverts.toStr().convert(value, StringUtil.EMPTY);
                matcher.appendReplacement(sb, convertValue);
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error("text {} template format with pattern {} error, cause {}", template, TEMPLATE_FIELD_NAME_PATTERN.toString(), e);
            return template;
        }
    }

    /**
     * <p>格式化</p>
     *
     * @param template template
     * @param values   values
     * @return java.lang.String
     * @author hedwing
     * @since 2022/9/17
     */
    public static String format(String template, Object... values) {
        if (ArrayUtil.isEmpty(values)) {
            return template;
        }
        try {
            matcher = TEMPLATE_FIELD_NAME_PATTERN.matcher(template);
            StringBuffer sb = new StringBuffer();
            int i = 0;
            while (matcher.find()) {
                Object value = values[i++];
                matcher.appendReplacement(sb, value == null ? "" : value.toString());
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            LOGGER.error("text {} template format with pattern {} error, cause {}", template, TEMPLATE_FIELD_NAME_PATTERN.toString(), e);
            return template;
        }
    }

}
