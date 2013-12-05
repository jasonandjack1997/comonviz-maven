package au.uq.dke.comonviz.ui.data;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.ui.data.panel.PrimaryRecordsTablePanel;
import au.uq.dke.comonviz.ui.data.panel.UnassociatedRecordsPanel;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;

/**
 * contains a primary list & a set of missing associated foreign records lists
 * @author uqwwan10
 *
 */
public class BasicRecordsInfoDialog extends JDialog{
	
	private Class<?> recordType;
	private BasicTablePanel primaryRecordsTablePanel;
	private List<BasicTablePanel> unassociatedForeignRecordsTablePanelList = new ArrayList<BasicTablePanel>();
	
	public BasicRecordsInfoDialog(Class<?> recordType){
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.recordType = recordType;
		this.setTitle(recordType.getSimpleName());
		primaryRecordsTablePanel = new PrimaryRecordsTablePanel(recordType);
		if(primaryRecordsTablePanel.getTable().getRowCount()>0){
			
			primaryRecordsTablePanel.getTable().setRowSelectionInterval(0, 0);
		}
		
		this.add(primaryRecordsTablePanel, BorderLayout.CENTER);
		List<Field> setFields = ReflectionUtils.getSetFieldList(recordType);
		for(Field field : setFields){
			Class setElementType = ReflectionUtils.getSetElementType(field);
			BasicTablePanel unassociatedForeignRecordsPanel = new UnassociatedRecordsPanel(setElementType, recordType);
			this.unassociatedForeignRecordsTablePanelList.add(unassociatedForeignRecordsPanel);
			this.add(unassociatedForeignRecordsPanel, BorderLayout.SOUTH);
		}
		
		primaryRecordsTablePanel.getTable().getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				BasicRecordsInfoDialog.this.updateForeignTables();
			}
		});
		
		this.pack();
		this.setVisible(true);
		
	}
	
	public void updateForeignTables(){
		for(BasicTablePanel foreignRecordsTablePanel : this.unassociatedForeignRecordsTablePanelList){
			
			Collection records = ((RecordsTableModel)foreignRecordsTablePanel.getTableModel()).getService().findAll();
			foreignRecordsTablePanel.getTableModel().importCollection(records);
		}
		
	}
	
	

}
