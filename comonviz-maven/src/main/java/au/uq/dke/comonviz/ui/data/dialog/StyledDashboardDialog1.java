package au.uq.dke.comonviz.ui.data.dialog;

import au.uq.dke.comonviz.ui.data.panel.StyledDashboardUnitPanel1;

public class StyledDashboardDialog1 extends BasicDashboardDialog{

	public StyledDashboardDialog1(String title) {
		super(title);
	}

	public static void main(String[] args) {
		StyledDashboardUnitPanel1 unitPanel1 = new StyledDashboardUnitPanel1("unit1", 3);
		StyledDashboardDialog1 dashboard = new StyledDashboardDialog1("");
		dashboard.addUnitPanel(unitPanel1);
	}
}
