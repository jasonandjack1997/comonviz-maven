package au.uq.dke.comonviz.graph.node;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;

import edu.umd.cs.piccolo.util.PPaintContext;

public class RecordsTableIcon extends BasicNodeIcon{
	
	public Class getRecordType(){
		DefaultGraphNode parentNode = (DefaultGraphNode) this.getParent();
		Class recordType = parentNode.getRecordType();
		return recordType;
	}
	
	
	@Override
	protected void paint(PPaintContext paintContext) {
		
		if(this.getRecordType() != null){

			Graphics2D g2 = paintContext.getGraphics();

		Paint bg = Color.BLUE;
		g2.setPaint(bg);
		g2.fill(this.getBounds());

		super.paint(paintContext);
		}
	}


}
