import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CsvPractice {

  CsvPractice() {

  }

  public ArrayList<HashMap<String, String>> parseCsv(String path) {
    Path filePath = Paths.get(path);
    ArrayList<HashMap<String, String>> ret = new ArrayList<HashMap<String, String>>();
    String currentLine = null;

    try {
      Scanner scanner = new Scanner(filePath);
      int index = 0;
      ArrayList<String> headers = new ArrayList<String>();

      while (scanner.hasNextLine()) {
        currentLine = scanner.nextLine();
        HashMap<String, String> currentRow = new HashMap<String, String>();
        if (currentLine != null) {

          // Grabbing index of commas only outside of quoted fields
          boolean withinQuotes = false;
          ArrayList<Integer> commaIndices = new ArrayList<Integer>();
          for (int j = 0; j < currentLine.length(); j++) {
            char c = currentLine.charAt(j);
            if (c == ',' && withinQuotes == false) {
              commaIndices.add(j);
            } else if (c == '\"') {
              withinQuotes = !withinQuotes;
            }
          }

          // Splitting string
          ArrayList<String> cells = new ArrayList<String>();
          int splitIdx = 0;
          for (int idx : commaIndices) {
            cells.add(currentLine.substring(splitIdx, idx).replaceAll("\"", ""));
            splitIdx = idx + 1;
          }
          cells.add(currentLine.substring(splitIdx, currentLine.length()).replaceAll("\"", ""));

          // Pairing key with string value
          for (int i = 0; i < cells.size(); i++) {
            if ((cells.get(i) != null) || (cells.get(i).length() != 0)) {
              if (index == 0) {
                headers.add(cells.get(i).trim());
              } else {

                currentRow.put(headers.get(i), cells.get(i).trim());
              }
            }
          }
        }
        if (index > 0) {
          ret.add(currentRow);
        }
        index++;
      }
      scanner.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ret;
  }

  public static void main(String[] args) {
    String path = (System.getProperty("user.dir") + "/sample.csv");
    CsvPractice csv = new CsvPractice();
    System.out.println(csv.parseCsv(path));
  }

}
