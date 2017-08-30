import java.util.ArrayList;

public class TestData {
	
	int[] testPerameters = new int[3]; //a,b,c
	ArrayList<float[]> posData = new ArrayList<float[]>();
	
	TestData(int[] perams){
		this.testPerameters = perams;
		System.out.println("\nPerams: "+perams[0]+", "+perams[1]+", "+perams[2]);
		
	}
	
	public void addPosData(float[] temp){ //temp(x,y,z)
		posData.add(temp);
		System.out.println("Time: "+ temp[0]+" X: "+temp[1]+" Y:"+temp[2]+" Z:"+temp[3]+" PWM:"+temp[4]);
	}
	
	public ArrayList<float[]> getPosData(){
		return posData;
	}

}
