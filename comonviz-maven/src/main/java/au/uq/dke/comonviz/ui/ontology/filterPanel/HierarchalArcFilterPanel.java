package au.uq.dke.comonviz.ui.ontology.filterPanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.ontology.FilterPanel;
import ca.uvic.cs.chisel.cajun.graph.GraphItemStyle;

public class HierarchalArcFilterPanel extends FilterPanel {

	private static final long serialVersionUID = -1656466039034202473L;

	public HierarchalArcFilterPanel(String title, Icon icon, GraphItemStyle style) {
		super(title, icon, style, false);
	}

	public void setTypeVisibility(Object nodeBranch, boolean visible) {
		EntryPoint.getFilterManager().getHierarchalArcFilter().setHierarchalArcVisibal(visible);
		EntryPoint.getFlatGraph().performLayout();
	}

	public Map<Object, Boolean> getTypes() {
		Map interBranchArcFilterMap = new HashMap();
		interBranchArcFilterMap.put("hierarchal relationships", EntryPoint.getFilterManager().getHierarchalArcFilter().isHierarchalArcVisibal());
		return interBranchArcFilterMap;
	}

}
