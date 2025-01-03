package easy.project.note.Aop切面.定义切面;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect // 标记为切面类
@Component // 注册为 Spring Bean
public class LoggingAspect {


/*
切点表达式
切点表达式用于定义哪些方法会被拦截。常用的表达式语法：
1：匹配 com.example.service 包下的所有方法。 execution(* com.example.service.*.*(..))：
2：匹配 UserService 类中的所有方法。execution(* com.example.service.UserService.*(..))：
3：匹配 com.example.service 包下所有以 get 开头的方法。execution(* com.example.service.*.get*(..))：
4：匹配 com.example.service 包及其子包下的所有方法 within(com.example.service..*)：。
5：可以使用逻辑运算符（&&、||、!）组合多个切点表达式。
例如：execution(* com.example.service.*.get*(..)) || execution(* com.example.service.*.set*(..))
6：匹配带有 @Loggable 注解的方法：@annotation(com.example.annotation.Loggable)
7：匹配带有 @Service 注解的类中的所有方法 @within(org.springframework.stereotype.Service)

execution(* com.example.service.UserService.*(..)) throws IllegalArgumentException 匹配

 */
    // 定义切点：匹配 com.example.service 包下的所有方法
    @Pointcut("execution(* easy.project.note.Aop切面.扫描方法.*.*(..))")
    public void serviceMethods() {

    }

    // 前置通知：在目标方法执行前执行
    @Before("serviceMethods()")
    public void beforeServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("前置通知：在目标方法执行前执行: " + methodName);
    }

    // 返回后通知：在目标方法成功返回后执行
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回后通知：在目标方法成功返回后执行: " + methodName + ", result: " + result);
    }

    // 异常抛出后通知：在目标方法抛出异常后执行
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void afterThrowingServiceMethod(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常抛出后通知：在目标方法抛出异常后执行: " + methodName + ", exception: " + exception.getMessage());
    }

    // 后置通知：在目标方法执行后执行（无论是否抛出异常）
    @After("serviceMethods()")
    public void afterServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知：在目标方法执行后执行（无论是否抛出异常）: " + methodName);
    }

    // 环绕通知：在目标方法执行前后执行
    @Around("serviceMethods()")
    public Object aroundServiceMethod(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("环绕通知：在目标方法执行前执行: " + methodName + " - Before");

        // 执行目标方法
        Object result = joinPoint.proceed();

        System.out.println("环绕通知：在目标方法执行后执行: " + methodName + " - After");
        return result;
    }
}