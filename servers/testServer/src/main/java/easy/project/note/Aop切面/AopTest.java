package easy.project.note.Aop切面;

import easy.project.note.Aop切面.扫描方法.AopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AopTest {

    @Autowired
    AopService aopService;
    @Test
    public void testGetUserById() {
        System.out.println(aopService.getUserById(1L));
    }

    @Test
    public void testGetUserByIdWithException() {
        try {
            aopService.getUserById(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

}
