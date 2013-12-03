package au.uq.dke.comonviz.ui.ontologyRefactor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;
import org.metawidget.util.CollectionUtils;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.data.tableModel.PrimaryRecordsTableModel;
import database.model.ontology.OntologyRelationship;

public class RelationshipListDialog extends JDialog{

	private SwingMetawidget relationshipMetawidget;
	private OriginalListTableModel<OntologyRelationship> relationshipListTableModel;
	private JTable relationshipTable;
	private JScrollPane relTypeScrollPane;
	
	
	public RelationshipListDialog(){
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		relationshipMetawidget = new SwingMetawidget();
		relationshipMetawidget
				.addWidgetProcessor(new BeansBindingProcessor(
						new BeansBindingProcessorConfig()));
		relationshipMetawidget
				.addWidgetProcessor(new ReflectionBindingProcessor());
		relationshipMetawidget.setToInspect(this);

		
		
		relationshipListTableModel = new OriginalListTableModel<OntologyRelationship>(OntologyRelationship.class, EntryPoint.getOntologyRelationshipService().findAll(), "sourceName", "name","destinationName");
		
		relTypeScrollPane = (JScrollPane) this.createResultsSection();
		this.add(relTypeScrollPane, BorderLayout.CENTER);
		this.add(relationshipMetawidget, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(800, 800));
		this.pack();
		this.setVisible(true);
	}
	
	public void updateList(){
		this.relationshipListTableModel.importCollection(EntryPoint.getOntologyRelationshipService().findAll());
	}
	
	public void updateList(OntologyRelationship ontologyRelationship){
		updateList();
		int rowNumber = this.relationshipListTableModel.findRowNumber(ontologyRelationship);
		if(rowNumber == -1){
			return;
		}
		this.relationshipTable.setRowSelectionInterval(rowNumber, rowNumber);
	}
	
	@UiAction
	public  void add(){
		OntologyRelationship newOntologyRelationship = new OntologyRelationship();
		new RelationshipBeanDialog(this, newOntologyRelationship, EntryPoint.getOntologyTreeRoot(), true).setVisible(true);
	}
	
	
	@UiAction
	public void edit(){
		
		OntologyRelationship ontologyRelationship = (OntologyRelationship) this.relationshipListTableModel.getValueAt(relationshipTable.getSelectedRow());
		new RelationshipBeanDialog(this, ontologyRelationship, EntryPoint.getOntologyTreeRoot(), false).setVisible(true);
	}
	
	@UiAction
	public void delete(){
		OntologyRelationship ontologyRelationship = (OntologyRelationship) this.relationshipListTableModel.getValueAt(relationshipTable.getSelectedRow());
		EntryPoint.getOntologyRelationshipService().delete(ontologyRelationship);

		// update list
		this.updateList();
		
		
	}
	
	@SuppressWarnings( "serial" )
	private JComponent createResultsSection() {

		relationshipTable = new JTable( relationshipListTableModel );
		relationshipTable.setAutoCreateColumnsFromModel( true );

		relationshipTable.setDefaultRenderer( Set.class, new DefaultTableCellRenderer() {

			@Override
			public void setValue( Object value ) {

				setText( CollectionUtils.toString( (Set<?>) value ) );
			}
		} );

		relationshipTable.setRowHeight( 25 );
		relationshipTable.setShowVerticalLines( true );
		relationshipTable.setCellSelectionEnabled( false );
		relationshipTable.setRowSelectionAllowed( true );

		relationshipTable.addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked( MouseEvent event ) {

				// When table is double clicked...

				if ( event.getClickCount() != 2 ) {
					return;
				}

				// ...fetch the Contact...

				@SuppressWarnings( "unchecked" )
				OntologyRelationship ontologyRelationship = (OntologyRelationship) RelationshipListDialog.this.relationshipListTableModel.getValueAt(relationshipTable.getSelectedRow());

				//createContactDialog( ontologyRelationship ).setVisible( true );
			}
		} );

		return new JScrollPane( relationshipTable );
	}

	public static void main(String args[]){
		EntryPoint entryPoint = new EntryPoint();
		entryPoint.start();
		new RelationshipListDialog();
	}
	
}
