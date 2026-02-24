package com.wzz.w_loader.event.events;

import com.wzz.w_loader.event.Event;

public class TransformEvent extends Event {
    private final ClassLoader classLoader;
    private final String className;
    private final Class<?> classBeingRedefined;
    private byte[] classfileBuffer;

    public TransformEvent(ClassLoader classLoader, String className, Class<?> classBeingRedefined, byte[] classfileBuffer) {
        this.classLoader = classLoader;
        this.className = className;
        this.classBeingRedefined = classBeingRedefined;
        this.classfileBuffer = classfileBuffer;
    }

    public void setClassFileBuffer(byte[] classfileBuffer) {
        this.classfileBuffer = classfileBuffer;
    }

    public byte[] getClassFileBuffer() {
        return classfileBuffer;
    }

    public Class<?> getClassBeingRedefined() {
        return classBeingRedefined;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public String getClassName() {
        return className;
    }
}
