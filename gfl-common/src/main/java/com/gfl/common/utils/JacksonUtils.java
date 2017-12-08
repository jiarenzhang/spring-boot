package com.gfl.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.TimeZone;

public class JacksonUtils extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = LoggerFactory.getLogger(JacksonUtils.class);

    private static JacksonUtils mapper;

    public JacksonUtils() {
        this(JsonInclude.Include.NON_EMPTY);
    }

    public JacksonUtils(JsonInclude.Include include) {
        // 设置输出时包含属性的风格
        if (include != null) {
            this.setSerializationInclusion(include);
        }
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
            @Override
            public void serialize(Object value, JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException,
                    JsonProcessingException {
                jgen.writeString("");
            }
        });
        // 进行HTML解码。
        this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>(){
            @Override
            public void serialize(String value, JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException,
                    JsonProcessingException {
                jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
            }
        }));
        // 设置时区
        this.setTimeZone(TimeZone.getDefault());//getTimeZone("GMT+8:00")
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public static JacksonUtils getInstance() {
        if (mapper == null){
            mapper = new JacksonUtils().enableSimple();
        }
        return mapper;
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public static JacksonUtils nonDefaultMapper() {
        if (mapper == null){
            mapper = new JacksonUtils(JsonInclude.Include.NON_DEFAULT);
        }
        return mapper;
    }

    /**
     * Object可以是POJO，也可以是Collection或数组。
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     */
    public String _toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (IOException e) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("write to json string error:" + object, e);
            }
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>.
     *
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     *
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String,JavaType)
     * @see #_fromJson(String, JavaType)
     */
    public <T> T _fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return this.readValue(jsonString, clazz);
        } catch (IOException e) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("parse json string error:" + jsonString, e);
            }
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用函數createCollectionType构造类型,然后调用本函数.
     * @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T _fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) this.readValue(jsonString, javaType);
        } catch (IOException e) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("parse json string error:" + jsonString, e);
            }
            return null;
        }
    }

    /**
     * 构造泛型的Collection Type如:
     * ArrayList<MyBean>, 则调用constructCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用(HashMap.class,String.class, MyBean.class)
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 当JSON里只含有Bean的部分属性时，更新一个已存在Bean，只覆盖部分的属性.
     */
    @SuppressWarnings("unchecked")
    public <T> T update(String json, T object) {
        try {
            return (T) this.readerForUpdating(object).readValue(json);
        } catch (JsonProcessingException e) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("update json string:" + json + " to object:" + object + " error.", e);
            }
        } catch (IOException e) {
            if(LOGGER.isWarnEnabled()){
                LOGGER.warn("update json string:" + json + " to object:" + object + " error.", e);
            }
        }
        return null;
    }

    /**
     * 输出JSONP格式数据.
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public JacksonUtils enableEnumUseToString() {
        this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return this;
    }

    /**
     * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
     * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
     */
    public JacksonUtils enableJaxbAnnotation() {
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        this.registerModule(module);
        return this;
    }

    /**
     * 允许单引号
     * 允许不带引号的字段名称
     */
    public JacksonUtils enableSimple() {
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return this;
    }

    /**
     * 对象转换为JSON字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        return JacksonUtils.getInstance()._toJson(object);
    }

    /**
     * JSON字符串转换为对象
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz){
        return JacksonUtils.getInstance()._fromJson(jsonString, clazz);
    }

    /**
     * 将obj对象转换成 class类型的对象
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T parseObject(Object obj, Class<T> clazz){
        return JacksonUtils.fromJson(JacksonUtils.toJson(obj), clazz);
    }

}
