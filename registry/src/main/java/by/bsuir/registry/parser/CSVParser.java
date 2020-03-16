package by.bsuir.registry.parser;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CSVParser {

    public static List<CSVRow> parse(Reader reader) {
        try (CSVReader source = new CSVReaderBuilder(reader).withCSVParser(new com.opencsv.CSVParser(';')).build()) {
            List<CSVRow> csvRows = new ArrayList<>();
            String[] columnNames = source.readNext();
            if (columnNames == null) {
                return Collections.emptyList();
            }
            String[] row = source.readNext();
            while (row != null) {
                List<CSVCell> cells = new ArrayList<>();
                for (int i = 0; i < columnNames.length; i++) {
                    cells.add(new CSVCell(columnNames[i], row[i]));
                }
                csvRows.add(new CSVRow(cells));
                row = source.readNext();
            }
            return csvRows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
