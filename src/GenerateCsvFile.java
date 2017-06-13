import xmode.LocationService;

/**
 * Simple abstraction to generate csv file for the given problem.
 **/
public class GenerateCsvFile {

  static String dayQuery(String day) {
    return  "SELECT advertiser_id, latitude, longitude, timestamp FROM location\n"+
           "where timestamp BETWEEN '"+day+" 00:00:00' AND '"+day+" 23:59:59'";
  }

  /**
   * Generates a csv file for a specific db/table/query for now.
   *
   * @param args[0]  day date, ie. '2017-05-01'
   * @param args[1]  filepath, ie. 'tmp/data.csv'
   */
  public static void main(String[] args) throws Exception {
    LocationService.generateCsvFile(dayQuery(args[0]), args[1]);
  }

}
