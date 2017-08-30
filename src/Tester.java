import java.util.ArrayList;

import processing.core.*;
import processing.event.MouseEvent;

public class Tester extends PApplet {
	
	TableLoader table;	
	TestData[] testData;
	
	int currentDataSet =0; 
	
	float rotX=0, rotY=0;
	float tempx, tempy, tempRotY, tempRotX;
	float myZoom=20;
	boolean rotate = false;
	
	int boxX=1920 , boxY =1080, boxZ =1000;

	
	public void settings() {
		fullScreen(P3D);
		smooth();
		}

  public void setup() {
    background(0);  
	lights();	
	table = new TableLoader(this);
	delay(3000);
	getTestData();
	strokeWeight(2);
  }

  public void draw() {  
	background(0);
	drawInfo();
	drawCamera();
	drawBox();
	drawDataSet(currentDataSet);
  }
  
  public void getTestData(){
	  testData =table.getTestData();
  }
  
  public void drawInfo(){
	  fill(255);
	  textSize(30);
	  text("Test: "+(currentDataSet+1)+" / "+testData.length,-width/2,-100);
  }
  
  public void drawCamera(){
	  camera(0,00,1000,0,0,0,0,1,0);	
		if (mousePressed){	
			if (rotate== false){
				tempRotX = rotX;
				tempRotY = rotY;
				tempx = mouseX;
				tempy = mouseY; 
			}
		rotate= true;
		float difx = mouseX - tempx;
		float dify = mouseY - tempy;	
		rotY = (float)(-difx*0.01)+tempRotY;
		rotX = (float)(dify*0.01)+tempRotX;	
		} else {
			rotate= false;
		}	
		rotateX(-rotX);
		rotateY(-rotY);
		scale(map(myZoom,0,100,0,5));
  }
  
  public void drawBox(){
	  stroke(0,234,58);
	  noFill();
	  box(boxX,boxY,boxZ);
	  
	  stroke(255,0,0);
	  line(0,0,0,500,0,0);
	  
	  stroke(0,255,0);
	  line(0,0,0,0,500,0);
	  
	  stroke(0,0,255);
	  line(0,0,0,0,0,500);
	  
  }
  
  public void drawDataSet(int t){
	  TestData dataToDraw= testData[t];
	  ArrayList<float[]> posData = dataToDraw.getPosData();
	  
	  for (int i=0; i<posData.size()-2;i++){	 
		  float[]p1 = posData.get(i);
		  float[]p2 = posData.get(i+1);
		  
		  if(p1[1]==0 && p2[1]!=0){
			  drawSphere(p2,255,40,30);
			  p1=new float[]{p1[0],boxX/2,2000,0};
		  } else if(p2[1]==0 && p1[1]!=0){
			  drawSphere(p1,255,40,30);
			  p2=new float[]{p2[0],boxX/2,2000,0};
		  } else {

		  
		  stroke(255,255-p1[4],255-p1[4]);
		  line(p1[1]-boxX/2,boxY/2-p1[2],-p1[3],p2[1]-boxX/2,boxY/2-p2[2],-p2[3]);
		  }
	  }
	  
	  if(posData.get(0)[1] !=0){
	  drawSphere(posData.get(0),0,255,30); //start point
	  } else {
		  drawSphere(posData.get(1),0,255,30);
	  }
	  
	  if(posData.get(posData.size()-1)[1] !=0){
		  drawSphere(posData.get(posData.size()-1),0,30,255); //start point
	  } else {
		  drawSphere(posData.get(posData.size()-2),0,30,255);
	  }
  }
  
  public void drawSphere(float[] temp,int r,int g, int b){
	  fill(r,g,b);
	  noStroke();
	  pushMatrix();
	  translate(temp[1]-boxX/2,boxY/2-temp[2],-temp[3]);
	  sphere(10);
	  popMatrix();
  }
  
  public void mouseWheel(MouseEvent event) {
	  float e = event.getCount();
	  myZoom = myZoom+e;
	}
  
  public void keyReleased(){
	  if (key == CODED) {
		    if (keyCode == DOWN) {
		    	if(currentDataSet>0){
		    		currentDataSet--;
		    	} else {
		    		currentDataSet = testData.length-1;
		    	}
		    }
		   
		    if (keyCode == UP) {
		    	if(currentDataSet<testData.length-1){
		    		currentDataSet++;
		    	} else {
		    		currentDataSet = 0;
		    	}
		    }
		    
	  }
  }


public static void main(String args[]) {
    PApplet.main(new String[] {  "Tester" });
  }

}