package au.uq.dke.comonviz.ui.ontology.refact;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.ontology.OntologyClass;

public class ClassBeanDialog extends JDialog {

	private ClassesListDialog classesCRUDDialog;
	private SwingMetawidget beanMetawidget;
	private SwingMetawidget editMetawidget;
	private OntologyClass ontologyClass;
	private GraphNode graphNode;
	private DefaultMutableTreeNode defaultMutableTreeNode;

	public ClassBeanDialog(ClassesListDialog classesCRUDDialog,
			DefaultMutableTreeNode defaultMutableTreeNode) {
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);

		this.defaultMutableTreeNode = defaultMutableTreeNode;

		//create a new node, we should generate some information: id, branchId, level
		if (defaultMutableTreeNode.getUserObject() == null) {
			DefaultMutableTreeNode parentTreeNode = (DefaultMutableTreeNode) defaultMutableTreeNode.getParent();
			GraphNode parentGraphNode = (GraphNode) parentTreeNode.getUserObject();
			OntologyClass parentOntologyClass = (OntologyClass) parentGraphNode.getUserObject();

			defaultMutableTreeNode.setUserObject(new DefaultGraphNode(
					new OntologyClass()));

			// generate an id
			this.graphNode = (GraphNode) defaultMutableTreeNode.getUserObject();
			this.classesCRUDDialog = classesCRUDDialog;
			ontologyClass = (OntologyClass) graphNode.getUserObject();
			ontologyClass.setBranchId(parentOntologyClass.getBranchId());
			ontologyClass.setLevel(parentOntologyClass.getLevel() + 1);
		} else {
			this.graphNode = (GraphNode) defaultMutableTreeNode.getUserObject();
			this.classesCRUDDialog = classesCRUDDialog;
			ontologyClass = (OntologyClass) graphNode.getUserObject();
		}
		beanMetawidget = new SwingMetawidget();
		beanMetawidget
				.addWidgetProcessor(new BeansBindingProcessor(
						new BeansBindingProcessorConfig()));
		beanMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		beanMetawidget.setToInspect(ontologyClass);
		beanMetawidget.setPreferredSize(new Dimension(400, 400));

		editMetawidget = new SwingMetawidget();
		editMetawidget
				.addWidgetProcessor(new BeansBindingProcessor(
						new BeansBindingProcessorConfig()));
		editMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		editMetawidget.setToInspect(this);
		editMetawidget.setPreferredSize(new Dimension(400, 400));

		this.add(beanMetawidget, BorderLayout.NORTH);
		this.add(editMetawidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void save() {
		//update bean
		beanMetawidget.getWidgetProcessor(
				BeansBindingProcessor.class).save(beanMetawidget);
		OntologyClass c = beanMetawidget.getToInspect();
		this.ontologyClass.update(c);
		
		// update database
		EntryPoint.getOntologyClassService().save(
				(OntologyClass) ((DefaultGraphNode) defaultMutableTreeNode
						.getUserObject()).getUserObject());

		
		//update trees
		this.classesCRUDDialog.getTreeModel().reload(
				defaultMutableTreeNode.getParent());
		this.classesCRUDDialog.getjTree().scrollPathToVisible(
				new TreePath(defaultMutableTreeNode.getPath()));
		
		EntryPoint.getTopView().getTreeModel()
				.reload(defaultMutableTreeNode.getParent());
		EntryPoint.getTopView().getjTree().scrollPathToVisible(new TreePath(defaultMutableTreeNode.getPath()));

		//update graph model
		EntryPoint.getGraphModel().addNode(ontologyClass);

		//update graph node
		((DefaultGraphNode) this.graphNode).invalidatePaint();
		((DefaultGraphNode) this.graphNode).repaint();
		((DefaultGraphNode) this.graphNode).validateFullPaint();

		return;
	}

	@UiAction
	public void cancel() {
		DefaultMutableTreeNode parentTreeNode = (DefaultMutableTreeNode) this.defaultMutableTreeNode.getParent();
		if(parentTreeNode != null){
			
			parentTreeNode.remove(this.defaultMutableTreeNode);
		}
		this.dispose();

	}
}
