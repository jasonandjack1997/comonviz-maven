package au.uq.dke.comonviz.ui.data;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JDialog;

import au.uq.dke.comonviz.ui.data.panel.PrimaryRecordsTablePanel;
import au.uq.dke.comonviz.ui.data.panel.UnassociatedRecordsPanel;
import au.uq.dke.comonviz.utils.ReflectionUtils;

public class RecordsDialog extends JDialog{
	
	private Class<?> recordType;
	public RecordsDialog(Class<?> recordType){
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.recordType = recordType;
		this.setTitle(recordType.getSimpleName());
		this.add(new PrimaryRecordsTablePanel(recordType), BorderLayout.CENTER);
		List<Field> setFields = ReflectionUtils.getSetFieldList(recordType);
		for(Field field : setFields){
			Class setElementType = ReflectionUtils.getSetElementType(field);
			this.add(new UnassociatedRecordsPanel(setElementType, recordType), BorderLayout.SOUTH);
		}
		this.pack();
		this.setVisible(true);
		
	}
	
	

}
