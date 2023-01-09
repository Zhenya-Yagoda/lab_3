package org.fpm.di.example;
//eugenia
import org.fpm.di.Container;
import org.fpm.di.Environment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

public class Example {

    private Container container;

    @Before
    public void setUp() {
        Environment env = new DummyEnvironment();
        container = env.configure(new MyConfiguration());
    }

    @Test
    public void shouldInjectSingleton() {
        assertSame(container.getComponent(MySingleton.class), container.getComponent(MySingleton.class));
    }

    @Test
    public void shouldInjectPrototype() {
        assertNotSame(container.getComponent(MyPrototype.class), container.getComponent(MyPrototype.class));
    }

    @Test
    public void shouldBuildInjectionGraph() {
        final B bAsSingleton = container.getComponent(B.class);
        assertSame(container.getComponent(A.class), bAsSingleton);
        assertSame(container.getComponent(B.class), bAsSingleton);
    }

    @Test
    public void shouldBuildInjectDependencies() {
        final UseA hasADependency = container.getComponent(UseA.class);
        assertSame(hasADependency.getDependency(), container.getComponent(B.class));
    }
    @Test
    public void new_test_1(){
        assertSame(container.getComponent(new_Student.class),container.getComponent(new_User.class));
    }
    @Test
    public void new_test_2(){
        final new_Student St_user=container.getComponent(new_Student.class);
        assertSame(container.getComponent(new_Student.class), St_user);

    }
    @Test
    public void new_test_3(){
        new_User st = container.getComponent(new_User.class);
        assertSame(st, container.getComponent(new_Student.class));

    }
    @Test
    public void new_test_4(){
        final new_Professor pr = container.getComponent(new_Professor.class);
        assertSame(pr.get_new_user(),container.getComponent(new_Student.class));

    }
}
