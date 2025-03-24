package EcbProject.cn.controller.NutzHelper.testConfig;

import EcbProject.cn.controller.NutzHelper.NutzConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EmptyConfig.class);
        EmptyConfig config = context.getBean(EmptyConfig.class);
        config.doSomething();
        context.close();
    }
}
