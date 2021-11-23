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
        Planet planet=new Planet(p.xxPos,
        p.yyPos,p.xxVel,p.yyVel,p.mass,p.imgFileName);
    }

}
