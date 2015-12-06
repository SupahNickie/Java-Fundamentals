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
          String[] cells = currentLine.split("\\s*,\\s*");
          for (int i = 0; i < cells.length; i++) {
            if ((cells[i] != null) || (cells[i].length() != 0)) {
              if (index == 0) {
                headers.add(cells[i].trim());
              } else {
                currentRow.put(headers.get(i), cells[i].trim());
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
