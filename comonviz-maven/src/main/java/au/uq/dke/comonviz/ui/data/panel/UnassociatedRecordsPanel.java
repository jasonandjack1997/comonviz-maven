package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.ui.data.filter.UnassociatedRecordRowFilter;
import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class UnassociatedRecordsPanel extends ButtonedTablePanel{

	public UnassociatedRecordsPanel(Class<?> primaryRecordType, Class<?> associatedRecordType) {
		super();
		RecordsTableModel tableModel = new RecordsTableModel(
				primaryRecordType);


		JTable table = new BasicTable(tableModel);

		TableRowSorter sorter = new TableRowSorter<RecordsTableModel>(tableModel);
		sorter.setRowFilter(new UnassociatedRecordRowFilter(associatedRecordType));
		table.setRowSorter(sorter);
		
		super.init(table);
	}
	
	

}
