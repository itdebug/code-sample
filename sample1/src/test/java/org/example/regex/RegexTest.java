package org.example.regex;

import org.junit.Test;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class RegexTest {

    private String regex = "\\$\\{\\s*(.*?)\\s*}";

    /**
     * Rigorous Test :-)
     */
    @Test
    public void regex() {
        Pattern mPattern = Pattern.compile(regex);
        Matcher mMatcher = mPattern.matcher("感谢您参加 ${campaign} 活动");
        boolean hasnext = mMatcher.find();
        while (hasnext) {
            String template = mMatcher.group(0);
            System.out.println(template);
            hasnext = mMatcher.find();
        }

    }

    @Test
    public void test() {
        LocalDate today = LocalDate.now();
        System.out.println(TaskFrequency.getItem("DAILY").isTrue(today, 0));
    }

    ;
}
