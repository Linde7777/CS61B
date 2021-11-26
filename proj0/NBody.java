import java.util.function.DoubleUnaryOperator;

public class NBody {
    public static void main(String[] args) {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        //StdDraw.setCanvasSize(1000,1000);
        StdDraw.setScale(-radius, radius);
        String starfieldPath="images/starfield.jpg";
        StdDraw.picture(0,0,starfieldPath);
        StdDraw.show();

        int planetsNumber=allPlanets.length;
        for(int i=0;i<planetsNumber;i++){
            allPlanets[i].draw();
        }


        StdDraw.enableDoubleBuffering();
        int time=0;
        for(time=0;time<T;time+=dt){
            //may have problems, planetsNumber or the number of forces?
            for(int i=0;i<planetsNumber;i++){
                double[] xForces = new double[planetsNumber];
                double[] yForces = new double[planetsNumber];
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
                allPlanets[i].update(dt, xForces[i], yForces[i]);

            }

            StdDraw.picture(0, 0, starfieldPath);
            for (int i = 0; i < planetsNumber; i++) {
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

    }

    public static double readRadius(String filename){
        In in=new In(filename);
        int planetsNumber=in.readInt();
        double radius=in.readDouble();

        return radius;
    }
    
    public static Planet[] readPlanets(String filename){
        In in=new In(filename);
        int planetsNumber=in.readInt();
        double radius=in.readDouble();
        Planet[] allPlanets=new Planet[planetsNumber];
        
        for(int i=0;i<planetsNumber;i++){
        allPlanets[i]=new Planet(in.readDouble(),in.readDouble(),
        in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }

        return allPlanets;
    }
}
