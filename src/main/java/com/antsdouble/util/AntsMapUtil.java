package com.antsdouble.util;

import org.apache.commons.collections4.MapUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/12
 */
public class AntsMapUtil {


    public static <K, V> Map<K, V> initMapByList(List<K> keys, List<V> values) {
        if (keys == null || keys.size() == 0) {
            return null;
        }
        Map<K, V> result = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            result.put(keys.get(i), values.get(i));
        }
        return result;
    }


    public static <K extends Comparable<? super K>, V> Map<K, V> orderByKey(Map<K, V> map, int method) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        List<Map.Entry<K, V>> linkedList = new LinkedList<>(map.entrySet());
        Collections.sort(linkedList, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> map1, Map.Entry<K, V> map2) {
                return (map1.getKey().compareTo(map2.getKey())) * method;
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : linkedList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> orderByValue(Map<K, V> map, int method) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        List<Map.Entry<K, V>> linkedList = new LinkedList<>(map.entrySet());
        Collections.sort(linkedList, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> map1, Map.Entry<K, V> map2) {
                return (map1.getValue().compareTo(map2.getValue())) * method;
            }
        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : linkedList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static <T> T getObject(Map map, Object key) {
        return (T) MapUtils.getObject(map, key);
    }

    public static <T> List<T> mapToListObject(List<Map> mapList, T javaBean) {

        if (mapList == null || mapList.isEmpty()) {
            return null;
        }
        List<T> objectList = new ArrayList<T>();

        T object = null;
        for (Map map : mapList) {
            if (map != null) {
                object = map2Java(javaBean, map);
                objectList.add(object);
            }
        }

        return objectList;
    }

    public static <T> T map2Java(T javaBean, Map map) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                // javaBean属性名
                // javaBean属性值
                String propertyName = null;
                Object propertyValue = null;
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (map.containsKey(propertyName)) {
                        propertyValue = map.get(propertyName);
                        pd.getWriteMethod().invoke(obj, new Object[]{propertyValue});
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Map java2Map(Object javaBean) {
        Map map = new HashMap(5);

        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                // javaBean属性名
                String propertyName = null;
                // javaBean属性值
                Object propertyValue = null;
                for (PropertyDescriptor pd : propertyDescriptors) {
                    propertyName = pd.getName();
                    if (!"class".equals(propertyName)) {
                        Method readMethod = pd.getReadMethod();
                        propertyValue = readMethod.invoke(javaBean, new Object[0]);
                        map.put(propertyName, propertyValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            if (map.get(field.getName()) == null) {
                continue;
            }
            field.setAccessible(true);
            if ("java.lang.Long".equals(field.getType().getName())) {
                field.set(obj, Long.valueOf(map.get(field.getName()) + ""));
            } else if ("java.lang.Double".equals(field.getType().getName())) {
                field.set(obj, Double.valueOf(map.get(field.getName()) + ""));
            } else if ("java.lang.Integer".equals(field.getType().getName())) {
                field.set(obj, Integer.valueOf(map.get(field.getName()) + ""));
            } else {
                field.set(obj, map.get(field.getName()));
            }
        }
        return obj;
    }


    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>(16);

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

}
