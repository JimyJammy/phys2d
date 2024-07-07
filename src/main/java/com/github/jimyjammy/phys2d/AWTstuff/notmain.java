package main.java.com.github.jimyjammy.phys2d.AWTstuff;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.github.jimyjammy.phys2d.simplecolliders.RectangularCollider;

public class notmain extends Frame{
    private RectangularCollider r;
    private double time = 0;

	public static void main(String[] args) {
        Frame f = new notmain();
        f.setSize(500,500);
        f.setVisible(true);
    }

    public void windowClosing(){
        System.exit(0);
    }

    public notmain(){
        time=java.time.LocalTime.now().getSecond()+(java.time.LocalTime.now().getNano()/1000000);
        this.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
           dispose();
           }  
        });
        r = new RectangularCollider(100, 100, 200, 200, 0.1f);
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){
        double temtime=java.time.LocalTime.now().getSecond()+(java.time.LocalTime.now().getNano()/1000000);
        r.Update(0.005);
        if (time == 0 ){

        }
        time = temtime;
        g.clearRect(0,0,500,500);
        g.drawLine(
            (int)Math.round((r.GetX1Y1()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX1Y1()[1])-r.getCY()+225), 
            (int)Math.round((r.GetX1Y2()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX1Y2()[1])-r.getCY()+225));


        g.drawLine(
            (int)Math.round((r.GetX1Y2()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX1Y2()[1])-r.getCY()+225), 
            (int)Math.round((r.GetX2Y2()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX2Y2()[1])-r.getCY()+225));
        
        g.drawLine(
            (int)Math.round((r.GetX2Y2()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX2Y2()[1])-r.getCY()+225), 
            (int)Math.round((r.GetX2Y1()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX2Y1()[1])-r.getCY()+225));

        g.drawLine(
            (int)Math.round((r.GetX2Y1()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX2Y1()[1])-r.getCY()+225), 
            (int)Math.round((r.GetX1Y1()[0])-r.getCX()+250), 
            (int)Math.round((r.GetX1Y1()[1])-r.getCY()+225));

        g.drawLine(0, 675 - (int)Math.round(r.getCY()), 500, 675-(int)Math.round(r.getCY()));
        
        //System.out.println(Double.toString(r.GetX1Y1()[0]) + "," + Double.toString(r.GetX1Y1()[1]) + " " + Double.toString(r.GetX1Y2()[0]) + "," + Double.toString(r.GetX1Y2()[1]) + " " + Double.toString(r.GetX2Y1()[0]) + "," + Double.toString(r.GetX2Y1()[0]) + " " + Double.toString(r.GetX2Y2()[0]) + "," + Double.toString(r.GetX2Y2()[1]));
        repaint();
    }
}
