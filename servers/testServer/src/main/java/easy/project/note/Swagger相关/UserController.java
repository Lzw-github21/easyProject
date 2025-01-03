package easy.project.note.Swagger相关;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/*
启动 Spring Boot 应用后，访问以下 URL：
Swagger UI: http://localhost:28703/a/swagger-ui/index.html
OpenAPI JSON: http://localhost:28703/v3/api-docs
 */
@RestController
@RequestMapping("/api")
@Tag(name = "用户管理", description = "用户相关的 API")
public class UserController {

    @GetMapping("/user/{id}")
    @Operation(summary = "获取用户信息", description = "根据用户 ID 获取用户信息")
    @ApiResponse(responseCode = "200", description = "成功获取用户信息")
    public String getUser(
            @Parameter(description = "用户 ID", required = true, example = "1")
            @PathVariable Long id) {
        return "User " + id;
    }

    @PostMapping("/user")
    @Operation(summary = "创建用户", description = "创建一个新用户")
    @ApiResponse(responseCode = "200", description = "用户创建成功")
    public String createUser(
            @Parameter(description = "用户名", required = true, example = "John")
            @RequestParam String name) {
        return "User " + name + " created";
    }
}