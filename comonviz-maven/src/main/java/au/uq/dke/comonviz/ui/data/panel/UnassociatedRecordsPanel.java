package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class UnassociatedRecordsPanel extends ButtonedTablePanel{

	public UnassociatedRecordsPanel(Class<?> primaryClass, Class<?> associatedClass) {
		super();
		RecordsTableModel tableModel = new RecordsTableModel(
				primaryClass);


		JTable table = new BasicTable(tableModel);

		TableRowSorter sorter = new TableRowSorter<RecordsTableModel>(tableModel);
		sorter.setRowFilter(new NoAssociatedRecordRowFilter(associatedClass));
		table.setRowSorter(sorter);
		
		super.init(table);
	}
	
	
	class NoAssociatedRecordRowFilter extends RowFilter<RecordsTableModel, Integer>{
		
		private Class<?> fieldType;
		public NoAssociatedRecordRowFilter(Class<?> fieldType){
			this.fieldType = fieldType;
		}

		@Override
		public boolean include(
				javax.swing.RowFilter.Entry<? extends RecordsTableModel, ? extends Integer> entry) {
			
			
			RecordsTableModel model = entry.getModel();
			BasicRecord record = model.getValueAt(entry.getIdentifier());
			String fieldName = ReflectionUtils.getFieldNameByType((Class<BasicRecord>) record.getClass(), this.fieldType);
			BasicRecord associatedRecord = ClassUtils.getProperty(record, fieldName);
			if(associatedRecord == null){
				
				return false;
			}
			return true;
		}
		
		
	}

}
