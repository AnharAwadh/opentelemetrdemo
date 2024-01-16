package com.example.opentelemtrydemo.services;


import com.example.opentelemtrydemo.aspect.CreateSpan;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderService {




    @CreateSpan(spanName = "schedule")
    public void testService(){

        log.info("schedule 1111");
        try  {
            Thread.sleep(1000);
            log.info("test 11111");
//            throw new InterruptedException("test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @CreateSpan(spanName = "demo1111")
    public void demo() throws Exception {
        log.info("test");
        throw new Exception("tessss");
    }



}

