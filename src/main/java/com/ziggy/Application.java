package com.ziggy;

import com.ziggy.component.*;
import com.ziggy.component.Breaker;
import com.ziggy.factory.ForwardMatcherFactory;

import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String args[]) {
        Breaker breaker = new Breaker();
        case00(breaker);
        case01(breaker);
        case02(breaker);
    }

    public static void case00(Breaker breaker) {
        WordSplitor splitor = new DefaultSplitor();
        WordMatcher matcher = ForwardMatcherFactory.instance().getMatcher();
        Dictionary dictionary = new Dictionary.DictionaryBuilder()
                .addWords(false, "i", "like", "samsung", "sam", "sung", "mobile")
                .addWords(true, "ice", "cream")
                .build(Dictionary.MatchMode.ALL_MATCH, splitor);
        String sentence = "ilikesamsungmobileandicecream";
        List<String> result = breaker.doBreak(sentence, dictionary, matcher);
        System.out.println("ALL_MATCH intput = " + sentence);
        System.out.println("output :");
        result.forEach(System.out::println);
    }

    public static void case01(Breaker breaker) {
        WordSplitor splitor = new DefaultSplitor();
        WordMatcher matcher = ForwardMatcherFactory.instance().getMatcher();
        Dictionary dictionary = new Dictionary.DictionaryBuilder()
                .addWords(false, "i", "like", "samsung", "sam", "sung", "mobile", "icecream", "mango")
                .addWords(true, "ice", "cream", "man", "go")
                .build(Dictionary.MatchMode.DEFAULT_MATCH, splitor);
        String sentence = "ilikesamsungmobile";
        List<String> result = breaker.doBreak(sentence, dictionary, matcher);
        System.out.println("");
        System.out.println("DEFAULT_MATCH intput = " + sentence);
        System.out.println("output :");
        result.forEach(System.out::println);
    }

    public static void case02(Breaker breaker) {
        WordSplitor splitor = new DefaultSplitor();
        WordMatcher matcher = ForwardMatcherFactory.instance().getMatcher();
        Dictionary dictionary = new Dictionary.DictionaryBuilder()
                .addWords(false, "i", "like", "samsung", "sam", "sung", "mobile", "ice", "cream")
                .addWords(true, "i", "phone", "mobile", "mango", "man", "go")
                .build(Dictionary.MatchMode.CUSTOMIZE_ONLY, splitor);
        String sentence = "iphonenotsamsungmobilemango";
        List<String> result = breaker.doBreak(sentence, dictionary, matcher);
        System.out.println("");
        System.out.println("CUSTOMIZE_ONLY intput = " + sentence);
        System.out.println("output :");
        result.forEach(System.out::println);
    }

}

