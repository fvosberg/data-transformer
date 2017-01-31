package de.frederikvosberg.datatransformer;

import org.junit.Test;

import java.io.StringWriter;
import java.util.SortedMap;
import java.util.TreeMap;

public class SQLOutputTest {
    @Test
    public void canWriteDataAsSQL() {
        SortedMap<String, String> data = new TreeMap<>();
        data.put("col_1", "val_1");
        data.put("col_2", "val_2");
        data.put("col_3", "val_3");

        StringWriter writerMock = new StringWriter();
        SQLOutput sqlOutput = new SQLOutput("my_data", writerMock);

        try {
            sqlOutput.write(data);
        } catch (Exception e) {
            org.junit.Assert.assertNull(e);
        }

        org.junit.Assert.assertEquals(
                "INSERT INTO `my_data` (`col_1`,`col_2`,`col_3`) VALUES (\"val_1\",\"val_2\",\"val_3\");",
                writerMock.toString()
        );
    }
}
