package nom.edu.starrism.core.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import nom.edu.starrism.common.helper.CodeHelper;
import nom.edu.starrism.common.helper.TextHelper;
import nom.edu.starrism.common.pool.RedisPool;
import nom.edu.starrism.common.service.RedisService;
import nom.edu.starrism.common.util.CollectionUtil;
import nom.edu.starrism.common.util.StringUtil;
import nom.edu.starrism.common.util.UUIDGeneratorUtil;
import nom.edu.starrism.core.annotation.api.ApiResource;
import nom.edu.starrism.data.component.SpringBean;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * <p>资源辅助类</p>
 *
 * @author guocq
 * @since 2022/11/17
 **/
public class ResourceHelper {
    public static final String RESOURCE = "resource";
    public static final String UUID = "uuid";

    /**
     * <p>生成资源缓存的key</p>
     *
     * @param uuid uuid
     * @return java.lang.String
     * @author guocq
     * @date 2022/11/18 9:47
     */
    public static String generateResourcesCacheKey(String uuid) {
        String template = StringUtil.redisKeyJoin(RedisPool.BASE_REDIS_KEY, RESOURCE, UUID, TextHelper.EMPTY_VAR_PLACEHOLDER);
        return TextHelper.build().format(template, uuid);
    }

    /**
     * <p>获取资源列表</p>
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author guocq
     * @date 2022/11/18 9:47
     */
    public static Map<String, Object> findResources() {
        Map<String, Object> resultData = Maps.newHashMapWithExpectedSize(2);
        String uuid = UUIDGeneratorUtil.uuid();
        List<Map<String, Object>> mappingList = findMappingList();
        resultData.put("uuid", uuid);
        resultData.put("mappings", mappingList);
        RedisService bean = SpringBean.getBean(RedisService.class);
        bean.lPushAll(generateResourcesCacheKey(uuid), mappingList.toArray());
        return resultData;
    }

    /**
     * <p>获取请求列表</p>
     *
     * @return {@link List<Map<String, Object>>}
     * @author guocq
     * @date 2022/11/18 9:47
     */
    public static List<Map<String, Object>> findMappingList() {
        ServletContext servletContext = CodeHelper.getHttpServletRequest().getSession().getServletContext();
        if (servletContext == null) {
            return Lists.newArrayList();
        }
        List<Map<String, Object>> resourceList = Lists.newArrayList();
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        if (ctx == null) {
            return resourceList;
        }
        Map<String, HandlerMapping> mappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(ctx, HandlerMapping.class, true, false);
        for (HandlerMapping handlerMapping : mappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
                handlerMethods.forEach((mappingInfo, handlerMethod) -> addResourceMap(mappingInfo, handlerMethod, resourceList));
            }
            break;
        }
        return resourceList;
    }

    private static void addResourceMap(RequestMappingInfo mappingInfo, HandlerMethod handlerMethod, List<Map<String, Object>> resourceList) {
        Method method = handlerMethod.getMethod();
        PatternsRequestCondition patternsCondition = mappingInfo.getPatternsCondition();
        if (method.isAnnotationPresent(ApiResource.class) && patternsCondition != null) {
            Map<String, Object> resourceMap = Maps.newHashMapWithExpectedSize(6);
            ApiResource apiResource = method.getAnnotation(ApiResource.class);
            String requestUrl = apiResource.app().getUrlPrefix() + CollectionUtil.findFirst(patternsCondition.getPatterns());
            resourceMap.put("permissionCategoryId", apiResource.category().getKey());
            resourceMap.put("permissionCode", apiResource.value());
            resourceMap.put("permissionName", apiResource.name());
            resourceMap.put("requestActionUrl", requestUrl);
            resourceMap.put("sort", apiResource.sort() == 0 ? null : apiResource.sort());
            resourceMap.put("remark", apiResource.remark());
            resourceList.add(resourceMap);
        }
    }

}
