package com.ziggy.component;

import java.util.HashSet;
import java.util.Set;

/*
* 单词切割器默认实现
*
*@author ziggy
* */
public class DefaultSplitor implements  WordSplitor {
    @Override
    public Set<String> split(Set<String> lexicon,String word) {
        int length = word.length(), index = 1;
        Set<String> left = new HashSet<>(), right = new HashSet<>();
        while (index < length) {
            String leftStr = word.substring(0, length - index);
            String rightStr = word.substring(length - index, length);
            left.add(leftStr);
            right.add(rightStr);
            index++;
        }
        Set<String> result = new HashSet<>();
        left.forEach(e -> {
            right.stream().filter(r -> lexicon.contains(e) && lexicon.contains(r)).forEach(r -> {
                result.add(e + " " + r);
            });
        });
        return result;
    }
}
