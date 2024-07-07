package main.java.com.github.jimyjammy.phys2d.simplecolliders;

public class RectangularCollider {
    public double x1; 
    public double x2;
    public double y1;
    public double y2;
    public double rot = 0;
    public double gravityStrength = 0.97;
    private float mass = 0;
    private double v = 0;
    public RectangularCollider(double x_1, double y_1, double x_2, double y_2, float mass){
        x1 = x_1;
        x2 = x_2;

        y1 = y_1;
        y2 = y_2;
        this.mass = mass; 
    }

    public RectangularCollider(double x_1, double y_1, double x_2, double y_2, double rota, float mass){
        this(x_1,y_1,x_2,y_2,mass);
        rot = rota;
    }

    public RectangularCollider(double x_1, double y_1, double x_2, double y_2, double rota, double gravsStreng, float mass){
        this(x_1,y_1,x_2,y_2,mass);
        rot = rota;
        gravityStrength = gravsStreng;
    }

    public void Update(double UpdateTime){
        System.out.println(Double.toString(UpdateTime));
        double a=(gravityStrength*mass*mass)/mass;
        //System.out.println(Double.toString(a));
        v=v+(a*UpdateTime);
        //System.out.println(Double.toString(v));
        if (GetX1Y1()[1] > 450 || GetX1Y2()[1] > 450 || GetX2Y2()[1] > 450 || GetX2Y1()[1] > 450){
            v = -v;
        }
        move(0, v*UpdateTime);
    }

    public double[] GetX2Y1(){
        double cx = (    x2-((x1+x2)/2)    );
        double cy = (    y1-((y1+y2)/2)    );
        double x = (cy * Math.sin(Math.toRadians(rot))) + (cx * Math.cos(Math.toRadians(rot))) + ((x1+x2)/2);
        double y = (cy * Math.cos(Math.toRadians(rot))) - (cx * Math.sin(Math.toRadians(rot))) + ((y1+y2)/2);
        
        
        double[] l = new double[2];
        l[0] = x;
        l[1] = y;
        return l;
    }

    public double[] GetX2Y2(){
        double cx = (    x2-((x1+x2)/2)    );
        double cy = (    y1-((y1+y2)/2)    );
        double x = ((x1+x2)/2) - (cy * Math.sin(Math.toRadians(rot))) + (cx * Math.cos(Math.toRadians(rot)));
        double y = ((y1+y2)/2) - (cy * Math.cos(Math.toRadians(rot))) - (cx * Math.sin(Math.toRadians(rot)));

        double[] l = new double[2];
        l[0] = x;
        l[1] = y;
        return l;
    }

    public double[] GetX1Y1(){
        double cx = (    x2-((x1+x2)/2)    );
        double cy = (    y1-((y1+y2)/2)    );
        double x = (cy * Math.sin(Math.toRadians(rot))) - (cx * Math.cos(Math.toRadians(rot))) + ((x1+x2)/2);
        double y = (cy * Math.cos(Math.toRadians(rot))) + (cx * Math.sin(Math.toRadians(rot))) + ((y1+y2)/2);

        double[] l = new double[2];
        l[0] = x;
        l[1] = y;
        return l;
    }

    public double[] GetX1Y2(){
        double cx = (    x2-((x1+x2)/2)    );
        double cy = (    y1-((y1+y2)/2)    );
        double x = ((x1+x2)/2) - (cy * Math.sin(Math.toRadians(rot))) - (cx * Math.cos(Math.toRadians(rot)));
        double y = ((y1+y2)/2) - (cy * Math.cos(Math.toRadians(rot))) + (cx * Math.sin(Math.toRadians(rot)));
        
        double[] l = new double[2];
        l[0] = x;
        l[1] = y;
        return l;
    }

    public void SetRotation(double AmmountToSet){
        rot = AmmountToSet;
    }

    public double GetRotation(){
        return rot;
    }

    public void move(double x, double y){
        x1+=x;
        x2+=x;

        y1+=y;
        y2+=y;
    }

    public void teleport(double x, double y){
        double cx1 = (    x1-((x1+x2)/2)    );
        double cy1 = (    y1-((y1+y2)/2)    );
        double cx2 = (    x2-((x1+x2)/2)    );
        double cy2 = (    y2-((y1+y2)/2)    );
        
        x1=x+cx1;
        x2=x+cx2;

        y1=y+cy1;
        y2=y+cy2;
    }

    public double getCX(){
        return (x1+x2)/2;
    }

    public double getCY(){
        return (y1+y2)/2;
    }
}
