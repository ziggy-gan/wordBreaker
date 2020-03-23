package com.ziggy.component;

import java.util.*;

public class Dictionary {
    private Set<String> lexicon = new HashSet<>();
    private Map<String, Set<String>> possibilities = new HashMap<>();

    public Set<String> getLexicon() {
        return lexicon;
    }

    public void setLexicon(Set<String> lexicon) {
        this.lexicon = lexicon;
    }

    public Map<String, Set<String>> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(Map<String, Set<String>> possibilities) {
        this.possibilities = possibilities;
    }

    private Dictionary(DictionaryBuilder builder, MatchMode mode, WordSplitor splitor) {
        if (mode.equals(MatchMode.ALL_MATCH)) {
            lexicon.addAll(builder.communal);
            lexicon.addAll(builder.customized);
        }
        if (mode.equals(MatchMode.CUSTOMIZE_ONLY)) lexicon.addAll(builder.customized);
        if (mode.equals(MatchMode.DEFAULT_MATCH)) lexicon.addAll(builder.communal);
        lexicon.forEach(w -> {
            Set<String> possibility = new HashSet<>();
                possibility.add(w);
                possibility.addAll(splitor.split(lexicon, w));
                possibilities.put(w, possibility);
        });
    }

    public enum MatchMode {
        ALL_MATCH, CUSTOMIZE_ONLY, DEFAULT_MATCH;
    }

    public static class DictionaryBuilder {
        private Set<String> communal = new HashSet<>();
        private Set<String> customized = new HashSet<>();

        public DictionaryBuilder addWords(boolean customize, String... words) {
            if (customize) Arrays.stream(words).map(String::trim).forEach(customized::add);
            else Arrays.stream(words).map(String::trim).forEach(communal::add);
            return this;
        }

        public Dictionary build(MatchMode mode, WordSplitor splitor) {
            return new Dictionary(this, mode, splitor);
        }
    }
}
