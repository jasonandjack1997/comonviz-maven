package au.uq.dke.comonviz.ui.refactor;

import static org.metawidget.inspector.InspectionResultConstants.HIDDEN;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiAttribute;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.EntryPoint;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.ontology.OntologyClass;

public class ClassesCRUDDialog extends JDialog {

	private SwingMetawidget ontologyClassSwingMetawidget;
	private JTree jTree;
	private DefaultTreeModel treeModel;
	private boolean isEditing = false;
	DefaultMutableTreeNode root;
	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public ClassesCRUDDialog(DefaultMutableTreeNode root ) {
		this.root = root;

		ontologyClassSwingMetawidget = new SwingMetawidget();
		ontologyClassSwingMetawidget.addWidgetProcessor( new BeansBindingProcessor( new BeansBindingProcessorConfig()
		));
		ontologyClassSwingMetawidget.addWidgetProcessor(new ReflectionBindingProcessor());
		ontologyClassSwingMetawidget.setToInspect(this);
		//root = new DefaultMutableTreeNode("root1");
		// DefaultMutableTreeNode root = EntryPoint.getGraphModel()
		// .generateMutableTree();


		
		treeModel = new DefaultTreeModel(root);
		jTree = new JTree(treeModel);
		jTree.setSelectionRow(0);
		jTree.setCellRenderer(treeCellRender);
		jTree.addTreeSelectionListener(treeSelectionListener);
		treeModel.addTreeModelListener(treeModelListener);
		jTree.setPreferredSize(new Dimension(400, 200));
		jTree.setEditable(true);
		JScrollPane jScrollPane = new JScrollPane(jTree);
		jScrollPane.setPreferredSize(new Dimension(300, 300));
		Dimension d = jTree.getPreferredScrollableViewportSize();
		this.add(jScrollPane, BorderLayout.NORTH);
		this.add(ontologyClassSwingMetawidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}


	@UiAction
	@UiAttribute(name = HIDDEN, value = "${this.isEditing")
	public void delete() {
	       TreePath currentSelection = jTree.getSelectionPath();
	        if (currentSelection != null) {
	            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
	                         (currentSelection.getLastPathComponent());
	            GraphNode graphNode = (GraphNode) currentNode.getUserObject();
	            EntryPoint.getGraphModel().removeNode(graphNode.getUserObject());
	            MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
	            if (parent != null) {
	                treeModel.removeNodeFromParent(currentNode);
	                EntryPoint.getTopView().getTreeModel().reload();
	                return;
	            }
	        } 
	        

	}

	@UiAction
	public void add() {
		String child = "child";
		this.addObject(child);
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	@UiAction
	public void edit() {
	    DefaultMutableTreeNode node = null;
	    TreePath path = jTree.getSelectionPath();

	    if (path == null) {
	    } else {
	        node = (DefaultMutableTreeNode)
	                     (path.getLastPathComponent());
	    }

	    new OntologyClassDialog(this, node).setVisible(true); 

	}

	@UiAction
	public void save() {

	}
	

	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
		//treeModel.rel
	}

	DefaultTreeCellRenderer treeCellRender = new DefaultTreeCellRenderer() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value,
				boolean sel, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			this.setLeafIcon(null);
			this.setOpenIcon(null);
			this.setClosedIcon(null);
			return super.getTreeCellRendererComponent(tree, value.toString(),
					sel, expanded, leaf, row, hasFocus);
		}
	};

	TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
//			// TODO Auto-generated method stub
//			DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode) ((JTree) e
//					.getSource()).getLastSelectedPathComponent();
//			GraphNode selectedGraphNode = (GraphNode) selectedTreeNode
//					.getUserObject();
//			OntologyClass ontologyClass = (OntologyClass) selectedGraphNode
//					.getUserObject();
//			
//			//ontologyClass.setName("hehe");

		}

	};
	
	TreeModelListener treeModelListener = new TreeModelListener () {
	    public void treeNodesChanged(TreeModelEvent e) {
	        DefaultMutableTreeNode node;
	        node = (DefaultMutableTreeNode)
	                 (e.getTreePath().getLastPathComponent());
	         
	        try {
	            int index = e.getChildIndices()[0];
	            node = (DefaultMutableTreeNode)
	                   (node.getChildAt(index));
	        } catch (NullPointerException exc) {}
	        
	        System.out.println("The user has finished editing the node.");
	        System.out.println("New value: " + node.getUserObject());
	    }
	    public void treeNodesInserted(TreeModelEvent e) {
	    }
	    public void treeNodesRemoved(TreeModelEvent e) {
	    }
	    public void treeStructureChanged(TreeModelEvent e) {
	    }
	};

	public static void main(String args[]) {
		EntryPoint entryPoint = new EntryPoint();
		entryPoint.start();
		new ClassesCRUDDialog(EntryPoint.getOntologyTreeRoot());
	}
	
	
	public DefaultMutableTreeNode addObject(Object child) {
	    DefaultMutableTreeNode parentNode = null;
	    TreePath parentPath = jTree.getSelectionPath();

	    if (parentPath == null) {
	        //There is no selection. Default to the root node.
	        parentNode = root;
	    } else {
	        parentNode = (DefaultMutableTreeNode)
	                     (parentPath.getLastPathComponent());
	    }

	    return addObject(parentNode, child, true);
	}
	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
	                                        Object child,
	                                        boolean shouldBeVisible) {
	    DefaultMutableTreeNode childNode =
	            new DefaultMutableTreeNode(child);
	    treeModel.insertNodeInto(childNode, parent,
	                             parent.getChildCount());

	    //Make sure the user can see the lovely new node.
	    if (shouldBeVisible) {
	        jTree.scrollPathToVisible(new TreePath(childNode.getPath()));
	    }
	    return childNode;
	}

}
