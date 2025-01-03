# 1. AOP 核心概念
### Aspect（切面）：
横切关注点的模块化，通常是一个类，包含多个通知（Advice）和切点（Pointcut）。
### Join Point（连接点）：
程序执行过程中的某个点，例如方法调用或异常抛出。
### Advice（通知）：
在连接点执行的动作，例如在方法调用前后执行代码。
### Pointcut（切点）：
定义哪些连接点会触发通知。
### Weaving（织入）：
将切面应用到目标对象的过程。
# Spring AOP 的通知类型
Spring AOP 支持以下五种通知类型：
### Before Advice（前置通知）：
在目标方法执行前执行。
### After Returning Advice（返回后通知）：
在目标方法成功返回后执行。
### After Throwing Advice（异常抛出后通知）：
在目标方法抛出异常后执行。
### After Advice（后置通知）：
在目标方法执行后执行（无论是否抛出异常）。
### Around Advice（环绕通知）：
在目标方法执行前后执行，可以控制目标方法的执行。