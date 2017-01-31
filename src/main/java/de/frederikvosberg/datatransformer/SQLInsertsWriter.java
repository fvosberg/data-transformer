package de.frederikvosberg.datatransformer;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.SortedMap;

class SQLInsertsWriter {
    private String _tableName;
    private Writer _writer;

    /**
     * @param tableName the name of the SQL table in which the SQL statements should insert into
     * @param writer    the writer for the statements - e.g. a FileWriter
     */
    public SQLInsertsWriter(String tableName, Writer writer) {
        _writer = writer;
        _tableName = tableName;
    }

    public void WriteToFile(SortedMap<String, String> data) throws IOException {
        StringBuilder statement = new StringBuilder();
        statement.append("INSERT INTO `` ");
        statement.insert(13, _tableName);
        statement.append(this.ColumnsDefinition(data));
        statement.append(" VALUES ");
        statement.append(this.ValuesDefinition(data));
        statement.append(";");
        _writer.write(statement.toString());
    }

    private String ColumnsDefinition(SortedMap<String, String> data) {
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

    private String ValuesDefinition(SortedMap<String, String> data) {
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