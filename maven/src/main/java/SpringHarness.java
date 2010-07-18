import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SpringHarness {
    public static void main(String[] args) {
        AbstractXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");

        // check value of the singleton
        String singleton = (String) ctx.getBean("single");
        assertThat(singleton, is("the singleton"));

        // check that the singletons behaves like a singleton
        assertTrue(ctx.getBean("single") == ctx.getBean("single"));
    }
}
