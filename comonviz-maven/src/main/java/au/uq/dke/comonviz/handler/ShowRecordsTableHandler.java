package au.uq.dke.comonviz.handler;

import au.uq.dke.comonviz.graph.node.BasicNodeIcon;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.graph.node.RecordsTableIcon;
import au.uq.dke.comonviz.ui.data.dialog.BasicRecordsInfoDialog;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class ShowRecordsTableHandler extends PBasicInputEventHandler{

	@Override
	public void mouseClicked(PInputEvent event) {
		PNode clickedNode = event.getPickedNode();
		if(clickedNode instanceof RecordsTableIcon){
			DefaultGraphNode parent = (DefaultGraphNode)clickedNode.getParent();
			Class recordType = parent.getRecordType();
			if(recordType != null){
				new BasicRecordsInfoDialog(recordType);
			}
			
		}
		super.mouseClicked(event);
	}

}
