package org.fpm.di.example;

import org.fpm.di.Binder;

import java.util.ArrayList;
import java.util.HashMap;

public class DummyBinder implements Binder {

    public ArrayList<Class<?>> mylist = new ArrayList<>();
    public HashMap<Class<?>, Class<?>> implementations = new HashMap<>();
    private HashMap<Class<?>, Object> instances = new HashMap<>();

    @Override
    public <T> void bind(Class<T> clazz) {
        mylist.add(clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        implementations.put(clazz , implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        instances.put(clazz ,instance);
    }
    public<T> Class<? extends T> get_im(Class <T> param){ //helper
        if (implementations.containsKey(param)) {return (Class<T>)  implementations.get(param);}
        else return null;
    }
    public<T> T get_in(Class <T> param){ //helper
        if (instances.containsKey(param)) {return (T) instances.get(param);}
        else return null;
    }
}
