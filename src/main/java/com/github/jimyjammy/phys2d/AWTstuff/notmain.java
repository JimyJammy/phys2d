package main.java.com.github.jimyjammy.phys2d.AWTstuff;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.java.com.github.jimyjammy.phys2d.simplecolliders.RectangularCollider;
import main.java.com.github.jimyjammy.phys2d.locationhandle.vec2d;

public class notmain extends Frame{
    private RectangularCollider r;
    private double time = 0;
    private double turning = 0;

  public static void main(String[] args) {
        Frame f = new notmain();
        f.setSize(500,500);
        f.setVisible(true);
    }

    public void windowClosing(){
        System.exit(0);
    }

    public notmain(){
        time=((double)System.nanoTime())/100000000;
        this.addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
           dispose();
           }  
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_D) {  
                    turning = 1;  
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {  
                    turning = -1;  
                }
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {  
                    turning = 0;  
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {  
                    turning = 0;  
                }
                
            }
        });

        r = new RectangularCollider(new vec2d(100,100), new vec2d(250,250), 10f);
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){
        double temtime = ((double)System.nanoTime())/100000000;
        r.Update(temtime-time);
        r.SetRotation(r.GetRotation() + ((temtime-time)*turning*4));
        time = temtime;
        if (time == 0 ){

        }
        time = temtime;
        g.clearRect(0,0,500,500);
        g.drawLine(
            (int)Math.round((r.GetX1Y1().x)-r.getCX()+250), 
            (int)Math.round((r.GetX1Y1().y)-r.getCY()+225), 
            (int)Math.round((r.GetX1Y2().x)-r.getCX()+250), 
            (int)Math.round((r.GetX1Y2().y)-r.getCY()+225));


        g.drawLine(
            (int)Math.round((r.GetX1Y2().x)-r.getCX()+250), 
            (int)Math.round((r.GetX1Y2().y)-r.getCY()+225), 
            (int)Math.round((r.GetX2Y2().x)-r.getCX()+250), 
            (int)Math.round((r.GetX2Y2().y)-r.getCY()+225));

        g.drawLine(
            (int)Math.round((r.GetX2Y2().x)-r.getCX()+250), 
            (int)Math.round((r.GetX2Y2().y)-r.getCY()+225), 
            (int)Math.round((r.GetX2Y1().x)-r.getCX()+250), 
            (int)Math.round((r.GetX2Y1().y)-r.getCY()+225));

        g.drawLine(
            (int)Math.round((r.GetX2Y1().x)-r.getCX()+250), 
            (int)Math.round((r.GetX2Y1().y)-r.getCY()+225), 
            (int)Math.round((r.GetX1Y1().x)-r.getCX()+250), 
            (int)Math.round((r.GetX1Y1().y)-r.getCY()+225));

        g.drawLine((int)Math.round(r.getF1()-r.getCX()+250), 250, (int)Math.round(r.getF2()-r.getCX()+250), 250);

        g.drawLine(0, 675 - (int)Math.round(r.getCY()), 500, 675-(int)Math.round(r.getCY()));

        //System.out.println(Double.toString(r.GetX1Y1().x) + "," + Double.toString(r.GetX1Y1().y) + " " + Double.toString(r.GetX1Y2().x) + "," + Double.toString(r.GetX1Y2().y) + " " + Double.toString(r.GetX2Y1().x) + "," + Double.toString(r.GetX2Y1().x) + " " + Double.toString(r.GetX2Y2().x) + "," + Double.toString(r.GetX2Y2().y));
        repaint();
    }
}
/*#define NUM_RIGID_BODIES 1

// 2D box shape. Physics engines usually have a couple different classes of shapes
// such as circles, spheres (3D), cylinders, capsules, polygons, polyhedrons (3D)...
typedef struct {
    float width;
    float height;
    float mass;
    float momentOfInertia;
} BoxShape;

// Calculates the inertia of a box shape and stores it in the momentOfInertia variable.
void CalculateBoxInertia(BoxShape *boxShape) {
    float m = boxShape->mass;
    float w = boxShape->width;
    float h = boxShape->height;
    boxShape->momentOfInertia = m * (w * w + h * h) / 12;
}

// Two dimensional rigid body
typedef struct {
    Vector2 position;
    Vector2 linearVelocity;
    float angle;
    float angularVelocity;
    Vector2 force;
    float torque;
    BoxShape shape;
} RigidBody;

// Global array of rigid bodies.
RigidBody rigidBodies[NUM_RIGID_BODIES];

// Prints the position and angle of each body on the output.
// We could instead draw them on screen.
void PrintRigidBodies() {
    for (int i = 0; i < NUM_RIGID_BODIES; ++i) {
        RigidBody *rigidBody = &rigidBodies[i];
        printf("body[%i] p = (%.2f, %.2f), a = %.2f\n", i, rigidBody->position.x, rigidBody->position.y, rigidBody->angle);
    }
}

// Initializes rigid bodies with random positions and angles and zero linear and angular velocities.
// They're all initialized with a box shape of random dimensions.
void InitializeRigidBodies() {
    for (int i = 0; i < NUM_RIGID_BODIES; ++i) {
        RigidBody *rigidBody = &rigidBodies[i];
        rigidBody->position = (Vector2){arc4random_uniform(50), arc4random_uniform(50)};
        rigidBody->angle = arc4random_uniform(360)/360.f * M_PI * 2;
        rigidBody->linearVelocity = (Vector2){0, 0};
        rigidBody->angularVelocity = 0;

        BoxShape shape;
        shape.mass = 10;
        shape.width = 1 + arc4random_uniform(2);
        shape.height = 1 + arc4random_uniform(2);
        CalculateBoxInertia(&shape);
        rigidBody->shape = shape;
    }
}

// Applies a force at a point in the body, inducing some torque.
void ComputeForceAndTorque(RigidBody *rigidBody) {
    Vector2 f = (Vector2){0, 100};
    rigidBody->force = f;
    // r is the 'arm vector' that goes from the center of mass to the point of force application
    Vector2 r = (Vector2){rigidBody->shape.width / 2, rigidBody->shape.height / 2};
    rigidBody->torque = r.x * f.y - r.y * f.x;
}

void RunRigidBodySimulation() {
    float totalSimulationTime = 10; // The simulation will run for 10 seconds.
    float currentTime = 0; // This accumulates the time that has passed.
    float dt = 1; // Each step will take one second.

    InitializeRigidBodies();
    PrintRigidBodies();

    while (currentTime < totalSimulationTime) {
        sleep(dt);

        for (int i = 0; i < NUM_RIGID_BODIES; ++i) {
            RigidBody *rigidBody = &rigidBodies[i];
            ComputeForceAndTorque(rigidBody);
            Vector2 linearAcceleration = (Vector2){rigidBody->force.x / rigidBody->shape.mass, rigidBody->force.y / rigidBody->shape.mass};
            rigidBody->linearVelocity.x += linearAcceleration.x * dt;
            rigidBody->linearVelocity.y += linearAcceleration.y * dt;
            rigidBody->position.x += rigidBody->linearVelocity.x * dt;
            rigidBody->position.y += rigidBody->linearVelocity.y * dt;
            float angularAcceleration = rigidBody->torque / rigidBody->shape.momentOfInertia;
            rigidBody->angularVelocity += angularAcceleration * dt;
            rigidBody->angle += rigidBody->angularVelocity * dt;
        }

        PrintRigidBodies();
        currentTime += dt;
    }
}
*/