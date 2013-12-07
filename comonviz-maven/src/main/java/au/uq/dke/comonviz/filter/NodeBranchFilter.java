package au.uq.dke.comonviz.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import ca.uvic.cs.chisel.cajun.graph.GraphItem;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.ontology.OntologyClass;

public class NodeBranchFilter implements GraphFilter {

	private Map<Object, Boolean> nodeBranchesVisibilityMap = new HashMap<Object, Boolean>();


	@Override
	public boolean isNodeFilter() {
		return true;
	}

	@Override
	public boolean isArcFilter() {
		return false;
	}

	@Override
	public boolean isVisible(GraphItem item) {
		GraphNode branchNode = EntryPoint.getGraphModel().getBranchGraphNode((GraphNode) item);
		if (this.nodeBranchesVisibilityMap.containsKey(branchNode)) {
			return nodeBranchesVisibilityMap.get(branchNode);
		}
		return true;
	}

	public void setNodeBranchVisible(Object branchNode, boolean visible) {

		// visible by default
		boolean old = true;
		if (nodeBranchesVisibilityMap.containsKey(branchNode)) {
			old = nodeBranchesVisibilityMap.get(branchNode);
		}
		if (old != visible) {
			nodeBranchesVisibilityMap.put(branchNode, visible);
			EntryPoint.getFilterManager().fireFiltersChanged();
		}
	}

	public Collection<Object> getNodeBranches() {
		return new HashSet<Object>(nodeBranchesVisibilityMap.keySet());
	}

	public Map<Object, Boolean> getNodeBranchesMap() {
		return new HashMap<Object, Boolean>(nodeBranchesVisibilityMap);
	}

	public void addNodeBranch(GraphNode node) {
		DefaultGraphNode branchNode = (DefaultGraphNode) EntryPoint.getGraphModel().getBranchGraphNode(node);
		if (EntryPoint.getGraphModel().findRoot().equals(node)) {
			//return;
		}
		if(!branchNode.equals(node)){
			return;
		}
		
		if (!nodeBranchesVisibilityMap.containsKey(branchNode)) {
			nodeBranchesVisibilityMap.put(branchNode, true);
		}
	}

	public void removeNodeBranch(GraphNode removedNode) {

	}

	public void init() {
		Collection<GraphNode> graphNodes = EntryPoint.getGraphModel()
				.getAllNodes();
		this.nodeBranchesVisibilityMap.clear();
		for (GraphNode graphNode : graphNodes) {
			addNodeBranch(graphNode);
		}
	}
}
