package by.bsuir.registry.parser;

public class CSVCell {

    private final String columnName;
    private final String cellValue;

    public CSVCell(String columnName, String cellValue) {
        this.columnName = columnName;
        this.cellValue = cellValue;
    }

    public String getCellValue() {
        return cellValue;
    }

    public String getColumnName() {
        return columnName;
    }

}
