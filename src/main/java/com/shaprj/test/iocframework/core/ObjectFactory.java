package com.shaprj.test.iocframework.core;

import com.shaprj.test.iocframework.core.annotation.DependencyInject;
import com.shaprj.test.iocframework.core.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ObjectFactory {

    @SneakyThrows
    public <T, S> T createObject(ApplicationContext context, Class<T> clazz, S... args) {

        return build(context, clazz, args);

    }

    private <T, S> T build(ApplicationContext context, Class<T> clazz, S[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        final T o;

        o = construct(clazz, args);

        context.putInjectableToCashe(clazz.getName(), o);

        injectDependencies(context, clazz, o);

        postConstruct(clazz, o);

        return o;
    }

    private <T, S> T construct(Class<T> clazz, S[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        final T o;
        final Object implClazz;
        if (clazz.isInterface()) {
            Reflections reflections = new Reflections("com.shaprj.test");
            var beans = reflections.getSubTypesOf(clazz);
            if (beans.size() > 1) {
                throw new IllegalStateException(String.format("Multiple beans for %s found", clazz));
            }
            implClazz = beans.stream().findFirst()
                    .orElseThrow(() -> new IllegalStateException(String.format("Can't find bean for %s", clazz)));
        } else {
            implClazz = clazz;
        }

        if (args.length <= 0) {
            o = ((Class<T>) implClazz).getConstructor().newInstance();
        } else {
            var ctor = ((Class<T>) implClazz).getConstructor(List.class);
            o = ctor.newInstance(args);
        }
        return o;
    }

    private <T> void postConstruct(Class<T> clazz, T o) throws IllegalAccessException, InvocationTargetException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(o);
            }
        }
    }

    private <T> void injectDependencies(ApplicationContext context, Class<T> clazz, T o) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DependencyInject.class)) {
                var subO = build(context, field.getType(), new String[]{});
                field.setAccessible(true);
                field.set(o, subO);
            }
        }
    }
}
