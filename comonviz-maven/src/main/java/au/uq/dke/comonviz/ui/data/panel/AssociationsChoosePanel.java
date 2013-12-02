package au.uq.dke.comonviz.ui.data.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.ui.data.tableModel.BasicTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtil;
import database.model.data.BasicRecord;

public class AssociationsChoosePanel extends ButtonedTablePanel{
	
	private BasicRecord mainRecord;

	public AssociationsChoosePanel(JTable table, BasicRecord mainRecord ) {
		super(table);
		this.mainRecord = mainRecord;
	}
	
	/**
	 * add the selected record(s) as the associated records to the pk record
	 */
	@SuppressWarnings("unchecked")
	@UiAction
	public void OK(){
		int[] selectedRows = this.getTable().getSelectedRows();
		List<BasicRecord> selectedRecords = new ArrayList<BasicRecord>();
		for(int i = 0; i < selectedRows.length; i++){
			BasicRecord record = ((BasicTableModel)this.getTable().getModel()).getValueAt(i);
			
			//main record should add selected record to it's set
			Set<BasicRecord> associatedSet = ReflectionUtil.getSpecificSet(mainRecord, (Class<BasicRecord>)record.getClass());
			//clear the set
			associatedSet.clear();
			associatedSet.add(record);
			
			//the selected record should set the main record as the association
			String property = ReflectionUtil.getFieldName((Class<BasicRecord>)record.getClass(), this.mainRecord.getClass());
			ClassUtils.setProperty(record, property, mainRecord);
			selectedRecords.add(record);
			
		}
		
		return;
		
	}

}
