package landvibe.springintro.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class TimeTraceAop {
    @Around("execution(* landvibe.springintro..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString()); // 어떤 메소드를 call 했는 지
        try {
            return joinPoint.proceed(); // 다음 메소드가 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString() + " " + timeMs +
                    "ms"); // 어떤 메소드를 call 했는지
        }
    }
}