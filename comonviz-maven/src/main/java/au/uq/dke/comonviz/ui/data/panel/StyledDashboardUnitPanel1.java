package au.uq.dke.comonviz.ui.data.panel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class StyledDashboardUnitPanel1 extends BasicDashboardUnitPanel{

	private Border defaultBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	public StyledDashboardUnitPanel1(String title) {
		super(title);
	}
	
	public StyledDashboardUnitPanel1(String title, int recordCount){
		super(title, recordCount);
		this.setBorder(defaultBorder);
	}


}
