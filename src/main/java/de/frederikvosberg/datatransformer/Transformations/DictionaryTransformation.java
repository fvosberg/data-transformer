package de.frederikvosberg.datatransformer.Transformations;

import java.util.Map;

/**
 * This class is used to translate strings with a dictionary
 * Be patient, the dictionary isn't ordered, so transitive sets can end up in non deterministic results
 */
public class DictionaryTransformation {
    private final Map<String, String> _dictionary;

    public DictionaryTransformation(Map<String, String> dictionary) {
        _dictionary = dictionary;
    }

    public String transform(String input) {
        for (Map.Entry<String, String> entry : _dictionary.entrySet()) {
            input = input.replace(entry.getKey(), entry.getValue());
        }
        return input;
    }
}
