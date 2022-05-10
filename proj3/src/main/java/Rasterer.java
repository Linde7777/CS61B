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

        /*
        d0_x0_y0 longitudes -122.29980468 and -122.21191406
        latitudes 37.82280243 and 37.89219554.

        ROOT_ULLON=-122.29980468
        midLon=(-122.29980468-122.21191406)/2
        ROOT_LRLON=-122.21191406
        d1 x0,y0 ROOT_ULLON and midLon
        d1 x0,y1 midLon and ROOT_LRLON
        d1 x1,yi

        d1LonDPP=cal(min,mid/1,256)
        d2LonDPP=cal(min,mid/2,256)
        */
        /*
        public static final double
        ROOT_ULLAT = 37.892195547244356,
        ROOT_ULLON = -122.2998046875,
        ROOT_LRLAT = 37.82280243352756,
        ROOT_LRLON = -122.2119140625;
         */
        final double size = 256;
        final double ROOT_ULLON = -122.2998046875;
        final double ROOT_ULLAT = 37.892195547244356;
        final double ROOT_LRLON = -122.2119140625;
        final double ROOT_LRLAT = 37.82280243352756;

        /*
        double singleTileLen1 =(ROOT_LRLON-ROOT_ULLON)/2;
        double singleTileLen2=(ROOT_LRLON-ROOT_ULLON)/4;
        double singleTileLen3=(ROOT_LRLON-ROOT_ULLON)/8;
        double LonDPP1=singleTileLen1/256;
        double LonDPP2=singleTileLen2/256;
        double LonDPP3=singleTileLen3/256;
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
        double raster_ul_lat = -1;
        int ullatTileIndex = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraUllat <= upBorders[i + 1]
                    && paraUllat > upBorders[i]) {
                raster_ul_lat = upBorders[i + 1];
                ullatTileIndex = i + 1;
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
            if (paraLrlat <= lowBorders[i + 1]
                    && paraLrlat > lowBorders[i]) {
                raster_lr_lat = lowBorders[i];
                lrlatTileIndex = i;
                break;
            }
        }

        /*
        leftBorderIndex=0
        rightBorderIndex=2
        upBorderIndex=0
        lowBorderIndex=2
         */

        /*
        ullonIndex,ullatIndex
        10           12

                           lrlonIndex,lrlatIndex
                           14         8???
        */
        int numberOfRows = lrlonTileIndex - ullonTileIndex + 1;
        int numberOfCols = ullatTileIndex - lrlatTileIndex + 1;
        String[][] render_grid = new String[numberOfRows][numberOfCols];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                render_grid[i][j] = "d" + depth + "_" + "x" + j + "_" + "y" + i + ".png";
            }
        }

        boolean query_success = true;
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

}
