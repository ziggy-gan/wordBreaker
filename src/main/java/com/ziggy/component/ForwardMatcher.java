package com.ziggy.component;

import java.util.*;

/*
 * 正向最大匹配器
 *
 *@author ziggy
 * */
public class ForwardMatcher implements WordMatcher {
    @Override
    public List<String> match(Set<String> words, String sentence) {
        List<String> record = new ArrayList<>();
        String removed = "";
        while (sentence.length() > 0) {
            String word = forward(words,sentence);
            final String temp = sentence;
            if (word != null) {
                int index = sentence.indexOf(word);
                sentence = sentence.substring(index + word.length(), sentence.length());
                if (!"".equals(removed)) record.add(removed);
                record.add(word);
                removed = "";
            } else {
                removed += sentence.substring(0, 1);
                sentence = sentence.substring(1, sentence.length());
            }
        }
        if (!"".equals(removed)) record.add(removed);
        return record;
    }
    private String forward(Set<String> words,String word) {
        String target = null;
        while (target == null && word.length() > 0) {
           if( words.contains(word))return  word;
            word = word.substring(0, word.length() - 1);
        }
        return target;
    }
}
