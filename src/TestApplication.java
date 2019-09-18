import beans.Duck;
import lockTest.DataLock;
import lockTest.MethodLock;
import lockTest.ProcessOne;
import lockTest.ProcessTwo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import reflect.field.FieldParser;
import reflect.target.GenericEntity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.BiConsumer;

public class TestApplication {

    public static void main(String[] args) {
        FieldParser.parse(new GenericEntity("field", true, (byte)3, 'a', 52.1f, 52.1d), true);
    }

    public static void contextTest() {
        ApplicationContext cxa = new ClassPathXmlApplicationContext("config\\application.xml");
        BeanFactory beanFactory = cxa.getAutowireCapableBeanFactory();
        try {
            Field field = beanFactory.getClass().getDeclaredField("beanDefinitionMap");
            int modifiers = field.getModifiers();
            if(modifiers == 18){
                field.setAccessible(true);
                Object map = field.get(beanFactory);
                if(map instanceof Map){
                    ((Map) map).forEach(new BiConsumer<String, BeanDefinition>(){

                        public void accept(String s, BeanDefinition beanDefinition) {
                            System.out.println(s);
                            System.out.println(beanDefinition.toString());
                        }
                    });
                }
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Duck duck = (Duck) cxa.getBean("duck");
        duck.fly();
        duck.quack();
    }



    public static void lockTest1(){
        DataLock data = new DataLock();
        MethodLock test1 = new MethodLock();
        test1.setData(data);
        ProcessOne one = new ProcessOne(test1);
        ProcessTwo two = new ProcessTwo(test1);
        Thread t1 = new Thread(one,"thread-deposit");
        Thread t2 = new Thread(two,"thread-draw");
        t1.start();
        t2.start();
    }
}
