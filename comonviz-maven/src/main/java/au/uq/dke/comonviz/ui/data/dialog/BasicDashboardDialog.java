package au.uq.dke.comonviz.ui.data.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import au.uq.dke.comonviz.ui.data.panel.BasicDashboardUnitPanel;

public class BasicDashboardDialog extends JDialog{
	
	private GridBagConstraints constraints = new GridBagConstraints();

	public BasicDashboardDialog(String title){
		super();
		this.setTitle(title);
		this.setLayout(new GridBagLayout());
		this.constraints.fill = GridBagConstraints.HORIZONTAL;
		this.pack();
		this.setVisible(true);
	}
	
	public void addUnitPanel(BasicDashboardUnitPanel unitPanel){
		this.add(unitPanel, constraints);
		constraints.gridy ++;
		this.pack();
	}

}
