package org.fpm.di.example;

import javax.inject.Inject;

public class new_Professor {
    private final new_User new_user;

    @Inject
    public new_Professor(new_User param) {
        this.new_user = param;
    }

    public new_User get_new_user() {return new_user;}
}
