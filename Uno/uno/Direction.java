package uno;

public class Direction {
	
	private int dir;
	//0 will mean going forward in array and 1 will mean going backwards
	
	public Direction () {
		this.dir = 0;
	}
	
	
	public int getDir() {
		return dir;
	}


	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public void reverse (Direction d) {
		if (d.getDir()==0) {
			d.setDir(1);
		}
		else {
			d.setDir(0);
		}
	}


}
