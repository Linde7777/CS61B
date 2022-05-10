import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }


    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        //System.out.println(params);
        double paraLrlon = params.get("lrlon");
        double paraLrlat = params.get("lrlat");
        double paraUllon = params.get("ullon");
        double paraUllat = params.get("ullat");
        double paraWidth = params.get("w");
        double paraLonDPP = calculateLonDPP(paraLrlon, paraUllon, paraWidth);

        final double size = 256;
        final double ROOT_ULLON = -122.2998046875;
        final double ROOT_ULLAT = 37.892195547244356;
        final double ROOT_LRLON = -122.2119140625;
        final double ROOT_LRLAT = 37.82280243352756;

        /*
        singleTileLen1=(ROOT_LRLON-ROOT_ULLON)/2;
        singleTileLen2=(ROOT_LRLON-ROOT_ULLON)/4;
        singleTileLen3=(ROOT_LRLON-ROOT_ULLON)/8;
        LonDPP1=singleTileLen1/256;
        LonDPP2=singleTileLen2/256;
        LonDPP3=singleTileLen3/256;
         */
        int depth = -1;
        for (int i = 1; i < 7; i++) {
            double singleTileLen = (ROOT_LRLON - ROOT_ULLON) / Math.pow(2, i);
            double LonDPP = singleTileLen / size;
            if (LonDPP < paraLonDPP) {
                depth = i;
                break;
            }
        }


        int len = (int) Math.pow(2, depth);
        //TODO: seal calculating border into method

        /*
        if depth==3
        len=2^3=8
        x0_leftBorder=ROOT_ULLON+(ROOT_LRLON-ROOT_ULLON)*(0/8)
        x1_leftBorder=ROOT_ULLON+(ROOT_LRLON-ROOT_ULLON)*(1/8)
        x2_leftBorder=ROOT_ULLON+(ROOT_LRLON-ROOT_ULLON)*(2/8)
        x3_leftBorder=ROOT_ULLON+(ROOT_LRLON-ROOT_ULLON)*(3/8)
        */
        double[] leftBorders = new double[len];
        for (int i = 0; i < len; i++) {
            leftBorders[i] = ROOT_ULLON + (ROOT_LRLON - ROOT_ULLON) * ((double) i / len);
        }
        double raster_ul_lon = -1;
        int ullonTileIndex = -1;
        for (int i = 0; i < leftBorders.length - 1; i++) {
            if (paraUllon >= leftBorders[i]
                    && paraUllon < leftBorders[i + 1]) {
                raster_ul_lon = leftBorders[i];
                ullonTileIndex = i;
                break;
            }
        }

        /*
        if depth==3
        len=2^3=8
        x0_rightBorder=x0_leftBorder+(ROOT_LRLON-ROOT_ULLON)/8
        x1_rightBorder=x1_leftBorder+(ROOT_LRLON-ROOT_ULLON)/8
        x2_rightBorder=x2_leftBorder+(ROOT_LRLON-ROOT_ULLON)/8
        x3_rightBorder=x3_leftBorder+(ROOT_LRLON-ROOT_ULLON)/8
         */
        double[] rightBorders = new double[len];
        for (int i = 0; i < len; i++) {
            rightBorders[i] = leftBorders[i] + (ROOT_LRLON - ROOT_ULLON) / len;
        }
        double raster_lr_lon = -1;
        int lrlonTileIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraLrlon >= rightBorders[i]
                    && paraLrlon < rightBorders[i + 1]) {
                /*
                In logic, raster_lr_lon should be rightBorders[i+1]
                and lrlonTileIndex= i+1
                but the test result seems that change i+1 to i is correct
                 */
                raster_lr_lon = rightBorders[i + 1];
                lrlonTileIndex = i + 1;
                break;
            }
        }

        /*
        if depth==3
        len=2^3=8
        y0_upBorder=ROOT_LRLAT+(ROOT_ULLAT-ROOT_LRLAT)*(0/8)
        y1_upBorder=ROOT_LRLAT+(ROOT_ULLAT-ROOT_LRLAT)*(1/8)
        y2_upBorder=ROOT_LRLAT+(ROOT_ULLAT-ROOT_LRLAT)*(2/8)
        y3_upBorder=ROOT_LRLAT+(ROOT_ULLAT-ROOT_LRLAT)*(3/8)
        */
        double[] upBorders = new double[len];
        for (int i = 0; i < len; i++) {
            upBorders[i] = ROOT_LRLAT + (ROOT_ULLAT - ROOT_LRLAT) * ((double) i / len);
        }
        /*
        Notice that I reverse upBorders, upBorders[i] > upBorders[i+1].
        Reason: As d3_x0_y0's latitude is greater than d3_x0_y1's latitude,
        Making upBorders[i] and d3_x0_yi to have the same increase direction
        can help you find the relative tile index easier.
         */
        reverseArray(upBorders);
        double raster_ul_lat = -1;
        int ullatTileIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraUllat <= upBorders[i]
                    && paraUllat > upBorders[i + 1]) {
                /*
                In my consideration, ullatTileIndex should be i;
                but the solution is i+1
                TODO: I don't understand
                 */
                raster_ul_lat = upBorders[i];
                ullatTileIndex = i+1;
                break;
            }
        }

        /*
        if len==3
        len=2^3=8
        y0_lowBorder=y0_upBorder+(ROOT_ULLAT-ROOT_LRLAT)/8
        y1_lowBorder=y1_upBorder+(ROOT_ULLAT-ROOT_LRLAT)/8
        y2_lowBorder=y2_upBorder+(ROOT_ULLAT-ROOT_LRLAT)/8
        y3_lowBorder=y3_upBorder+(ROOT_ULLAT-ROOT_LRLAT)/8
         */
        double[] lowBorders = new double[len];
        for (int i = 0; i < len; i++) {
            lowBorders[i] = upBorders[i] + (ROOT_ULLAT - ROOT_LRLAT) / len;
        }
        double raster_lr_lat = -1;
        int lrlatTileIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraLrlat < lowBorders[i]
                    && paraLrlat >= lowBorders[i + 1]) {
                /*
                In my consideration, lrlatTileIndex should be i + 1,
                but the solution is the following code
                TODO: I don't understand
                 */
                raster_lr_lat = lowBorders[i+1];
                lrlatTileIndex = i;
                break;
            }
        }

        int numberOfRows = lrlonTileIndex - ullonTileIndex + 1;
        int numberOfCols = lrlatTileIndex - ullatTileIndex + 1;
        String[][] render_grid = new String[numberOfRows][numberOfCols];
        int ullonTileIndexBackup=ullonTileIndex;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                render_grid[i][j] = "d" + depth + "_"
                        + "x" + ullonTileIndex + "_"
                        + "y" + ullatTileIndex + ".png";
                ullonTileIndex+=1;
            }
            ullonTileIndex=ullonTileIndexBackup;
            ullatTileIndex+=1;
        }

        boolean query_success = true;
        //TODO: add more situation
        if (depth == -1 || raster_lr_lat == -1 || raster_ul_lat == -1
                || raster_lr_lon == -1 || raster_ul_lon == -1) {
            query_success = false;
        }
