package au.uq.dke.comonviz.ui.data.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import database.model.data.BasicRecord;
import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.ui.data.panel.ForeignRecordsChoosePanel;

public class ForeignRecordsChooseDialog extends JDialog{
	
	public ForeignRecordsChooseDialog(BasicRecord primaryRecord,
			Class<?> foreignClass, BasicTablePanel callerTablePanel){
		
		ForeignRecordsChoosePanel panel = new ForeignRecordsChoosePanel(primaryRecord,
				foreignClass, callerTablePanel);
		this.setTitle(foreignClass.getSimpleName());
		
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

}
