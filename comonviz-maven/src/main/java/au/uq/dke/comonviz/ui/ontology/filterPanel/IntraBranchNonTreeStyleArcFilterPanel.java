package au.uq.dke.comonviz.ui.ontology.filterPanel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.ontology.FilterPanel;
import ca.uvic.cs.chisel.cajun.graph.GraphItemStyle;

public class IntraBranchNonTreeStyleArcFilterPanel extends FilterPanel {

	private static final long serialVersionUID = -1656466039034202473L;

	public IntraBranchNonTreeStyleArcFilterPanel(String title, Icon icon, GraphItemStyle style) {
		super(title, icon, style);
	}

	public void setTypeVisibility(Object nodeBranch, boolean visible) {
		EntryPoint.getFilterManager().getInterBranchArcFilter().setInterBranchArcVisibal(visible);
		EntryPoint.getFlatGraph().performLayout();
	}

	public Map<Object, Boolean> getTypes() {
		Map interBranchArcFilterMap = new HashMap();
		interBranchArcFilterMap.put("intra-branch cross-tree relationships", EntryPoint.getFilterManager().getIntraBranchNonTreeStyleArcFilter().isIntraBranchNonTreeStyleArcVisibal());
		return interBranchArcFilterMap;
	}

}