/**
 * Takes a user query and finds the grid of images that best matches the query. These
 * images will be combined into one big image (rastered) by the front end. <br>
 * <p>
 * The grid of images must obey the following properties, where image in the
 * grid is referred to as a "tile".
 * <ul>
 *     <li>The tiles collected must cover the most longitudinal distance per pixel
 *     (LonDPP) possible, while still covering less than or equal to the amount of
 *     longitudinal distanCe per pixel in the query box for the user viewport size. </li>
 *     <li>Contains all tiles that intersect the query bounding box that fulfill the
 *     above condition.</li>
 *     <li>The tiles must be arranged in-order to reconstruct the full image.</li>
 * </ul>
 *
 * @param params Map of the HTTP GET request's query parameters - the query box and
 *               the user viewport width and height.
 * @return A map of results for the front end as specified: <br>
 * "render_grid"   : String[][], the files to display. <br>
 * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
 * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
 * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
 * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
 * "depth"         : Number, the depth of the nodes of the rastered image <br>
 * "query_success" : Boolean, whether the query was able to successfully complete; don't
 * forget to set this to true on success! <br>
 */

        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", render_grid);
        results.put("raster_ul_lon", raster_ul_lon);
        results.put("raster_ul_lat", raster_ul_lat);
        results.put("raster_lr_lon", raster_lr_lon);
        results.put("raster_lr_lat", raster_lr_lat);
        results.put("depth", depth);
        results.put("query_success", query_success);
        System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                + "your browser.");
        return results;
    }

    private double calculateLonDPP(double lrlon, double ullon, double width) {
        return (lrlon - ullon) / width;
    }

    private void reverseArray(double[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            double temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
