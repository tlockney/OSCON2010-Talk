import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SpringHarness {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

        // check value of the singleton
        String singleton = (String) ctx.getBean("single");
        assertThat(singleton, is("the singleton"));

        // check that singletons are singletones and prototoypes, prototypes
        assertTrue(ctx.getBean("single") == ctx.getBean("single"));
    }
}
