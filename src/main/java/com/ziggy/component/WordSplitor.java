package com.ziggy.component;

import java.util.Set;

public interface WordSplitor {
  Set<String> split(Set<String> lexicon,String word);
}
