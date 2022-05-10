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

        minLon=-122.29980468
        midLon=(-122.29980468-122.21191406)/2
        maxLon=-122.21191406
        d1 x0,y0 minLon and midLon
        d1 x0,y1 midLon and maxLon
        d1 x1,yi

        d1LonDPP=cal(min,mid/1,256)
        d2LonDPP=cal(min,mid/2,256)
        */
        final double size = 256;
        final double minLon = -122.29980468;
        final double maxLat = 37.892195547244356;
        final double maxLon = -122.21191406;
        final double minLat = 37.82280243352756;

        /*
        double len1 =(maxLon-minLon)/2;
        double len2=(maxLon-minLon)/4;
        double len3=(maxLon-minLon)/8;
        double LonDPP1= len1/256;
        double LonDPP2=len2/256;
        double LonDPP3=len3/256;
         */
        int depth = -1;
        for (int i = 1; i < 7; i++) {
            double len = (maxLon - minLon) / Math.pow(2, i);
            double LonDPP = len / size;
            if (LonDPP < paraLonDPP) {
                depth = i;
                break;
            }
        }


        int len = (int) Math.sqrt(Math.pow(4, depth));
        //TODO: seal calculating border into method

        /*
        if depth==4
        x0_leftBorder=minLon+(maxLon-minLon)*(0/4)
        x1_leftBorder=minLon+(maxLon-minLon)*(1/4)
        x2_leftBorder=minLon+(maxLon-minLon)*(2/4)
        x3_leftBorder=minLon+(maxLon-minLon)*(3/4)
        */
        double[] leftBorders = new double[len];
        for (int i = 0; i < len; i++) {
            leftBorders[i] = minLon + (maxLon - minLon) * ((double) i / 4);
        }
        double raster_ul_lon = -1;
        for (int i = 0; i < leftBorders.length - 1; i++) {
            if (paraUllon >= leftBorders[i]
                    && paraUllon < leftBorders[i + 1]) {
                raster_ul_lon = leftBorders[i];
                break;
            }
        }

        /*
        x0_rightBorder=x0_leftBorder+(maxLon-minLon)/4
        x1_rightBorder=x1_leftBorder+(maxLon-minLon)/4
        x2_rightBorder=x2_leftBorder+(maxLon-minLon)/4
        x3_rightBorder=x3_leftBorder+(maxLon-minLon)/4
         */
        double[] rightBorders = new double[len];
        for (int i = 0; i < len; i++) {
            rightBorders[i] = leftBorders[i] + (maxLon - minLon) / 4;
        }
        double raster_lr_lon = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraLrlon >= rightBorders[i] && paraLrlon < rightBorders[i + 1]) {
                raster_lr_lon = rightBorders[i + 1];
                break;
            }
        }

        /*
        if depth==4
        y0_upBorder=minLat+(maxLat-minLat)*(0/4)
        y1_upBorder=minLat+(maxLat-minLat)*(1/4)
        y2_upBorder=minLat+(maxLat-minLat)*(2/4)
        y3_upBorder=minLat+(maxLat-minLat)*(3/4)
        */
        double[] upBorders = new double[len];
        for (int i = 0; i < len; i++) {
            upBorders[i] = minLat + (maxLat - minLat) * ((double) (i) / 4);
        }
        double raster_ul_lat = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraUllat >= upBorders[i] && paraUllat < upBorders[i + 1]) {
                raster_ul_lat = upBorders[i];
                break;
            }
        }

        /*
        y0_lowBorder=y0_upBorder+(maxLat-minLat)/4
        y1_lowBorder=y1_upBorder+(maxLat-minLat)/4
        y2_lowBorder=y2_upBorder+(maxLat-minLat)/4
        y3_lowBorder=y3_upBorder+(maxLat-minLat)/4
         */
        double[] lowBorders = new double[len];
        for (int i = 0; i < len; i++) {
            lowBorders[i] = upBorders[i] + (maxLat - minLat) / 4;
        }
        double raster_lr_lat = -1;
        for (int i = 0; i < len - 1; i++) {
            if (paraLrlat >= lowBorders[i] && paraLrlat < lowBorders[i + 1]) {
                raster_lr_lat = lowBorders[i + 1];
                break;
            }
        }

        /*
        leftBorderIndex=0
        rightBorderIndex=2
        upBorderIndex=0
        lowBorderIndex=2
         */

        int numberOfRows = (int) (raster_ul_lat - raster_lr_lat + 1);
        int numberOfCols = (int) (raster_lr_lon - raster_ul_lon + 1);
        String[][] render_grid = new String[numberOfRows][numberOfCols];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                render_grid[i][j] = "d" + depth + "_" + "x" + j + "_" + "y" + i + "_" + ".png";
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
