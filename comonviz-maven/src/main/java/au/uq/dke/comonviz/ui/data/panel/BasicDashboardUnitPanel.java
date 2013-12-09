package au.uq.dke.comonviz.ui.data.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class BasicDashboardUnitPanel extends JPanel {
	JLabel titleLabel = new JLabel();
	//private GridBagConstraints constraints = new GridBagConstraints();
	private static final Insets WEST_INSETS = new Insets(5, 0, 5, 5);
	private static final Insets EAST_INSETS = new Insets(5, 5, 5, 0);

	public BasicDashboardUnitPanel(String title) {
		//this.setMinimumSize(new Dimension(100, 100));
		//this.setPreferredSize(new Dimension(200, 200));
		this.setLayout(new BorderLayout());
		titleLabel.setText(title);
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		this.add(titleLabel, BorderLayout.NORTH);
	}

	public BasicDashboardUnitPanel(String title, int recordCount) {
		this(title);

		JPanel detailPanel = new JPanel(new MigLayout());
		detailPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		detailPanel.setLayout(new GridLayout());
		JLabel recordCountLabel = new JLabel("count: ");
		JLabel recordCountNumberLabel = new JLabel(String.valueOf(recordCount));
		
		detailPanel.add(recordCountLabel);
		detailPanel.add(recordCountNumberLabel);

		this.add(detailPanel, BorderLayout.CENTER);
	}

	private GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.anchor = (x == 0) ? GridBagConstraints.WEST
				: GridBagConstraints.EAST;
		gbc.fill = (x == 0) ? GridBagConstraints.BOTH
				: GridBagConstraints.HORIZONTAL;

		gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
		gbc.weightx = (x == 0) ? 0.1 : 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}

}
