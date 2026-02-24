package com.wzz.w_loader.util;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 调用栈检测工具类
 * 基于 StackWalker 实现高性能的调用链分析
 */
public class StackHelper {

    private static final StackWalker WALKER = StackWalker.getInstance(
        StackWalker.Option.RETAIN_CLASS_REFERENCE
    );

    private static final ThreadLocal<Map<String, Boolean>> CACHE = 
        ThreadLocal.withInitial(HashMap::new);

    /**
     * 获取调用者的类名
     * @param skipFrames 跳过的栈帧数量（0=当前方法的调用者）
     * @return 调用者类名，如果不存在返回 null
     */
    public static String getCallerClassName(int skipFrames) {
        return WALKER.walk(frames -> 
            frames.skip(skipFrames + 1) // +1 跳过当前方法
                .findFirst()
                .map(StackWalker.StackFrame::getClassName)
                .orElse(null)
        );
    }
    
    /**
     * 获取直接调用者的类名
     */
    public static String getCallerClassName() {
        return getCallerClassName(1);
    }
    
    /**
     * 获取调用者的 Class 对象
     */
    public static Class<?> getCallerClass(int skipFrames) {
        return WALKER.walk(frames -> 
            frames.skip(skipFrames + 1)
                .findFirst()
                .map(StackWalker.StackFrame::getDeclaringClass)
                .orElse(null)
        );
    }
    
    /**
     * 获取直接调用者的 Class 对象
     */
    public static Class<?> getCallerClass() {
        return getCallerClass(1);
    }
    
    /**
     * 获取调用者的方法名
     */
    public static String getCallerMethodName(int skipFrames) {
        return WALKER.walk(frames -> 
            frames.skip(skipFrames + 1)
                .findFirst()
                .map(StackWalker.StackFrame::getMethodName)
                .orElse(null)
        );
    }
    
    /**
     * 获取完整的调用信息（类名 + 方法名 + 行号）
     */
    public static String getCallerInfo(int skipFrames) {
        return WALKER.walk(frames -> 
            frames.skip(skipFrames + 1)
                .findFirst()
                .map(frame -> String.format("%s.%s(%s:%d)",
                    frame.getClassName(),
                    frame.getMethodName(),
                    frame.getFileName(),
                    frame.getLineNumber()
                ))
                .orElse("Unknown")
        );
    }

    /**
     * 检查是否被指定类调用（完全匹配）
     * @param className 完整的类名，如 "com.example.MyClass"
     */
    public static boolean isCalledBy(String className) {
        return WALKER.walk(frames -> 
            frames.skip(1) // 跳过当前方法
                .anyMatch(frame -> frame.getClassName().equals(className))
        );
    }
    
    /**
     * 检查是否被指定类调用（使用缓存，性能更好）
     */
    public static boolean isCalledByCached(String className) {
        Map<String, Boolean> cache = CACHE.get();
        return cache.computeIfAbsent(className, StackHelper::isCalledBy);
    }
    
    /**
     * 检查是否被指定包下的类调用
     * @param packageName 包名，如 "com.example"
     */
    public static boolean isCalledByPackage(String packageName) {
        return WALKER.walk(frames -> 
            frames.skip(1)
                .anyMatch(frame -> frame.getClassName().startsWith(packageName + "."))
        );
    }
    
    /**
     * 检查是否被指定类的任意一个调用
     */
    public static boolean isCalledByAny(String... classNames) {
        Set<String> nameSet = new HashSet<>(Arrays.asList(classNames));
        return WALKER.walk(frames -> 
            frames.skip(1)
                .anyMatch(frame -> nameSet.contains(frame.getClassName()))
        );
    }
    
    /**
     * 检查是否被指定类调用（支持通配符）
     * @param pattern 类名模式，支持 * 通配符，如 "com.example.*"
     */
    public static boolean isCalledByPattern(String pattern) {
        String regex = pattern.replace(".", "\\.").replace("*", ".*");
        return WALKER.walk(frames -> 
            frames.skip(1)
                .anyMatch(frame -> frame.getClassName().matches(regex))
        );
    }
    
    /**
     * 检查是否被 Minecraft 类调用
     */
    public static boolean isCalledByMinecraft() {
        return isCalledByPattern("net.minecraft.*");
    }
    
    /**
     * 获取完整的调用栈（字符串列表）
     * @param maxDepth 最大深度，-1 表示全部
     */
    public static List<String> getStackTrace(int maxDepth) {
        return WALKER.walk(frames -> {
            var stream = frames.skip(1); // 跳过当前方法
            if (maxDepth > 0) {
                stream = stream.limit(maxDepth);
            }
            return stream
                .map(frame -> String.format("%s.%s(%s:%d)",
                    frame.getClassName(),
                    frame.getMethodName(),
                    frame.getFileName(),
                    frame.getLineNumber()
                ))
                .collect(Collectors.toList());
        });
    }
    
    /**
     * 获取完整调用栈
     */
    public static List<String> getStackTrace() {
        return getStackTrace(-1);
    }
    
    /**
     * 获取调用链（只包含类名）
     */
    public static List<String> getCallChain(int maxDepth) {
        return WALKER.walk(frames -> {
            var stream = frames.skip(1);
            if (maxDepth > 0) {
                stream = stream.limit(maxDepth);
            }
            return stream
                .map(StackWalker.StackFrame::getClassName)
                .collect(Collectors.toList());
        });
    }
    
    /**
     * 查找第一个满足条件的栈帧
     */
    public static Optional<StackWalker.StackFrame> findFirstFrame(Predicate<StackWalker.StackFrame> predicate) {
        return WALKER.walk(frames -> 
            frames.skip(1)
                .filter(predicate)
                .findFirst()
        );
    }
    
    /**
     * 获取栈深度（到达指定类为止）
     */
    public static int getDepthToClass(String className) {
        return WALKER.walk(frames -> {
            List<StackWalker.StackFrame> list = frames.skip(1).collect(Collectors.toList());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClassName().equals(className)) {
                    return i;
                }
            }
            return -1; // 未找到
        });
    }
    
    /**
     * 检查调用链中是否包含所有指定的类（按顺序）
     */
    public static boolean hasCallSequence(String... classNames) {
        List<String> chain = getCallChain(50);
        
        int lastIndex = -1;
        for (String className : classNames) {
            int index = chain.indexOf(className);
            if (index == -1 || index <= lastIndex) {
                return false;
            }
            lastIndex = index;
        }
        return true;
    }
    
    // ==================== 调试工具 ====================
    
    /**
     * 打印当前调用栈（用于调试）
     */
    public static void printStackTrace() {
        printStackTrace(10);
    }
    
    /**
     * 打印当前调用栈（指定深度）
     */
    public static void printStackTrace(int maxDepth) {
        System.out.println("=== Stack Trace ===");
        List<String> stack = getStackTrace(maxDepth);
        for (int i = 0; i < stack.size(); i++) {
            System.out.println("  [" + i + "] " + stack.get(i));
        }
    }
    
    /**
     * 格式化调用栈为字符串
     */
    public static String formatStackTrace(int maxDepth) {
        StringBuilder sb = new StringBuilder("Stack Trace:\n");
        List<String> stack = getStackTrace(maxDepth);
        for (int i = 0; i < stack.size(); i++) {
            sb.append("  [").append(i).append("] ").append(stack.get(i)).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * 清除当前线程的缓存
     */
    public static void clearCache() {
        CACHE.get().clear();
    }
    
    /**
     * 移除所有线程的缓存
     */
    public static void clearAllCache() {
        CACHE.remove();
    }
}