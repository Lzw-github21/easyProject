package easy.project.note.Aop切面.扫描方法;

import org.springframework.stereotype.Service;

@Service
public class AopService {
    public String getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return "User" + id;
    }
}
