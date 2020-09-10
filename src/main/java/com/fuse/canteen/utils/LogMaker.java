package com.fuse.canteen.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fuse.canteen.entity.LogDb;
import com.fuse.canteen.repo.LoggerRepo;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class LogMaker {

    private final ThreadPoolTaskExecutor taskExecutor;
    private final LoggerRepo loggerRepo;
    private final UserDataConfig userDataConfig;

    public Object make(ProceedingJoinPoint proceedingJoinPoint, Object body) throws Throwable {
        Date startTime = new Date();
        Object value = proceedingJoinPoint.proceed();
        if (value instanceof ResponseEntity) {
            ResponseEntity responseEntity = (ResponseEntity) value;
            GlobalApiResponse globalApiResponse = (GlobalApiResponse) responseEntity.getBody();
            Long userId = userDataConfig.getLoggedInUserId();
            taskExecutor.execute(() ->{saveLog(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(),
                    startTime,
                    body,
                    globalApiResponse,
                    userId);});
            return value;
        } else {
            return value;
        }
    }

    private void saveLog(String controllerName,Date startTime, Object body, GlobalApiResponse globalApiResponse,Long userId) {
        Date date = new Date();
        LogDb logDb = LogDb.builder()
                .controllerName(controllerName)
                .processStartTime(startTime)
                .processEndTime(new Date())
                .requestBody(String.valueOf(body))
                .responseBody(globalApiResponse.toString())
                .build();
        logDb.setEntryDate(date);
        logDb.setStatusChangeDate(date);
        logDb.setEntryUserId(userId);
        logDb.setStatusChangeUserId(userId);
        loggerRepo.save(logDb);
    }
}
