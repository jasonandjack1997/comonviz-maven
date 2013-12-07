package au.uq.dke.comonviz.ui.ontology.filterPanel;

import java.util.Map;

import javax.swing.Icon;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.ontology.FilterPanel;
import ca.uvic.cs.chisel.cajun.graph.GraphItemStyle;

public class NodeLevelFilterPanel extends FilterPanel {

	private static final long serialVersionUID = -1656466039034202473L;

	public NodeLevelFilterPanel(String title, Icon icon, GraphItemStyle style) {
		super(title, icon, style, true);
		// TODO Auto-generated constructor stub
	}

	public void setTypeVisibility(Object nodeLevel, boolean visible) {
		EntryPoint.getFilterManager().getNodeLevelFilter()
				.setNodeLevelVisible(nodeLevel, visible);
		EntryPoint.getFlatGraph().performLayout();
		//EntryPoint.getTopView().getTreeModel();
	}

	public Map<Object, Boolean> getTypes() {
		return EntryPoint.getFilterManager().getNodeLevelFilter()
				.getNodeLevelsMap();
	}

}
