package EcbProject.cn.controller.NutzHelper.testConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class EmptyConfig {
    public void doSomething() {
        System.out.println("This is an empty configuration class.");
    }
}
