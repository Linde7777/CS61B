import java.util.function.DoubleUnaryOperator;

public class NBody {
    public static void main(String[] args) {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius = readRadius(filename);
        Planet[] aPlanets = readPlanets(filename);
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
