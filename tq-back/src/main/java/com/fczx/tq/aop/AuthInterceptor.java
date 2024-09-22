package com.fczx.tq.aop;


import com.fczx.tq.annotation.AuthCheck;
import com.fczx.tq.common.ErrorCode;
import com.fczx.tq.exception.BusinessException;
import com.fczx.tq.model.entity.User;
import com.fczx.tq.model.enums.UserRoleEnum;
import com.fczx.tq.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验 AOP
 *

 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User loginUser = userService.getLoginUser(request);
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }
        // 必须有该权限才通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        if (userRoleEnum == null) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 如果被封号，直接拒绝
        if (UserRoleEnum.BAN.equals(userRoleEnum)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        // 必须有管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            // 用户没有管理员权限，拒绝
            if (!UserRoleEnum.ADMIN.equals(userRoleEnum)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
    /**
     * 这段代码是一个基于 Spring AOP（Aspect-Oriented Programming，面向切面编程）的权限检查拦截器。它通过拦截特定的方法调用，检查当前用户是否具有执行该方法的权限。如果用户没有足够的权限，代码将抛出异常拒绝访问。
     *
     * ### 代码详解
     *
     * #### 1. `@Around("@annotation(authCheck)")`
     * - **作用**：该注解表示这个方法是一个环绕通知（Around advice）。`@Around` 注解结合切点表达式使用，在这里表示拦截所有带有 `@authCheck` 注解的方法。
     * - **参数**：`authCheck` 是一个自定义注解，用来指定该方法需要的权限级别。
     *
     * #### 2. `ProceedingJoinPoint joinPoint, AuthCheck authCheck`
     * - `ProceedingJoinPoint`：表示连接点（JoinPoint），即当前正在执行的方法。你可以通过它来访问方法的参数、目标对象等。
     * - `AuthCheck`：这个参数是注解本身，`authCheck.mustRole()` 可以获取注解中的 `mustRole` 参数，用于判断所需的权限级别。
     *
     * #### 3. `RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();`
     * - **作用**：获取当前请求的上下文信息。
     * - `RequestContextHolder` 是 Spring 提供的工具类，用于获取当前线程的 `RequestAttributes` 对象。
     *
     * #### 4. `HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();`
     * - **作用**：从 `RequestAttributes` 中获取 `HttpServletRequest` 对象，用于访问当前 HTTP 请求的数据。
     *
     * #### 5. `User loginUser = userService.getLoginUser(request);`
     * - **作用**：通过 `userService` 获取当前登录的用户信息，这通常包括用户的角色等权限信息。
     *
     * #### 6. `UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByValue(mustRole);`
     * - **作用**：将注解中定义的 `mustRole` 转换为 `UserRoleEnum` 枚举类型，用于后续权限比较。
     *
     * #### 7. `if (mustRoleEnum == null) { return joinPoint.proceed(); }`
     * - **作用**：如果 `mustRole` 为 `null`，表示该方法不需要特定权限，直接放行，调用目标方法。
     *
     * #### 8. `UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());`
     * - **作用**：获取当前用户的角色，将其转换为 `UserRoleEnum` 枚举类型。
     *
     * #### 9. 权限检查逻辑：
     *    - **如果用户角色为空**：抛出 `NO_AUTH_ERROR` 异常。
     *    - **如果用户角色是被封禁**：抛出 `NO_AUTH_ERROR` 异常。
     *    - **如果需要管理员权限**：检查当前用户是否是管理员，否则抛出 `NO_AUTH_ERROR` 异常。
     *
     * #### 10. `return joinPoint.proceed();`
     * - **作用**：如果通过了权限检查，放行并继续执行目标方法。
     *
     * ### 总结
     * 这段代码的核心功能是：在带有 `@AuthCheck` 注解的方法执行之前，拦截方法调用，并根据当前用户的角色信息进行权限检查。如果用户拥有足够的权限，代码会继续执行目标方法；如果没有足够的权限，则抛出异常阻止方法的执行。
     */
}

