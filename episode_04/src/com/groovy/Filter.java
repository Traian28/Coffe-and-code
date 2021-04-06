package com.ea.groovy;

public class Filter<T> {

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private boolean flag;

    public boolean isEnabled(){
       if(flag == true)
           return true;
        return false;
    };

    public boolean evaluate (T item){

        return false;

    };
}
