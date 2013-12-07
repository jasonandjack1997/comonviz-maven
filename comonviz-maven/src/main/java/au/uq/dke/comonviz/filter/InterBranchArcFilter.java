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

public class InterBranchArcFilter implements GraphFilter {

	private boolean isInterBranchArcVisibal = false;
	
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
		OntologyRelationship rel = (OntologyRelationship) ((DefaultGraphArc)arc).getUserObject();
		
		GraphNode srcNode = EntryPoint.getGraphModel().getSourceNode(rel);
		GraphNode dstNode = EntryPoint.getGraphModel().getDestinationNode(rel);
		
		GraphNode srcBranchNode = EntryPoint.getGraphModel().getBranchGraphNode(srcNode);
		GraphNode dstBranchNode = EntryPoint.getGraphModel().getBranchGraphNode(dstNode);
		
		//rel with root
		if(srcNode.equals(EntryPoint.getGraphModel().findRoot())
				|| dstNode.equals(EntryPoint.getGraphModel().findRoot())){
			return true;
		}
		
		//cross branches
		if(!srcBranchNode.equals(dstBranchNode)){
			return isInterBranchArcVisibal;
		}
		
		// visible by default
		return true;
	}

	public boolean isInterBranchArcVisibal() {
		return isInterBranchArcVisibal;
	}

	public void setInterBranchArcVisibal(boolean interBranchArcVisibility) {
		this.isInterBranchArcVisibal = interBranchArcVisibility;
	}
}
