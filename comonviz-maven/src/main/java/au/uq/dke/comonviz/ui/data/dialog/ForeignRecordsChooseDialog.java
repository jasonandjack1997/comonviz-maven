package au.uq.dke.comonviz.ui.data.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class ForeignRecordsChooseDialog extends JDialog{
	
	public ForeignRecordsChooseDialog(JPanel panel){
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}

}
