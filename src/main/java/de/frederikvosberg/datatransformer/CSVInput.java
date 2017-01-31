package de.frederikvosberg.datatransformer;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;

class CSVInput {
    private final BufferedReader _reader;
    private String separator = ",";

    public CSVInput(Reader reader) {
        _reader = new BufferedReader(reader);
    }

    public List<SortedMap<String, String>> readAll() throws java.io.IOException {
        List<SortedMap<String, String>> result = new LinkedList<>();
        List<String> headers = readHeaders();

        String line;
        while ((line = _reader.readLine()) != null) {
            result.add(
                    colsFromLine(headers, line)
            );
        }
        _reader.close();
        return result;
    }

    private List<String> readHeaders() throws java.io.IOException {
        List<String> headers = new ArrayList<>();

        String line = _reader.readLine();
        if (line == null) {
            throw new RuntimeException("There is no first line for the headers in the CSV");
        }

        return valuesFromLine(line);
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * creates a list of values from a CSV line
     * it uses the separator field
     *
     * @param line a line with values separated by this.separator
     * @return a list of values
     */
    private List<String> valuesFromLine(String line) {
        return Arrays.asList(
                line.split(this.separator)
        );
    }

    private SortedMap<String, String> colsFromLine(List<String> headers, String line) {
        SortedMap<String, String> cols = new TreeMap<>();
        List<String> values = valuesFromLine(line);
        Iterator<String> headersIterator = headers.iterator();
        Iterator<String> valuesIterator = values.iterator();
        while (headersIterator.hasNext() && valuesIterator.hasNext()) {
            cols.put(headersIterator.next(), valuesIterator.next());
        }
        if (headersIterator.hasNext() || valuesIterator.hasNext()) {
            throw new RuntimeException("The size of a row doesn't fit with the size of the headers");
        }
        return cols;
    }
}