package com.batu.demo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class ObjectMapperUtil {

    private static final Log log = LogFactory.getLog(ObjectMapperUtil.class.getSimpleName());

    public ObjectMapperUtil() {
        throw new UnsupportedOperationException("");
    }

    private static ObjectMapper omFactory() {
        return new ObjectMapper();
    }

    public static <T> T toObject(String data, Class<T> clazz) {
        if(!StringUtils.hasLength(data)) {
            log.debug("Json parser must not be a null or empty value !");
            return null;
        }
        try {
            return omFactory().readValue(data, clazz);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static <T> String toString(T data)  {
        try {
            return omFactory().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
