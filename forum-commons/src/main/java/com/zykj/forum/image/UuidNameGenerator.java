package com.zykj.forum.image;

import java.util.UUID;

public class UuidNameGenerator implements NameGenerator{

    @Override
    public String generater(String suffix) {
        return UUID.randomUUID().toString()+suffix;
    }

    @Override
    public String change(String name) {
        return UUID.randomUUID().toString()+name.substring(name.lastIndexOf('.'));
    }
}
