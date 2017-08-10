package com.github.jmetzz.swingTests.tables;

import javax.swing.table.AbstractTableModel;
import java.util.Locale;
import java.util.ResourceBundle;

class MyTableModel extends AbstractTableModel {

    private boolean DEBUG = false;
    private static Locale locale = new Locale("en", "US");
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("labels", locale);

    private static final String ID = "TableModel.id";
    private static final String NAME = "TableModel.name";
    private static final String FIRST_NAME = "TableModel.firstname";
    private static final String EMAIL = "TableModel.email";
    private static final String LANGUAGE = "TableModel.language";
    private static final String PHONE_NUMBER = "TableModel.phonenumber";

    private final String[] COLUMN_NAMES = {ID, FIRST_NAME, NAME, EMAIL, LANGUAGE, PHONE_NUMBER};

    private Object[][] data = {
            {"AA", "Kathy", "Smith", "katthy@smith.com", "English" , "555-555-555"},
            {"AB", "John", "Doe", "john@doe.com", "English",  "555-555-555"},
            {"AC", "Sue", "Black", "sue@black.com", "English",  "555-555-555"},
            {"AD", "Jane", "White", "jane@white.com", "English",  "555-555-555"},
            {"AE", "Joe", "Brown", "joe@brown.com", "English",  "555-555-555"}
    };

    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {

        if (!COLUMN_NAMES[col].trim().equals("")) {
            return resourceBundle.getString(COLUMN_NAMES[col]);
        } else {
            return COLUMN_NAMES[col];
        }
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the columns are rendered as String
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return col != 0;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

}