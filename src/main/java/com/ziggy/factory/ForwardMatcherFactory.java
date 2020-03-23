package com.ziggy.factory;

import com.ziggy.component.ForwardMatcher;
import com.ziggy.component.WordMatcher;

public class ForwardMatcherFactory implements  WordMatcherFactory {
    static ForwardMatcherFactory   factory=null;
    private  ForwardMatcherFactory (){
    }
    public  static ForwardMatcherFactory instance(){
        if(factory ==null) return  new ForwardMatcherFactory();
        else  return factory;
    }
    @Override
    public WordMatcher getMatcher() {
        return new ForwardMatcher();
    }
}
