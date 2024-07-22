package landvibe.springintro.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Aspect
@Component //스프링 빈에 등록
public class TimeTraceAop {
    @Around("execution(* landvibe.springintro..*(..))") //이 로직을 적용하고자 하는 범위 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
