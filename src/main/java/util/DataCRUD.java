package data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * This is a Class for reading/writing data to/from excel.
 */
public class DataCRUD {
  private File file;
  private InputStream inStream;
  private OutputStream outStream;
  private Workbook workBook;
  private Sheet tab;

  private String filePath;
  private String tabName;

  /**
   * This is a Constructor for the Data Class.
   *
   * @param filePath - The Path of the file include extension.
   * @param tabName  - name of the sheet/tab in the excel file.
   */
  public DataCRUD(String filePath, String tabName) {
    this.filePath = filePath;
    this.tabName = tabName;
  }


  /**
   * This is a private method/function, opens a stream and catches an IO exception if thrown during
   * the process
   *
   * @param mode - A string, whose value if equal to "write", opens an inputStream and an
   *             outputStream, else opens an inputStream only.
   * @throws - IO exception if unable to close input or output stream.
   */
  private void openStream(String mode) {
    this.file = new File(this.filePath);
    try {
      this.inStream = new FileInputStream(file);
      this.workBook = new XSSFWorkbook(inStream);
      if (mode.equals("write")) {
        this.outStream = new FileOutputStream(file);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.tab = workBook.getSheet(this.tabName);
  }


  /**
   * This is a private method/function, closes a stream and catches an IO exception if thrown during
   * the process.
   *
   * @param mode - A string, whose value if equal to "write", closes an inputStream and a
   *             outputStream, else closes an outputStream only.
   * @throws - IO exception if unable to close input or output stream.
   */
  private void closeStream(String mode) throws IllegalStateException {
    try {
      this.workBook.close();
      this.inStream.close();
      if (mode.equals("write")) {
        this.outStream.close();
      }
    } catch (Exception e) {
      if (e instanceof IOException) {
      }
    }
  }


  /**
   * This is a public method/function for reading a cell on a excel Tab. The hardRead() method reads
   * a cell value by using two parameters, the row number and column number.User MUST Instantiate a
   * Data class to use the read() method.
   *
   * @param row - an integer that represents the row number.
   * @param col - an integer that represents the column number.
   * @return - a String that represents the value of the cell.
   */
  public String hardRead(int row, int col) {
    String cellValue = "";
    openStream("read");
    Cell cell = this.tab.getRow(row).getCell(col);
    CellType cellType = cell.getCellTypeEnum();
    if (cellType == cellType.NUMERIC) {
      cellValue = cell.getNumericCellValue() + "";
    } else if (cellType == cellType.STRING) {
      cellValue = cell.getStringCellValue();
    } else if (cellType == cellType._NONE) {
      cellValue = "";
    } else {
      cellValue = "";
    }
    closeStream("read");
    return cellValue;
  }


  /**
   * This is a public method/function for reading a cell on a excel Tab. While the read() method is
   * different from the hardRead() method in that the second parameter, unlike hardread(), takes a
   * String that represents the column name. It uses the column name to first search linearly for
   * which column a cell belongs to and then retrieve the value. User MUST Instantiate a Data class
   * to use the read() method.
   *
   * @param row     - an integer that represents the row number.
   * @param colName - an String that represents the column Name.
   * @return - a String that represents the value of the cell.
   */
  public String read(int row, String colName) {
    int totCol = this.tab.getRow(0).getLastCellNum();
    Integer foundColNum = null;
    String tempColumnName = "";
    for (int x = 0; x < totCol; x++) {
      try {
        tempColumnName = tab.getRow(0).getCell(x).getStringCellValue().toUpperCase();
      } catch (NullPointerException e) {
        tempColumnName = "";
      }
      if (tempColumnName
              .equals(colName.trim().toUpperCase())) {
        foundColNum = x;
        break;
      }
    }
    return hardRead(row, foundColNum);
  }


  /**
   * This is a public method/function for writing a value to a cell on an excel Tab. User MUST
   * Instantiate a Data * class to use the read() method.
   *
   * @param value - a String whose value is appended to a cell.
   * @param row   - an integer that represents the row of an appended cell.
   * @param col   - an integer that represents the col of an appended cell.
   */
  public void write(String value, int row, int col) {
    openStream("write");
    try {
      this.tab.getRow(row).createCell(col).setCellValue(value);
      this.workBook.write(outStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    closeStream("write");
  }


  /**
   * This Method puts an excel row in a data object, map. A row hashmap contains key value pairs,
   * where the key is the column name and the value for that column field. This datastructure allows
   * users to store a row representation as a map in memory and retrieve relevant information
   * throughout a script execution.
   *
   * @param row - an integer that indicates which row  to store in the map.
   * @return - a map that represents the excel row in an object.
   */
  public HashMap<String, String> saveRowAsMap(int row) {
    openStream("read");
    int totCol = this.tab.getRow(0).getLastCellNum();
    HashMap<String, String> map = new HashMap<String, String>();
    for (int x = 0; x < totCol; x++) {
      String tempColumnName = "";
      String tempFieldval = "";
      try {
        tempColumnName = tab.getRow(0)
                .getCell(x)
                .getStringCellValue()
                .trim();
        tempFieldval = read(row, tempColumnName);
        map.put(tempColumnName, tempFieldval);
      } catch (NullPointerException e) {
        tempColumnName = null;
      }
    }
    closeStream("read");
    return map;
  }
}
