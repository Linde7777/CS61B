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
        double minLon = -122.29980468;
        double maxLon = -122.21191406;
        double midLon = (minLon + maxLon) / 2;
        //double depth1LonDPP = calculateLonDPP(minLon, midLon, size);
        //double depth2LonDPP = calculateLonDPP(minLon, midLon / 2, size);

        int depth;
        double currentDepthLonDPP;
        //The largest depth is 7
        for (depth = 1; depth <= 7; depth++) {
            currentDepthLonDPP = calculateLonDPP(minLon, midLon / depth, size);
            if (currentDepthLonDPP <= paraLonDPP) {
                break;
            }
        }

        /*
        int k = (int) Math.pow(2, depth) - 1;
        String[][] array = new String[k][k + 1];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                int xIndex = j;
                int yIndex = i + 1;
                array[i][j] = "d" + depth + "_" + "x" + xIndex + "_" + "y" + yIndex + "_" + ".png";
                System.out.println(array[i][j]);
            }
        }

         */

        /*
        if depth==4
        x0_leftBorder=minLon+(maxLon-minLon)*(0/4)
        x1_leftBorder=minLon+(maxLon-minLon)*(1/4)
        x2_leftBorder=minLon+(maxLon-minLon)*(2/4)
        x3_leftBorder=minLon+(maxLon-minLon)*(3/4)
        */
        int len = (int) Math.sqrt(Math.pow(4, depth));

        double[] leftBorders = new double[len];
        for (int i = 0; i < len; i++) {
            leftBorders[i] = minLon + (maxLon - minLon) * ((double) i / 4);
        }
        int theLeftBorderIndex=-1;
        for (int i = 0; i < leftBorders.length - 1; i++) {
            if (paraUllon >= leftBorders[i]
                    && paraUllon < leftBorders[i + 1]) {
                theLeftBorderIndex=i;
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
            rightBorders[i] =leftBorders[i]+(maxLon-minLon)/4;
        }
        double theRightBorderIndex=-1;
        for(int i=0;i<len-1;i++){
            if(paraLrlon>=rightBorders[i]&&paraLrlon<rightBorders[i+1]){
                theRightBorderIndex=i;
                break;
            }
        }

        Map<String, Object> results = new HashMap<>();
        System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
                + "your browser.");
        return results;
    }

    private double calculateLonDPP(double lrlon, double ullon, double width) {
        return (lrlon - ullon) / width;
    }

}
