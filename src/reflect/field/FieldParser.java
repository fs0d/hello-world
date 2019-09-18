package reflect.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;

public class FieldParser {

    public static void parse(Object target, boolean isDeclared){
        if(isDeclared){
            parseDeclaredFields(target);
        }else {
            parseFields(target);
        }
    }

    private static void parseDeclaredFields(Object target) {
        Class clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            System.out.println("----------------------------------------");
            System.out.println(doParseField(field));
            System.out.println("----------------------------------------");
        }
    }

    private static void parseFields(Object target){

    }

    private static String doParseField(Field field){
        StringBuilder sb = new StringBuilder();
        Method[] methods = field.getClass().getDeclaredMethods();
        for (Method method:methods) {
            String methodName = method.getName();
            int paramCount = method.getParameterCount();
            if(paramCount < 1 && (methodName.startsWith("get") || methodName.startsWith("is"))) {
                sb.append(methodName).append(":");
                if(method.getModifiers() == Modifier.PRIVATE) {

                    method.setAccessible(true);
                }
                Object value = null;
                try {
                    value = method.invoke(field, null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if(null!=value && value.getClass().isArray()){
                    int i = 1;
                    Iterator iterator = Arrays.asList(value).iterator();
                    while(iterator.hasNext()){
                        sb.append("value")
                                .append(i++)
                                .append(":")
                                .append(iterator.next().toString());
                    }
                    sb.append("\n");
                }else {
                    sb.append(value==null?"":value.toString())
                      .append("\n");
                }
            }
        }
        return sb.toString();
    }
}
