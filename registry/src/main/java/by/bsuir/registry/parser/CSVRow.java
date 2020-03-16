package by.bsuir.registry.parser;

import java.util.Iterator;
import java.util.List;

public class CSVRow implements Iterable<CSVCell> {

    private final List<CSVCell> row;

    public CSVRow(List<CSVCell> row) {
        this.row = row;
    }

    public int rowLength() {
        return row.size();
    }

    public CSVCell getAt(int index) {
        return row.get(index);
    }

    public CSVCell getByName(String name) {
        return row.stream().filter(csvCell -> csvCell.getColumnName().equals(name)).findFirst().orElse(new CSVCell(name, null));
    }

    @Override
    public Iterator<CSVCell> iterator() {
        return row.iterator();
    }

}
