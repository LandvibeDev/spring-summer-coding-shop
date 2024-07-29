package landvibe.springintro.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {

    @Around("execution(* landvibe.springintro..*(..))")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toShortString());

        try{
            return joinPoint.proceed();
        } finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toShortString() + " " + timeMs + " ms");
        }
    }
}
