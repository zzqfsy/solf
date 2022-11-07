package com.zzqfsy.solf.core.utils;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzqfsy on 3/14/17.
 */
public class BeanUtils {
    private static final LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();


    public static Object convertToType(Object o, Class paramType) {
        String str = String.valueOf(o);
        if (paramType.getName().equals(Integer.class.getName())) {
            return Integer.parseInt(str);
        } else if (paramType.getName().equals(Long.class.getName())) {
            return Long.parseLong(str);
        } else if (paramType.getName().equals(Double.class.getName())) {
            return Double.parseDouble(str);
        } else if (paramType.getName().equals(Float.class.getName())) {
            return Float.parseFloat(str);
        } else if (paramType.getName().equals(String.class.getName())) {
            return str;
        } else if (paramType.getName().equals(Boolean.class.getName())) {
            return Boolean.parseBoolean(str);
        } else if (paramType.getName().equals(Short.class.getName())) {
            return Short.parseShort(str);
        } else if (paramType.getName().equals(Byte.class.getName())) {
            return Byte.parseByte(str);
        }
        return null;
    }

    public static <T> Map<String, Object> getPropertyMap(T commonEntryReq) {
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(commonEntryReq.getClass());
            PropertyDescriptor[] descriptorSrc = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : descriptorSrc) {
                String name = property.getName();
                Method method = property.getReadMethod();
                if (method != null) {
                    map.put(name, method.invoke(commonEntryReq));
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;

    }

    public static <T> Object[] genArgs(String[] argNames, Class[] paramTypes, T commonEntryReq) {
        Object[] args = new Object[paramTypes.length];
        Map<String, Object> propertyMap = BeanUtils.getPropertyMap(commonEntryReq);
        for (int i = 0; i < paramTypes.length; i++) {
            Class paramType = paramTypes[i];
            if (paramType.getName().equals(commonEntryReq.getClass().getName())) {
                args[i] = commonEntryReq;
                continue;
            }
            if (propertyMap.containsKey(argNames[i])) {
                args[i] = BeanUtils.convertToType(propertyMap.get(argNames[i]), paramType);
                continue;
            }
            args[i] = null;
        }
        return args;
    }

    public static String[] getParameterNames(Method method) {
        return parameterNameDiscoverer.getParameterNames(method);
    }
}
