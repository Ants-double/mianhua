package com.antsdouble.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/15
 */
public class AntsProtostuffUtil {

    private static final ThreadLocal<LinkedBuffer> BUFFER_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    private static final Set<Class<?>> WRAPPER_SET = new HashSet<>();

    private static final Class<SerializeDeserializeWrapper> WRAPPER_CLASS = SerializeDeserializeWrapper.class;


    private static final Schema<SerializeDeserializeWrapper> WRAPPER_SCHEMA = RuntimeSchema.createFrom(WRAPPER_CLASS);


    private static final Map<Class<?>, Schema<?>> CACHE_SCHEMA = new ConcurrentHashMap<>();

    static {
        WRAPPER_SET.add(List.class);
        WRAPPER_SET.add(ArrayList.class);
        WRAPPER_SET.add(CopyOnWriteArrayList.class);
        WRAPPER_SET.add(LinkedList.class);
        WRAPPER_SET.add(Stack.class);
        WRAPPER_SET.add(Vector.class);
        WRAPPER_SET.add(Map.class);
        WRAPPER_SET.add(HashMap.class);
        WRAPPER_SET.add(TreeMap.class);
        WRAPPER_SET.add(LinkedHashMap.class);
        WRAPPER_SET.add(Hashtable.class);
        WRAPPER_SET.add(SortedMap.class);
//        WRAPPER_SET.add(Object.class);
    }


    public static void registerWrapperClass(Class clazz) {
        WRAPPER_SET.add(clazz);
    }

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) CACHE_SCHEMA.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            CACHE_SCHEMA.put(clazz, schema);
        }
        return schema;
    }

    public static <T> byte[] serializer(T obj) {
        //获取序列化对象
        Class<T> clazz = (Class<T>) obj.getClass();
        //设置缓数组缓冲区
        LinkedBuffer buffer = BUFFER_THREAD_LOCAL.get();
        ;
        byte[] bytes = null;
        try {
            //获取序列化对象
            Object serializerObj = obj;
            //获取Schema对象
            Schema schema = WRAPPER_SCHEMA;
            //包装class对象
            if (WRAPPER_SET.contains(clazz)) {
                //外部类是否可以使用静态内部类的成员？【外部类使用内部类的成员，需要新建内部类实例。】
                serializerObj = SerializeDeserializeWrapper.builder(obj);
            } else {
                //将class对象和schema对象保存到hashMap中
                schema = getSchema(clazz);
            }
            //将对象转换为字节流
            bytes = ProtostuffIOUtil.toByteArray(serializerObj, schema, buffer);
        } catch (Exception e) {

            throw new IllegalStateException(e.getMessage());
        } finally {
            //回收buffer
            buffer.clear();
        }
        return bytes;
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        try {
            //判断是否是不可序列化对象，若是不能序列化对象，将对象进行包装
            if (WRAPPER_SET.contains(clazz)) {
                //SerializeDeserializeWrapper<T> wrapper = SerializeDeserializeWrapper.builder(clazz.newInstance());
                SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
                ProtostuffIOUtil.mergeFrom(data, wrapper, WRAPPER_SCHEMA);
                return wrapper.getData();
            } else {
                T message = clazz.newInstance();
                Schema<T> schema = getSchema(clazz);
                ProtostuffIOUtil.mergeFrom(data, message, schema);
                return message;
            }
        } catch (Exception e) {

            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static class SerializeDeserializeWrapper<T> {
        //泛型的使用
        private T data;

        //建造者模式(返回实体类型)
        public static <T> SerializeDeserializeWrapper<T> builder(T data) {
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<T>();
            wrapper.setData(data);
            return wrapper;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}
