package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Singleton;
import java.lang.reflect.Constructor;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;

public class DummyContainer implements Container {
    DummyBinder binder;
    public DummyContainer(DummyBinder param){
        this.binder=param;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        T p=null;
        if (binder.get_in(clazz) != null) {
            return binder.get_in(clazz);
        }
        if (binder.get_im(clazz) != null) {
            return getComponent((Class<T>) binder.implementations.get(clazz));
        }
        try {
            for(Constructor<?> constructor : clazz.getConstructors()){
                if(constructor.isAnnotationPresent(Inject.class)){
                    Object[] ob = new Object[constructor.getParameters().length];
                    for(int i = 0; i < constructor.getParameters().length; i++){
                        ob[i] = getComponent(constructor.getParameters()[i].getType());
                    }
                    p = (T) constructor.newInstance(ob);
                    break;
                }
            }
            if(p == null){
                p = clazz.newInstance();
            }
            if(clazz.isAnnotationPresent(Singleton.class)){
                binder.bind(clazz, p);
            }
            return p;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
        throw new RuntimeException(e);}


    }
}
