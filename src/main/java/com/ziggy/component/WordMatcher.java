package com.ziggy.component;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface WordMatcher {
    List<String> match(Set<String> words, String sentence);
}
