package au.uq.dke.comonviz.treeUtils;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import au.uq.dke.comonviz.graph.node.DefaultGraphNode;

public class JTreeUtil {

	public static void filterNode(JTree jTree, String regex) {
		if (regex.length() <= 5 && !regex.endsWith(" ")) {
				return;
		}
		
		if(regex.contains(" ")) {
			regex = regex.trim();
		}
		
		DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jTree
				.getModel().getRoot();

		jTree.collapsePath(new TreePath(rootNode.getPath()));
		List<DefaultMutableTreeNode> descendants = MutableTreeNodeUtil
				.getDesendants(rootNode);

		for (DefaultMutableTreeNode node : descendants) {

			if (((DefaultGraphNode) node.getUserObject()).getText()
					.startsWith(regex)) {
				// jTree.scrollPathToVisible(new TreePath(node.getPath()));
				jTree.setSelectionPath(new TreePath(node.getPath()));
			}
		}
	}
}
