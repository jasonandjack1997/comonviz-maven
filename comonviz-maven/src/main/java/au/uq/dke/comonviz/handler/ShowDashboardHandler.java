package au.uq.dke.comonviz.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import au.uq.dke.comonviz.graph.node.DashboardIconNode;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

public class ShowDashboardHandler extends PBasicInputEventHandler{

	@Override
	public void mouseClicked(PInputEvent event) {
		PNode clickedNode = event.getPickedNode();
		if(clickedNode instanceof DashboardIconNode){
			DefaultGraphNode parent = (DefaultGraphNode)clickedNode.getParent();
			Class recordType = parent.getRecordType();
			Map<Class, Integer> actualRecordTypes = new HashMap<Class, Integer>();
			List records = DatabaseUtils.findAll(recordType);
			for(Object record : records){
				if(actualRecordTypes.containsKey(record.getClass())){
					Integer count = actualRecordTypes.get(record.getClass()) + 1;
					actualRecordTypes.put(record.getClass(), count);
				}else{
					actualRecordTypes.put(record.getClass(), 1);
					
				}
			}
			return;
		}
	}

}
