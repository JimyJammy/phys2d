package main.java.com.github.jimyjammy.phys2d.simplecolliders;

import main.java.com.github.jimyjammy.phys2d.locationhandle.vec2d;

public class RectangularCollider {
    private vec2d x1y1; 
    private vec2d x2y2;
    private double rot = 0;
    private float gravityStrength = 9.81f;
    private float mass = 0;
    private double v = 0;
    private double torque;
    public RectangularCollider(vec2d x1y1pos, vec2d x2y2pos, float mass){
        x1y1 = x1y1pos;
        x2y2 = x2y2pos;

        this.mass = mass; 
    }

    public RectangularCollider(vec2d x1y1pos, vec2d x2y2pos, double rota, float mass){
        this(x1y1pos,x2y2pos,mass);
        rot = rota;
    }

    public RectangularCollider(vec2d x1y1pos, vec2d x2y2pos, double rota, float gravsStreng, float mass){
        this(x1y1pos,x2y2pos,mass);
        rot = rota;
        gravityStrength = gravsStreng;
    }

    public void Update(double UpdateTime){
        //System.out.println(Double.toString(UpdateTime));
        double a=(gravityStrength*mass)/mass;
        //System.out.println(Double.toString(a));
        v=v+(a*UpdateTime);
        //System.out.println(Double.toString(v));
        if ((GetX1Y1().y > 450 || GetX1Y2().y > 450 || GetX2Y2().y > 450 || GetX2Y1().y > 450 ) && v > 0){
            v = -v;
        }
        move(new vec2d(0, v*UpdateTime));
    }

    public vec2d GetX2Y1(){
        double cx = (    x2y2.x-((x1y1.x+x2y2.x)/2)    );
        double cy = (    x1y1.y-((x1y1.y+x2y2.y)/2)    );
        double x = (cy * Math.sin(Math.toRadians(rot))) + (cx * Math.cos(Math.toRadians(rot))) + ((x1y1.x+x2y2.x)/2);
        double y = (cy * Math.cos(Math.toRadians(rot))) - (cx * Math.sin(Math.toRadians(rot))) + ((x1y1.y+x2y2.y)/2);

        vec2d l = new vec2d(x,y);
        return l;
    }

    public vec2d GetX2Y2(){
        double cx = (    x2y2.x-((x1y1.x+x2y2.x)/2)    );
        double cy = (    x1y1.y-((x1y1.y+x2y2.y)/2)    );
        double x = ((x1y1.x+x2y2.x)/2) - (cy * Math.sin(Math.toRadians(rot))) + (cx * Math.cos(Math.toRadians(rot)));
        double y = ((x1y1.y+x2y2.y)/2) - (cy * Math.cos(Math.toRadians(rot))) - (cx * Math.sin(Math.toRadians(rot)));

        vec2d l = new vec2d(x,y);
        return l;
    }

    public vec2d GetX1Y1(){
        double cx = (    x2y2.x-((x1y1.x+x2y2.x)/2)    );
        double cy = (    x1y1.y-((x1y1.y+x2y2.y)/2)    );
        double x = (cy * Math.sin(Math.toRadians(rot))) - (cx * Math.cos(Math.toRadians(rot))) + ((x1y1.x+x2y2.x)/2);
        double y = (cy * Math.cos(Math.toRadians(rot))) + (cx * Math.sin(Math.toRadians(rot))) + ((x1y1.y+x2y2.y)/2);

        vec2d l = new vec2d(x,y);
        return l;
    }

    public vec2d GetX1Y2(){
        double cx = (    x2y2.x-((x1y1.x+x2y2.x)/2)    );
        double cy = (    x1y1.y-((x1y1.y+x2y2.y)/2)    );
        double x = ((x1y1.x+x2y2.x)/2) - (cy * Math.sin(Math.toRadians(rot))) - (cx * Math.cos(Math.toRadians(rot)));
        double y = ((x1y1.y+x2y2.y)/2) - (cy * Math.cos(Math.toRadians(rot))) + (cx * Math.sin(Math.toRadians(rot)));

        vec2d l = new vec2d(x,y);
        return l;
    }

    public void SetRotation(double AmmountToSet){
        rot = AmmountToSet;
    }

    public double GetRotation(){
        return rot;
    }

    public void move(vec2d v){
        x1y1.x+=v.x;
        x2y2.x+=v.x;

        x1y1.y+=v.y;
        x2y2.y+=v.y;
    }

    public void teleport(vec2d v){
        double cx1 = (    x1y1.x-((x1y1.x+x2y2.x)/2)    );
        double cy1 = (    x1y1.y-((x1y1.y+x2y2.y)/2)    );
        double cx2 = (    x2y2.x-((x1y1.x+x2y2.x)/2)    );
        double cy2 = (    x2y2.y-((x1y1.y+x2y2.y)/2)    );

        x1y1.x=v.x+cx1;
        x2y2.x=v.x+cx2;

        x1y1.y=v.y+cy1;
        x2y2.y=v.y+cy2;
    }

    public double getCX(){
        return (x1y1.x+x2y2.x)/2;
    }

    public double getCY(){
        return (x1y1.y+x2y2.y)/2;
    }

    public float getDrag(){
        float[] xs = new float[4];
        xs[0] = (float)GetX1Y1().x;
        xs[1] = (float)GetX2Y2().x;
        xs[2] = (float)GetX1Y2().x;
        xs[3] = (float)GetX2Y1().x;
        java.util.Arrays.sort(xs);
        return Math.abs(xs[0]);
    }

    public float getF1(){
        float[] xs = new float[4];
        xs[0] = (float)GetX1Y1().x;
        xs[1] = (float)GetX2Y2().x;
        xs[2] = (float)GetX1Y2().x;
        xs[3] = (float)GetX2Y1().x;
        java.util.Arrays.sort(xs);
        return xs[0];
    }

    public float getF2(){
        float[] xs = new float[4];
        xs[0] = (float)GetX1Y1().x;
        xs[1] = (float)GetX2Y2().x;
        xs[2] = (float)GetX1Y2().x;
        xs[3] = (float)GetX2Y1().x;
        java.util.Arrays.sort(xs);
        return xs[3];
    }

    public double get
}
