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

public class IntraBranchNonTreeStyleArcFilter implements GraphFilter {

	private boolean isIntraBranchNonTreeStyleArcVisibal = false;

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

		// get source node and destination node
		// if they are not parent relationship or vise versa, they are not tree
		// style relation

		OntologyRelationship rel = (OntologyRelationship) ((DefaultGraphArc) arc)
				.getUserObject();

		GraphNode srcNode = EntryPoint.getGraphModel().getSourceNode(rel);
		GraphNode dstNode = EntryPoint.getGraphModel().getDestinationNode(rel);

		if (!EntryPoint.getGraphModel().isParentChildRelation(srcNode, dstNode) == true) {
			return isIntraBranchNonTreeStyleArcVisibal;
		}

		// visible by default
		return true;
	}

	public boolean isIntraBranchNonTreeStyleArcVisibal() {
		return isIntraBranchNonTreeStyleArcVisibal;
	}

	public void setIntraBranchNonTreeStyleArcVisibal(
			boolean IntraBranchArcVisibility) {
		this.isIntraBranchNonTreeStyleArcVisibal = IntraBranchArcVisibility;
	}
}
