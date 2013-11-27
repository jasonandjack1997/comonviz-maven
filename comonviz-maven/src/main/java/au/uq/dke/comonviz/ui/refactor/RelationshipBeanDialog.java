package au.uq.dke.comonviz.ui.refactor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;
import org.metawidget.util.CollectionUtils;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.treeUtils.MutableTreeNodeUtil;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.ontology.OntologyAxiom;
import database.model.ontology.OntologyClass;
import database.model.ontology.OntologyRelationship;

public class RelationshipBeanDialog extends JDialog {

	private RelationshipListDialog relationshipCRUDDialog;
	private SwingMetawidget editSwingMetawidget;
	private OntologyRelationship ontologyRelationship;
	private DefaultMutableTreeNode root;
	
	private OntologyClass srcClass;
	private OntologyClass dstClass;
	
	private GraphNode srcGraphNode;
	private GraphNode dstGraphNode;
	
	private OntologyAxiom relType;
	
	private JTree srcJTree;
	private TreeModel srcTreeModel;
	
	private JTree dstJTree;
	private TreeModel dstTreeModel;

	private ListTableModel relTypesListTableModel;
	private JTable relTypesTable;
	private JScrollPane relTypeScrollPane;

	public RelationshipBeanDialog(RelationshipListDialog relationshipCRUDDialog,
			OntologyRelationship ontologyRelationship, DefaultMutableTreeNode root) {
		
		this.relationshipCRUDDialog = relationshipCRUDDialog;
		this.ontologyRelationship = ontologyRelationship;

		this.root = root;
		this.srcClass = EntryPoint.getOntologyClassService().findById(ontologyRelationship.getSrcClassId());
		this.dstClass = EntryPoint.getOntologyClassService().findById(ontologyRelationship.getDstClassId());
		
		this.srcGraphNode = EntryPoint.getGraphModel().findGraphNode(srcClass);
		this.dstGraphNode = EntryPoint.getGraphModel().findGraphNode(dstClass);
		
		DefaultMutableTreeNode srcTreeNode = MutableTreeNodeUtil.findTreeNode(root, srcGraphNode);
		DefaultMutableTreeNode dstTreeNode = MutableTreeNodeUtil.findTreeNode(root, dstGraphNode);

		
		srcTreeModel = new DefaultTreeModel(root);
		srcJTree = new JTree(srcTreeModel);
		srcJTree.setSelectionRow(0);
		srcJTree.setCellRenderer(treeCellRender);
		JScrollPane srcJScrollPane = new JScrollPane(srcJTree);
		srcJScrollPane.setPreferredSize(new Dimension(200, 400));
		srcJTree.setSelectionPath(new TreePath(srcTreeNode.getPath()));
		
		
		
		dstTreeModel = new DefaultTreeModel(root);
		dstJTree = new JTree(dstTreeModel);
		dstJTree.setSelectionRow(0);
		dstJTree.setCellRenderer(treeCellRender);
		JScrollPane dstJScrollPane = new JScrollPane(dstJTree);
		dstJScrollPane.setPreferredSize(new Dimension(200, 400));
		dstJTree.scrollPathToVisible(new TreePath(dstTreeNode.getPath()));
		dstJTree.setSelectionPath(new TreePath(dstTreeNode.getPath()));
		
		
		relType = EntryPoint.getOntologyAxiomService().findById(this.ontologyRelationship.getAxiomId());
		
		
		relTypesListTableModel = new ListTableModel<OntologyAxiom>(OntologyAxiom.class, EntryPoint.getOntologyAxiomService().findAll(), "name");
		relTypeScrollPane = (JScrollPane) this.createResultsSection();
		int rowNumber = relTypesListTableModel.findRowNumber(relType);
		this.relTypesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.relTypesTable.setRowSelectionInterval(rowNumber, rowNumber);
		
		
		this.setLayout(new GridLayout(1,4));
		
		this.add(srcJScrollPane);
		this.add(relTypeScrollPane);
		this.add(dstJScrollPane);

		this.pack();
		this.setVisible(true);

		
		editSwingMetawidget = new SwingMetawidget();
		editSwingMetawidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		editSwingMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		editSwingMetawidget.setToInspect(this);
		editSwingMetawidget.setPreferredSize(new Dimension(400, 400));

		this.add(editSwingMetawidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void save() {

		
		// update database
		EntryPoint.getOntologyRelationshipService().save(ontologyRelationship);

		// update list
		this.relationshipCRUDDialog.updateList();

		return;
	}

	@UiAction
	public void cancel() {

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

	@SuppressWarnings( "serial" )
	private JComponent createResultsSection() {

		relTypesListTableModel.setEditable(true);
		relTypesTable = new JTable( relTypesListTableModel );
		relTypesTable.setAutoCreateColumnsFromModel( true );

		relTypesTable.setDefaultRenderer( Set.class, new DefaultTableCellRenderer() {

			@Override
			public void setValue( Object value ) {

				setText( CollectionUtils.toString( (Set<?>) value ) );
			}
		} );

		relTypesTable.setRowHeight( 25 );
		relTypesTable.setShowVerticalLines( true );
		relTypesTable.setCellSelectionEnabled( false );
		relTypesTable.setRowSelectionAllowed( true );

		return new JScrollPane( relTypesTable );
	}

}
