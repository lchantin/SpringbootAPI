package laura.example.springboot.logaspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Class allowing to display logs at the beginning, at the end and calculating the processing time of a method.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before("execution(* laura.example.springboot.service.UserService.*(..))")
    public void start (JoinPoint joinPoint){
        log.info("Called method {} ",joinPoint.toShortString());
    }

    @Around("execution(* laura.example.springboot.service.UserService.*(..))")
    public Object superviser(ProceedingJoinPoint joinPoint)
            throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info(String.format("Execution time of  %s.%s ==> %s ", methodSignature.getDeclaringType().getSimpleName(),
                methodSignature.getName(), stopWatch.getTotalTimeMillis()));

        return result;
    }

    @After("execution(* laura.example.springboot.service.UserService.*(..))")
    public void stop (JoinPoint joinPoint){
        log.info("End of call method {} ",joinPoint.toShortString());
    }
}