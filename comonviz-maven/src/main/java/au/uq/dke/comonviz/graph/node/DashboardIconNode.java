package au.uq.dke.comonviz.graph.node;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;

import javax.swing.ImageIcon;

import edu.umd.cs.piccolo.util.PPaintContext;

public class DashboardIconNode extends BasicIconNode{
	
//	private final static String defaultIconFilePath = null;
	private final static String defaultIconFilePath = "F:/Git/comonviz-maven/comonviz-maven/src/main/resources/dashboard.png";
	
	public DashboardIconNode(String fileName) {
		super(fileName);
	}

	public DashboardIconNode() {
		super();
		ImageIcon icon = new ImageIcon(RecordsTableIconNode.class.getResource("/dashboard.png"));
		Image iconImage = icon.getImage();
		super.setImage(iconImage);
	}


	public Class getRecordType(){
		DefaultGraphNode parentNode = (DefaultGraphNode) this.getParent();
		Class recordType = parentNode.getRecordType();
		return recordType;
	}
	
}
