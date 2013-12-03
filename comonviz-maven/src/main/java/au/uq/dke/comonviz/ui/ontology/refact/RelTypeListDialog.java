package au.uq.dke.comonviz.ui.ontology.refact;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;
import org.metawidget.util.CollectionUtils;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import database.model.ontology.OntologyAxiom;

public class RelTypeListDialog extends JDialog {

	private SwingMetawidget ontologyRelTypesMetawidget;
	private OriginalListTableModel<OntologyAxiom> relTypesListTableModel;
	private JTable relTypesTable;
	private JScrollPane relTypeScrollPane;

	public RelTypeListDialog() {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		ontologyRelTypesMetawidget = new SwingMetawidget();
		ontologyRelTypesMetawidget
				.addWidgetProcessor(new BeansBindingProcessor(
						new BeansBindingProcessorConfig()));
		ontologyRelTypesMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		ontologyRelTypesMetawidget.setToInspect(this);

		relTypesListTableModel = new OriginalListTableModel<OntologyAxiom>(
				OntologyAxiom.class, EntryPoint.getOntologyAxiomService()
						.findAll(), "name");

		relTypeScrollPane = (JScrollPane) this.createResultsSection();
		this.add(relTypeScrollPane, BorderLayout.CENTER);
		this.add(ontologyRelTypesMetawidget, BorderLayout.SOUTH);
		// this.setPreferredSize(new Dimension(400, 400));
		this.pack();
		// this.setSize(400, 400);
		this.setVisible(true);
	}

	public void updateList() {
		this.relTypesListTableModel.importCollection(EntryPoint
				.getOntologyAxiomService().findAll());
	}

	@UiAction
	public void add() {
		OntologyAxiom newOntologyRelType = new OntologyAxiom();
		new RelTypeBeanDialog(this, newOntologyRelType).setVisible(true);
	}

	@UiAction
	public void edit() {

		OntologyAxiom ontologyAxiom = (OntologyAxiom) this.relTypesListTableModel
				.getValueAt(relTypesTable.getSelectedRow());
		new RelTypeBeanDialog(this, ontologyAxiom).setVisible(true);
	}

	@UiAction
	public void delete() {
		OntologyAxiom ontologyAxiom = (OntologyAxiom) this.relTypesListTableModel
				.getValueAt(relTypesTable.getSelectedRow());
		EntryPoint.getOntologyAxiomService().delete(ontologyAxiom);

		// update list
		this.updateList();

	}

	@SuppressWarnings("serial")
	private JComponent createResultsSection() {

		relTypesListTableModel.setEditable(true);
		relTypesTable = new JTable(relTypesListTableModel);
		relTypesTable.setAutoCreateColumnsFromModel(true);

		relTypesTable.setDefaultRenderer(Set.class,
				new DefaultTableCellRenderer() {

					@Override
					public void setValue(Object value) {

						setText(CollectionUtils.toString((Set<?>) value));
					}
				});

		relTypesTable.setRowHeight(25);
		relTypesTable.setShowVerticalLines(true);
		relTypesTable.setCellSelectionEnabled(false);
		relTypesTable.setRowSelectionAllowed(true);

		relTypesTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				// When table is double clicked...

				if (event.getClickCount() != 2) {
					return;
				}

				// ...fetch the Contact...

				@SuppressWarnings("unchecked")
				OntologyAxiom ontologyAxiom = (OntologyAxiom) RelTypeListDialog.this.relTypesListTableModel
						.getValueAt(relTypesTable.getSelectedRow());

				// createContactDialog( ontologyAxiom ).setVisible( true );
			}
		});

		return new JScrollPane(relTypesTable);
	}

	public static void main(String args[]) {
		EntryPoint entryPoint = new EntryPoint();
		entryPoint.start();
		new RelTypeListDialog();
	}

}
