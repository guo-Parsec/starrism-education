package nom.edu.starrism.common.helper;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import nom.edu.starrism.common.util.CollectionUtil;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>反射辅助类</p>
 *
 * @author guocq
 * @since 2022/11/22
 **/
public class ReflectHelper {
    private static final String SETTER_METHOD_PREFIX = "set";
    private static final String GETTER_METHOD_PREFIX = "get";

    /**
     * <p>为属性注入值</p>
     *
     * @param field  属性
     * @param target 目标对象
     * @param value  被注入的值
     * @author guocq
     * @date 2022/11/22 17:47
     */
    public static <T> void setValue(Field field, @NonNull T target, @NonNull Object value) {
        setValue(field.getName(), target, value);
    }

    /**
     * <p>为属性注入值</p>
     *
     * @param clazz  类对象
     * @param field  属性
     * @param target 目标对象
     * @param value  被注入的值
     * @author guocq
     * @date 2022/11/22 17:47
     */
    public static <T> void setValue(Class<T> clazz, Field field, @NonNull T target, @NonNull Object value) {
        setValue(clazz, field.getName(), target, value);
    }

    /**
     * <p>为属性注入值</p>
     *
     * @param fieldName 属性名
     * @param target    目标对象
     * @param value     被注入的值
     * @author guocq
     * @date 2022/11/22 17:47
     */
    @SuppressWarnings("unchecked")
    public static <T> void setValue(String fieldName, @NonNull T target, @NonNull Object value) {
        setValue((Class<T>) target.getClass(), fieldName, target, value);
    }

    /**
     * <p>为属性注入值</p>
     *
     * @param clazz     类对象
     * @param fieldName 属性名
     * @param target    目标对象
     * @param value     被注入的值
     * @author guocq
     * @date 2022/11/22 17:47
     */
    public static <T> void setValue(Class<T> clazz, String fieldName, @NonNull T target, @NonNull Object value) {
        String setter = generateSetter(fieldName);
        invokeMethod(clazz, setter, target, value);
    }

    /**
     * <p>获取属性值为字符串类型</p>
     *
     * @param field  属性
     * @param target 目标对象
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 17:38
     */
    public static <T> String getValueString(Field field, T target) {
        return getValueString(field.getName(), target);
    }

    /**
     * <p>获取属性值为字符串类型</p>
     *
     * @param fieldName 属性名
     * @param target    目标对象
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 17:38
     */
    public static <T> String getValueString(String fieldName, T target) {
        return getValue(fieldName, target, String.class);
    }

    /**
     * <p>获取属性值</p>
     *
     * @param field  属性
     * @param target 目标对象
     * @param vClazz 待转换类型的类对象
     * @return V 待转换类型
     * @author guocq
     * @date 2022/11/22 17:37
     */
    public static <T, V> V getValue(Field field, T target, Class<V> vClazz) {
        return getValue(field.getName(), target, vClazz);
    }

    /**
     * <p>获取属性值</p>
     *
     * @param fieldName 属性名
     * @param target    目标对象
     * @param vClazz    待转换类型的类对象
     * @return V 待转换类型
     * @author guocq
     * @date 2022/11/22 17:37
     */
    @SuppressWarnings("unchecked")
    public static <T, V> V getValue(String fieldName, T target, Class<V> vClazz) {
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
        Class<T> clazz = (Class<T>) target.getClass();
        return getValue(clazz, fieldName, target, vClazz);
    }

    /**
     * <p>获取属性值为字符串类型</p>
     *
     * @param clazz  类对象
     * @param field  属性
     * @param target 目标对象
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 17:34
     */
    public static <T> String getValueString(Class<T> clazz, Field field, T target) {
        return getValueString(clazz, field.getName(), target);
    }

    /**
     * <p>获取属性值为字符串类型</p>
     *
     * @param clazz     类对象
     * @param fieldName 属性名
     * @param target    目标对象
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 17:34
     */
    public static <T> String getValueString(Class<T> clazz, String fieldName, T target) {
        return getValue(clazz, fieldName, target, String.class);
    }

    /**
     * <p>获取属性值</p>
     *
     * @param clazz  类对象
     * @param field  属性
     * @param target 目标对象
     * @param vClazz 待转换类型的类对象
     * @return V 待转换类型
     * @author guocq
     * @date 2022/11/22 17:40
     */
    public static <T, V> V getValue(Class<T> clazz, Field field, T target, Class<V> vClazz) {
        return getValue(clazz, field.getName(), target, vClazz);
    }

    /**
     * <p>获取属性值</p>
     *
     * @param clazz     类对象
     * @param fieldName 属性名称
     * @param target    目标对象
     * @param vClazz    待转换类型的类对象
     * @return V 待转换类型
     * @author guocq
     * @date 2022/11/22 17:31
     */
    public static <T, V> V getValue(Class<T> clazz, String fieldName, T target, Class<V> vClazz) {
        String getter = generateGetter(fieldName);
        return vClazz.cast(invokeMethod(clazz, getter, target));
    }

