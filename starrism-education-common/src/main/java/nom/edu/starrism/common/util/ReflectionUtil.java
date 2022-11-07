package nom.edu.starrism.common.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author hedwing
 * @since 2022/8/13
 **/
public class ReflectionUtil {
    private static final String SETTER_METHOD_PREFIX = "set";
    private static final String GETTER_METHOD_PREFIX = "get";

    /**
     * <p>获取全部属性 包括父类</p>
     *
     * @param clazz 类对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author hedwing
     * @since 2022/8/13
     */
    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /**
     * <p>获取全部属性 包括父类</p>
     *
     * @param entity 对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author hedwing
     * @since 2022/8/14
     */
    public static <T> List<Field> getAllFields(T entity) {
        return getAllFields(entity.getClass());
    }

    /**
     * <p>获取指定注解下的全部属性 包括父类</p>
     *
     * @param clazz           类对象
     * @param annotationClass 指定注解类对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author hedwing
     * @since 2022/8/14
     */
    public static List<Field> getAllFieldsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Field> allFields = getAllFields(clazz);
        return allFields.stream().filter(field -> field.getAnnotation(annotationClass) != null)
                .collect(Collectors.toList());
    }

    /**
     * <p>获取指定注解下的全部属性 包括父类</p>
     *
     * @param entity          对象
     * @param annotationClass 指定注解类对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author hedwing
     * @since 2022/8/14
     */
    public static <T> List<Field> getAllFieldsByAnnotation(T entity, Class<? extends Annotation> annotationClass) {
        return getAllFieldsByAnnotation(entity.getClass(), annotationClass);
    }

    /**
     * <p>获取指定注解下的全部属性 包括父类</p>
     *
     * @param entity     对象
     * @param annotation 指定注解对象
     * @return java.util.List<java.lang.reflect.Field>
     * @author hedwing
     * @since 2022/8/14
     */
    public static <T> List<Field> getAllFieldsByAnnotation(T entity, Annotation annotation) {
        return getAllFieldsByAnnotation(entity.getClass(), annotation.getClass());
    }

    /**
     * <p>获取指定注解下的全部属性 包括父类</p>
     *
     * @param clazz           类对象
     * @param annotationClass 指定注解类对象
     * @return {@link List<FieldAnnotationPair<A>>}
     * @author hedwing
     * @since 2022/8/14
     */
    public static <A extends Annotation> List<FieldAnnotationPair<A>> getAnnotationPair(Class<?> clazz,
                                                                                        Class<A> annotationClass) {
        List<Field> allFields = getAllFields(clazz);
        return allFields.stream().map(field -> {
                    A annotation = field.getAnnotation(annotationClass);
                    if (annotation != null) {
                        return new FieldAnnotationPair<A>(field, annotation);
                    }
                    return null;
                }).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * <p>获取指定注解下的全部属性 包括父类</p>
     *
     * @param entity           对象
     * @param annotationClass 指定注解类对象
     * @return {@link List<FieldAnnotationPair<A>>}
     * @author hedwing
     * @since 2022/8/14
     */
    public static <T, A extends Annotation> List<FieldAnnotationPair<A>> getAnnotationPair(T entity, Class<A> annotationClass) {
        return getAnnotationPair(entity.getClass(), annotationClass);
    }

    public static class FieldAnnotationPair<A extends Annotation> {
        private Field field;

        private A annotation;

        public FieldAnnotationPair() {
        }

        FieldAnnotationPair(Field field, A annotation) {
            this.field = field;
            this.annotation = annotation;
        }

        public Field getField() {
            return field;
        }

        public void setField(Field field) {
            this.field = field;
        }

        public A getAnnotation() {
            return annotation;
        }

        public void setAnnotation(A annotation) {
            this.annotation = annotation;
        }
    }


    /**
     * <p>通过setter为target的field属性赋值</p>
     *
     * @param field  属性
     * @param target 对象
     * @param value  赋值
     * @author hedwing
     * @since 2022/8/14
     */
    public static void setField(Field field, @NonNull Object target, @NonNull Object value) {
        Class<?> clz = target.getClass();
        String name = field.getName();
        if (StringUtil.isBlank(name)) {
            throw new RuntimeException("field name cannot be empty");
        }
        String setFieldMethodName = SETTER_METHOD_PREFIX + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
        try {
            Method setFieldMethod = clz.getMethod(setFieldMethodName, field.getType());
            setFieldMethod.invoke(target, ConvertUtils.convert(value, field.getType()));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>通过修改访问权限为target的field属性赋值</p>
     *
     * @param field  属性
     * @param target 对象
     * @param value  赋值
     * @author hedwing
     * @since 2022/8/14
     */
    public static void setFieldByAccessible(Field field, @NonNull Object target, @NonNull Object value) {
        field.setAccessible(true);
        ReflectionUtils.setField(field, target, value);
        field.setAccessible(false);
    }

    /**
     * <p>通过getter获取target的field属性值</p>
     *
     * @param field  属性
     * @param target 对象
     * @return java.lang.Object
     * @author hedwing
     * @since 2022/8/14
     */
    public static Object getFieldValue(Field field, @NonNull Object target) {
        try {
            field.setAccessible(true);
            if (Objects.isNull(field.get(target))) {
                return null;
            }
            return ConvertUtils.convert(field.get(target), field.getType());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            field.setAccessible(false);
        }
    }
}