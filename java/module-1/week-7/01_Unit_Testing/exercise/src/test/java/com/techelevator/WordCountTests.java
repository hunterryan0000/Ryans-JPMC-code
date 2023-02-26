package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class WordCountTests {

    WordCount wordCount = new WordCount();

    @Test
    public void getCountSuccesfully(){
        String[] words = new String[]{"ba", "ba", "black", "sheep"};
        Map<String, Integer> expected = new HashMap<>();
        expected.put("ba", 2);
        expected.put("black", 1);
        expected.put("sheep", 1);


        Assert.assertEquals(expected, wordCount.getCount(words));
    }
}
