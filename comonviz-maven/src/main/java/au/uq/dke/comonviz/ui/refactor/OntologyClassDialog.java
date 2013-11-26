package au.uq.dke.comonviz.ui.refactor;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.tree.DefaultMutableTreeNode;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.ontology.OntologyClass;

public class OntologyClassDialog extends JDialog {

	private ClassesCRUDDialog classesCRUDDialog;
	private SwingMetawidget ontologyClassSwingMetawidget;
	private SwingMetawidget ontologyClassDialogMetawidget;
	private OntologyClass ontologyClass;
	private GraphNode graphNode;
	private DefaultMutableTreeNode defaultMutableTreeNode;

	public OntologyClassDialog(ClassesCRUDDialog classesCRUDDialog,
			DefaultMutableTreeNode defaultMutableTreeNode) {
		this.defaultMutableTreeNode = defaultMutableTreeNode;
		this.graphNode = (GraphNode) defaultMutableTreeNode.getUserObject();
		this.classesCRUDDialog = classesCRUDDialog;
		ontologyClass = (OntologyClass) graphNode.getUserObject();
		ontologyClassSwingMetawidget = new SwingMetawidget();
		ontologyClassSwingMetawidget
				.addWidgetProcessor(new BeansBindingProcessor(
						new BeansBindingProcessorConfig()));
		ontologyClassSwingMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		ontologyClassSwingMetawidget.setToInspect(ontologyClass);
		ontologyClassSwingMetawidget.setPreferredSize(new Dimension(400, 400));

		ontologyClassDialogMetawidget = new SwingMetawidget();
		ontologyClassDialogMetawidget
				.addWidgetProcessor(new BeansBindingProcessor(
						new BeansBindingProcessorConfig()));
		ontologyClassDialogMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		ontologyClassDialogMetawidget.setToInspect(this);
		ontologyClassDialogMetawidget.setPreferredSize(new Dimension(400, 400));

		this.add(ontologyClassSwingMetawidget, BorderLayout.NORTH);
		this.add(ontologyClassDialogMetawidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void save() {
		ontologyClassSwingMetawidget.getWidgetProcessor(
				BeansBindingProcessor.class).save(ontologyClassSwingMetawidget);
		OntologyClass c = ontologyClassSwingMetawidget.getToInspect();
		this.ontologyClass.update(c);
		//defaultMutableTreeNode.setUserObject(c);
		this.classesCRUDDialog.getTreeModel().reload(defaultMutableTreeNode);
        EntryPoint.getTopView().getTreeModel().reload(defaultMutableTreeNode);

		((DefaultGraphNode)this.graphNode).invalidatePaint();
		((DefaultGraphNode)this.graphNode).repaint();
		((DefaultGraphNode)this.graphNode).validateFullPaint();
		

		return;
	}

	@UiAction
	public void cancel() {

	}
}
