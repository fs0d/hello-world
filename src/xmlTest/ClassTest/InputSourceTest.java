package xmlTest.ClassTest;

import jdk.internal.org.xml.sax.InputSource;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InputSourceTest {

    private InputSource is;

    public InputSourceTest() {
        this.is = new InputSource();
    }

    public InputSourceTest(String systemId) {
        this.is = new InputSource(systemId);
    }

    public InputSourceTest(InputStream byteStream) {
        this.is = new InputSource(byteStream);
    }

    public void printInputSource() {
        Field[] fields = InputSource.class.getDeclaredFields();
        for(Field field:fields){
            try {
                String methodName = "get";
                methodName += Character.toUpperCase(field.getName().charAt(0));
                methodName += field.getName().substring(1);
                Method method = InputSource.class.getMethod(methodName);
                Object value = method.invoke(is,null);
                System.out.println(field.getName()+":"+(null==value?"":value.toString()));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
