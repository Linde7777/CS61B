import java.util.Arrays;
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos,
    double xxVel, double yyVel, 
    double mass, String imgFileName) {
        this.xxPos=xxPos;
        this.yyPos=yyPos;
        this.xxVel=xxVel;
        this.yyVel=yyVel;
        this.mass=mass;
        this.imgFileName=imgFileName;
    }

    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet aPlanet){
        double distance;
        distance=Math.sqrt(
        (this.xxPos-aPlanet.xxPos)*
        (this.xxPos-aPlanet.xxPos)+
        (this.yyPos-aPlanet.yyPos)*
        (this.yyPos-aPlanet.yyPos));
        return distance;
    }

    public double calcForceExertedBy(Planet aPlanet){
        final double G=6.67e-11;
        double totalForce=0;
        totalForce=(G * this.mass * aPlanet.mass)/
        ( calcDistance(aPlanet) * calcDistance(aPlanet) );
        return totalForce;
    }

    public double calcForceExertedByX(Planet aPlanet){
        double forceByX=0;
        forceByX=( (aPlanet.xxPos-this.xxPos) / calcDistance(aPlanet) )
        *calcForceExertedBy(aPlanet);
        return forceByX;
    }

    public double calcForceExertedByY(Planet aPlanet){
        double forceByY=0;
        forceByY = ((aPlanet.yyPos-this.yyPos) / calcDistance(aPlanet))
        *calcForceExertedBy(aPlanet);
        return forceByY;
    }

    public double calcNetForceExertedByX(Planet[] arr){
        double netForceByX=0;
        for(int i=0;i<arr.length;i++){
            if(this.equals(arr[i])){
                continue;
            }
            else{
                netForceByX += calcForceExertedByX(arr[i]);
            }
        }

        return netForceByX;
    }

    public double calcNetForceExertedByY(Planet[] arr){
        double netForceByY=0;
        for(int i=0;i<arr.length;i++){
            if(this.equals(arr[i])){
                continue;
            }
            else{
                netForceByY += calcForceExertedByY(arr[i]);
            }
        }

        return netForceByY;
    }

    public void update(double dt,double fx,double fy){
        double ax=fx/this.mass;
        double ay=fy/this.mass;
        this.xxVel+=ax*dt;
        this.yyVel+=ay*dt;
        this.xxPos+=this.xxVel*dt;
        this.yyPos+=this.yyVel*dt;
    }

    public void draw(){
        //StdDraw.setScale(-100, 100);
        StdDraw.picture(this.xxPos,this.yyPos,imgFileName);
        StdDraw.show();
 //       StdDraw.pause(200);
    }
}
