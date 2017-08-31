package HbaseTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HbaseConnectionTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext-service.xml", "classpath:kafka-beans.xml"});

}
