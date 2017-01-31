package de.frederikvosberg.datatransformer;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.SortedMap;

class SQLOutput {
    private final String _tableName;
    private final Writer _writer;

    /**
     * @param tableName the name of the SQL table in which the SQL statements should insert into
     * @param writer    the writer for the statements - e.g. a FileWriter
     */
    public SQLOutput(String tableName, Writer writer) {
        _writer = writer;
        _tableName = tableName;
    }

    public void write(SortedMap<String, String> data) throws IOException {
        StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO `` ");
        statement.insert(13, _tableName);
        statement.append(this.columnsDefinition(data));
        statement.append(" VALUES ");
        statement.append(this.valuesDefinition(data));
        statement.append(";");
        _writer.write(statement.toString());
    }

    private String columnsDefinition(SortedMap<String, String> data) {
        StringBuilder cols = new StringBuilder("(");
        boolean isFirst = true;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                cols.append(",");
            }
            cols.append("`");
            cols.append(entry.getKey());
            cols.append("`");
        }
        cols.append(")");
        return cols.toString();
    }

    private String valuesDefinition(SortedMap<String, String> data) {
        StringBuilder cols = new StringBuilder("(");
        boolean isFirst = true;
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                cols.append(",");
            }
            cols.append("\"");
            cols.append(entry.getValue());
            cols.append("\"");
        }
        cols.append(")");
        return cols.toString();
    }
}