package com.fuse.canteen.utils;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Component
@Aspect
@RequiredArgsConstructor
public class LoggerDbAspect {

    private final LogMaker logMaker;

    /**
     *  for post request handles
     * @param proceedingJoinPoint
     * @param postMapping
     * @param reqBody
     * @return
     * @throws Throwable
     */
    @Around("@annotation(postMapping) && execution(public * *(..)) && args( reqBody, ..)")
    public Object PostRequest(final ProceedingJoinPoint proceedingJoinPoint, PostMapping postMapping, Object reqBody) throws Throwable {
        return logMaker.make(proceedingJoinPoint,reqBody);
    }

    /**
     * for put request handles
     * @param proceedingJoinPoint
     * @param putMapping
     * @param body
     * @return
     * @throws Throwable
     */
    @Around("@annotation(putMapping) && execution(public * *(..)) && args(body, ..)")
    public Object PutRequest(final ProceedingJoinPoint proceedingJoinPoint, PutMapping putMapping, Object body) throws Throwable {
        return logMaker.make(proceedingJoinPoint,body);
    }

    /**
     * for delete request handles
     * @param proceedingJoinPoint
     * @param body
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping) && execution(* *(..)) && args( body, ..)")
    public Object DeleteRequest(final ProceedingJoinPoint proceedingJoinPoint, Object body) throws Throwable {
        return logMaker.make(proceedingJoinPoint,body);
    }

    /**
     * for get request handles
     * @param proceedingJoinPoint
     * @param body
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) && execution(* *(..)) && args( body, ..)")
    public Object getRequest(final ProceedingJoinPoint proceedingJoinPoint, Object body) throws Throwable {
        return logMaker.make(proceedingJoinPoint,body);
    }

}
