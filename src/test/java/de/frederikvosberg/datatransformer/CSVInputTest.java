package de.frederikvosberg.datatransformer;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class CSVInputTest {
    @Test
    public void shouldUseFirstLinesAsKeys() throws IOException {
        final String CSV = "col_1,col_2,col_3\nval_1,val_2,val_3";
        StringReader reader = new StringReader(CSV);
        CSVInput input = new CSVInput(reader);

        List<SortedMap<String, String>> result = input.readAll();

        SortedMap<String, String> expectedAsFirst = new TreeMap<String, String>() {{
            put("col_1", "val_1");
            put("col_2", "val_2");
            put("col_3", "val_3");
        }};
        assertEquals(1, result.size());
        assertEquals(expectedAsFirst, result.get(0));
    }
}