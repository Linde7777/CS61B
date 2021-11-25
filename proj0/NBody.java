public class NBody {
    public static double readRadius(String filePath){
        In in=new In(filePath);
        int planetsNumber=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    
    public static Planet[] readPlanets(String filePath){
        In in=new In(filePath);
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
