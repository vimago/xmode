package xmode.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class CsvWritter {
  private BufferedWriter pw;
  private StringBuilder sb;

  public CsvWritter(String filename) throws IOException {
    pw = new BufferedWriter(new FileWriter(filename));
    sb = new StringBuilder("");
  }

  public CsvWritter row(String[] values) {
    for(int i=0; i<values.length; i++) {
      if(i > 0) { sb.append(','); }
      sb.append(values[i]);
    }
    sb.append('\n');
    return this;
  }

  public CsvWritter append(String value) {
    if(sb.length() > 0) { sb.append(','); }
    sb.append(value);
    return this;
  }

  public void close() throws IOException {
    pw.write(sb.toString());
    pw.close();
  }
}
