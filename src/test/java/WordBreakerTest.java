import com.ziggy.component.*;
import com.ziggy.factory.ForwardMatcherFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class WordBreakerTest {
    /*
     *
     * use the default dictionary only
     * */
    @Test
    public void case01() {
        Breaker breaker = new Breaker();
        WordSplitor splitor = new DefaultSplitor();
        WordMatcher matcher = ForwardMatcherFactory.instance().getMatcher();
        Dictionary dictionary = new Dictionary.DictionaryBuilder()
                .addWords(false, "i", "like", "samsung", "sam", "sung", "mobile", "icecream", "mango")
                .addWords(true, "ice", "cream", "man", "go")
                .build(Dictionary.MatchMode.DEFAULT_MATCH, splitor);

        String sentence = "ilikesamsungmobile";
        List<String> result = breaker.doBreak(sentence, dictionary, matcher);
        Assert.assertTrue(result.size() == 2);
        Assert.assertEquals("i like sam sung mobile", result.get(0));
        Assert.assertEquals("i like samsung mobile", result.get(1));
    }

    /*
     *
     * use the customized dictionary only
     * */
    @Test
    public void case02() {
        Breaker breaker = new Breaker();
        WordSplitor splitor = new DefaultSplitor();
        WordMatcher matcher = ForwardMatcherFactory.instance().getMatcher();
        Dictionary dictionary = new Dictionary.DictionaryBuilder()
                .addWords(false, "i", "like", "samsung", "sam", "sung", "ice", "cream")// these words won't be matched
                .addWords(true, "i", "phone", "mobile", "mango", "man", "go")//only these words will be matched
                .build(Dictionary.MatchMode.CUSTOMIZE_ONLY, splitor);

        String sentence = "iphonenotsamsungmobilemango";
        List<String> result = breaker.doBreak(sentence, dictionary, matcher);
        Assert.assertTrue(result.size() == 2);
        Assert.assertEquals("i phone notsamsung mobile man go", result.get(0));
        Assert.assertEquals("i phone notsamsung mobile mango", result.get(1));
    }

    /*
     *
     * use both the default dictionary and the customized dictionary
     * */
    @Test
    public void case03() {
        Breaker breaker = new Breaker();
        WordSplitor splitor = new DefaultSplitor();
        WordMatcher matcher = ForwardMatcherFactory.instance().getMatcher();
        Dictionary dictionary = new Dictionary.DictionaryBuilder()
                .addWords(false, "i", "like", "samsung", "sam", "sung", "mobile")
                .addWords(true, "ice", "cream")
                .build(Dictionary.MatchMode.ALL_MATCH, splitor);

        String sentence = "ilikesamsungmobileandicecream";
        List<String> result = breaker.doBreak(sentence, dictionary, matcher);
        Assert.assertTrue(result.size() == 2);
        Assert.assertEquals("i like sam sung mobile and ice cream", result.get(0));
        Assert.assertEquals("i like samsung mobile and ice cream", result.get(1));
    }
}
