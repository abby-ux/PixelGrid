package week7;
import java.awt.Color;
import java.util.*;

public class Action {
	private int prevIndex;
	private Action changedInfo;
	private ArrayList<Color> column;
	
	public Action(int index, ArrayList<Color> col) {
		this.prevIndex = index;
		this.column = new ArrayList<Color>();
		
		for (int i = 0; i < col.size(); i++) {
			this.column.add(new Color(col.get(i).getRGB()));
		}
	}
	public int getIndex() {
		return this.prevIndex;
	}
	public ArrayList<Color> getColumn() {
		return new ArrayList<Color>(this.column);
	}
	public Action changedInfo() {
		return this.changedInfo;
	}
}
