package project.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: CaiZhiGang
 * @Title: EcaTableId
 * @ProjectName: servers
 * @Description: 表ID字段标识
 * @date: 2021/10/28 16:33
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface EcaTableId {
    String value() default "";
}
