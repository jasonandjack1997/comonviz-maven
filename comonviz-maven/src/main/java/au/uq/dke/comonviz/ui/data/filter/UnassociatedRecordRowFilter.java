package au.uq.dke.comonviz.ui.data.filter;

import javax.swing.RowFilter;

import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class UnassociatedRecordRowFilter extends RowFilter<RecordsTableModel, Integer>{
	
	private Class<?> fieldType;
	public UnassociatedRecordRowFilter(Class<?> fieldType){
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
			return true;
		}
		return false;
	}
	
	
}

