package com.example.opentelemtrydemo;

import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.TracerProvider;

public class CustomTracerProvider implements TracerProvider {

    @Override
    public Tracer get(String instrumentationScopeName) {
        return this.tracerBuilder(instrumentationScopeName)
                .build();
    }

    @Override
    public Tracer get(String instrumentationScopeName, String instrumentationScopeVersion) {
        return  this.tracerBuilder(instrumentationScopeName)
                .setInstrumentationVersion(instrumentationScopeVersion)
                .build();
    }





}
