package com.ziggy.component;

import java.util.*;

public class Breaker {


    public List<String> doBreak(String sentence, Dictionary dictionary, WordMatcher matcher) {
        Set<String> lexicon = dictionary.getLexicon();
        Map<String, Set<String>> possibilities = dictionary.getPossibilities();

        List<String> record = matcher.match(lexicon, sentence);
        List<String> result =  new ArrayList<>();
        if (record.isEmpty())return result;
        buildUp(possibilities,  record,result,"",0);
        return result;
    }

    private void buildUp(Map<String, Set<String>> possibilities, List<String> record, List<String> result, String word, int index) {
        if (index == record.size()) {
            result.add(word.trim());
            return;
        }
        String focus = record.get(index);
        Set<String> posibility = possibilities.get(focus);
        if (posibility == null) {
            posibility = new HashSet<>();
            posibility.add(focus);
        }
        int next = ++index;
        for (String s : posibility) {
            buildUp(possibilities, record, result, word + " " + s, next);
        }
    }


}
