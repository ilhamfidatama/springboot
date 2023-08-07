package com.bootcamp.springboot;

public interface DatabaseListener<T> {

    public void onSuccess(T response);

}
