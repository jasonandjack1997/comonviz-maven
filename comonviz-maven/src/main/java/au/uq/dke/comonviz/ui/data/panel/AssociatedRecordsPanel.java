package au.uq.dke.comonviz.ui.data.panel;

import java.util.Set;

import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;

import database.model.data.BasicRecord;

/**this panel is in the record dialog
 * this shows a list of associated records in a table
 * buttons are add and delete, but operate differently from {@PKRecordBeanDialog}
 * 
 * @author uqwwan10
 *
 */
public class AssociatedRecordsPanel extends ButtonedTablePanel{
	private Set<BasicRecord> associationSet;

	public AssociatedRecordsPanel(JTable table, Set<BasicRecord> associationSet) {
		super(table);
		this.associationSet = associationSet;
	}
	
	
	/**
	 *add a new associated record
	 *this will open a {@AssociationChoosePanel} of the associated type and let user
	 *choose one or multiple associations
	 */
	@UiAction
	public void addAssociation(){
		
	}
	
	@UiAction
	//@UiComesAfter("save")
	public void deleteAssication(){
		
	}

}
