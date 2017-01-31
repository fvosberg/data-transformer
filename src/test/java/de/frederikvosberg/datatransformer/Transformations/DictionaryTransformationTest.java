package de.frederikvosberg.datatransformer.Transformations;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DictionaryTransformationTest {
    @Test
    public void transformsAField() {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("foo", "oof");
        dictionary.put("bar", "rab");

        DictionaryTransformation transformation = new DictionaryTransformation(dictionary);

        assertEquals("blaoofbla", transformation.transform("blafoobla"));
        assertEquals("blarabbla", transformation.transform("blabarbla"));
        assertEquals("blablabla", transformation.transform("blablabla"));
    }
}