package au.uq.dke.comonviz.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.graph.arc.DefaultGraphArc;
import ca.uvic.cs.chisel.cajun.graph.GraphItem;
import ca.uvic.cs.chisel.cajun.graph.GraphModel;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.ontology.OntologyRelationship;

public class HierarchalArcFilter implements GraphFilter {

	private boolean isHierarchalArcVisibal = true;

	public boolean isArcFilter() {
		return true;
	}

	public boolean isNodeFilter() {
		return false;
	}

	public boolean isVisible(GraphItem item) {
		return isArcVisible(item);
	}

	public boolean isArcVisible(Object arc) {
		OntologyRelationship rel = (OntologyRelationship) ((DefaultGraphArc) arc)
				.getUserObject();
		

		GraphNode srcNode = EntryPoint.getGraphModel().getSourceNode(rel);
		GraphNode dstNode = EntryPoint.getGraphModel().getDestinationNode(rel);

		if (EntryPoint.getGraphModel().isParentChildRelation(srcNode, dstNode) == true) {
			return isHierarchalArcVisibal;
		}

		// visible by default
		return true;
	}

	public boolean isHierarchalArcVisibal() {
		return isHierarchalArcVisibal;
	}

	public void setHierarchalArcVisibal(boolean interBranchArcVisibility) {
		this.isHierarchalArcVisibal = interBranchArcVisibility;
	}
}