    /**
     * <p>invoke方法</p>
     *
     * @param clazz      类对象
     * @param methodName 方法名称
     * @param target     目标对象
     * @param args       方法参数
     * @return java.lang.Object
     * @author guocq
     * @date 2022/11/22 17:29
     */
    public static <T> Object invokeMethod(Class<T> clazz, String methodName, T target, Object... args) {
        try {
            Method getFieldMethod = clazz.getMethod(methodName);
            return getFieldMethod.invoke(target, args);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>获取指定方法{@code method}上的指定注解{@code A}对象 </p>
     *
     * @param method          方法
     * @param annotationClazz 注解类对象
     * @return A 注解
     * @author guocq
     * @date 2022/11/22 17:22
     */
    public static <A extends Annotation> A findMethodAnnotation(Method method, Class<A> annotationClazz) {
        if (method == null) {
            throw new IllegalArgumentException("method cannot be null");
        }
        return method.getAnnotation(annotationClazz);
    }

    /**
     * <p>查询指定类及其父类{@code clazz}中被指定注解{@code annotationClass}标注的属性</p>
     *
     * @param clazz           类对象
     * @param annotationClass 指定注解类对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:18
     */
    public static List<Field> findFieldsWithAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return findFieldsWithFilter(clazz, field -> field.isAnnotationPresent(annotationClass));
    }

    /**
     * <p>查询指定类及其父类{@code clazz}中的属性名属于{@code filterFieldNames}的所有属性</p>
     *
     * @param clazz            指定类对象
     * @param filterFieldNames 允许过滤的属性名称
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:12
     */
    public static List<Field> findFieldsWithFilter(Class<?> clazz, Set<String> filterFieldNames) {
        return findFieldsWithFilter(clazz, filterFieldNames);
    }

    /**
     * <p>查询指定类{@code clazz}中的属性名属于{@code filterFieldNames}的所有属性</p>
     *
     * @param clazz            指定类对象
     * @param filterFieldNames 允许过滤的属性名称
     * @param isFindSuperClass 是否查询父类
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:12
     */
    public static List<Field> findFieldsWithFilter(Class<?> clazz, Set<String> filterFieldNames, Boolean isFindSuperClass) {
        Predicate<Field> filter = null;
        if (CollectionUtil.isNotEmpty(filterFieldNames)) {
            filter = field -> filterFieldNames.contains(field.getName());
        }
        return findFieldsWithFilter(clazz, filter, isFindSuperClass);
    }

    /**
     * <p>查询指定类及其父类{@code clazz}中的符合{@code filter}过滤条件的所有属性</p>
     *
     * @param clazz  指定类对象
     * @param filter 过滤条件
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:13
     */
    public static List<Field> findFieldsWithFilter(Class<?> clazz, Predicate<Field> filter) {
        return findFieldsWithFilter(clazz, filter, Boolean.TRUE);
    }

    /**
     * <p>查询指定类{@code clazz}中的符合{@code filter}过滤条件的所有属性</p>
     *
     * @param clazz            指定类对象
     * @param filter           过滤条件
     * @param isFindSuperClass 是否查询父类
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:06
     */
    public static List<Field> findFieldsWithFilter(Class<?> clazz, Predicate<Field> filter, Boolean isFindSuperClass) {
        List<Field> fieldList = findFields(clazz, isFindSuperClass);
        if (filter == null) {
            return fieldList;
        }
        return fieldList.stream().filter(filter)
                .collect(Collectors.toList());
    }

    /**
     * <p>查询{@code clazz}以及起父类中的属性包括private,protected和public</p>
     *
     * @param clazz 指定类对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:04
     */
    public static List<Field> findFields(Class<?> clazz) {
        return findFields(clazz, Boolean.TRUE);
    }

    /**
     * <p>查询{@code clazz}中的属性包括private,protected和public</p>
     *
     * @param clazz            指定类对象
     * @param isFindSuperClass 是否查询父类
     * @return java.util.List<java.lang.reflect.Field>
     * @author guocq
     * @date 2022/11/22 17:00
     */
    public static List<Field> findFields(Class<?> clazz, Boolean isFindSuperClass) {
        List<Field> fieldList = Lists.newArrayList();
        while (clazz != null) {
            fieldList.addAll(Lists.newArrayList(clazz.getDeclaredFields()));
            clazz = isFindSuperClass ? clazz.getSuperclass() : null;
        }
        return fieldList;
    }

    /**
     * <p>生成getter方法名称</p>
     *
     * @param field 属性
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 16:58
     */
    private static String generateGetter(Field field) {
        return generateGetter(field.getName());
    }

    /**
     * <p>生成setter方法名称</p>
     *
     * @param field 属性
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 16:58
     */
    private static String generateSetter(Field field) {
        return generateSetter(field.getName());
    }

    /**
     * <p>生成getter方法名称</p>
     *
     * @param fieldName 属性名
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 16:55
     */
    private static String generateGetter(String fieldName) {
        return generateGetterOrSetter(fieldName, GETTER_METHOD_PREFIX);
    }

    /**
     * <p>生成setter方法名称</p>
     *
     * @param fieldName 属性名
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 16:55
     */
    private static String generateSetter(String fieldName) {
        return generateGetterOrSetter(fieldName, SETTER_METHOD_PREFIX);
    }

    /**
     * <p>拼接setter或getter方法名称</p>
     *
     * @param fieldName    属性名
     * @param methodPrefix 方法前缀
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/22 16:56
     */
    private static String generateGetterOrSetter(String fieldName, String methodPrefix) {
        return methodPrefix + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, fieldName);
    }
}
