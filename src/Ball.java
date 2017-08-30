
import processing.core.PApplet;


public class Ball {
	
	PApplet p;
	int x,y,z; 
	int spacing=80;
	
	Ball(PApplet p,int i, int j,int k){
		this.p = p;
		x=i*spacing;
		y=j*spacing;
		z=k*spacing;
	}
	
	void display(){
		p.pushMatrix();
		p.translate(x, y, z);
		p.noStroke();
		p.fill((int)p.random(255),(int)p.random(255),(int)p.random(255));
		p.sphere(40);
		p.popMatrix();
		
	}

}
