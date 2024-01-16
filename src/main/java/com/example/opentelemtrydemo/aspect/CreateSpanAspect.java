package com.example.opentelemtrydemo.aspect;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
@Aspect
public class CreateSpanAspect {

    @Around(value = "@annotation(com.example.opentelemtrydemo.aspect.CreateSpan)")
    public Object rateLimitPointcut(ProceedingJoinPoint joinPoint)  {
        CreateSpan metaDate = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(CreateSpan.class);
        Span span = createSpanLinkedToParent(metaDate.spanName(), "self");
        Scope scope = span.makeCurrent();
        try {
            Object proceed = joinPoint.proceed();
            span.end();
            scope.close();
            return proceed;
        } catch (Throwable e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Attributes attributes = Attributes.of(
                    AttributeKey.stringKey("exception.message"),e.getLocalizedMessage(),
                    AttributeKey.stringKey("exception.stacktrace"),sw.toString(),
                    AttributeKey.stringKey("exception.type"),e.getClass().getName()
            );
            span.addEvent("exception",attributes);
            span.setStatus(StatusCode.ERROR);
            span.end();
            scope.close();
            throw new RuntimeException(e);
        }


    }

    private static Span createSpanLinkedToParent(String spanName, String scopeName ) {
        // Fetch the trace and span IDs from wherever you've stored them
        String traceIdHex = Span.current().getSpanContext().getTraceId();
        String spanIdHex = Span.current().getSpanContext().getSpanId();

        SpanContext remoteContext = SpanContext.createFromRemoteParent(
                traceIdHex,
                spanIdHex,
                TraceFlags.getSampled(),
                TraceState.getDefault());

        return GlobalOpenTelemetry.getTracer(scopeName)
                .spanBuilder(spanName)
                .setParent(Context.current().with(Span.wrap(remoteContext)))
                .startSpan();
    }
}
