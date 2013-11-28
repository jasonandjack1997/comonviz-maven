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
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import database.model.ontology.OntologyAxiom;
import database.model.ontology.OntologyClass;

public class RelTypeBeanDialog extends JDialog {

	private RelTypeListDialog relTypeCRUDDialog;
	private SwingMetawidget beanSwingMetawidget;
	private SwingMetawidget editSwingMetawidget;
	private OntologyAxiom ontologyAxiom;
	private DefaultMutableTreeNode defaultMutableTreeNode;

	public RelTypeBeanDialog(RelTypeListDialog relTypeCRUDDialog,
			OntologyAxiom ontologyAxiom) {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		this.relTypeCRUDDialog = relTypeCRUDDialog;
		this.ontologyAxiom = ontologyAxiom;

		beanSwingMetawidget = new SwingMetawidget();
		beanSwingMetawidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		beanSwingMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		beanSwingMetawidget.setToInspect(ontologyAxiom);
		beanSwingMetawidget.setPreferredSize(new Dimension(400, 400));

		editSwingMetawidget = new SwingMetawidget();
		editSwingMetawidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		editSwingMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		editSwingMetawidget.setToInspect(this);
		editSwingMetawidget.setPreferredSize(new Dimension(400, 400));

		this.add(beanSwingMetawidget, BorderLayout.NORTH);
		this.add(editSwingMetawidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void save() {
		// update bean
		beanSwingMetawidget.getWidgetProcessor(BeansBindingProcessor.class)
				.save(beanSwingMetawidget);
		OntologyAxiom c = beanSwingMetawidget.getToInspect();
		this.ontologyAxiom.update(c);

		// update database
		EntryPoint.getOntologyAxiomService().save(ontologyAxiom);

		// update list
		this.relTypeCRUDDialog.updateList();

		return;
	}

	@UiAction
	public void cancel() {
		this.dispose();

	}
}
