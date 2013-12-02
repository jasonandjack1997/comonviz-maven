package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.PrimeryRecordsTableModel;
import database.model.data.bussinesProcessManagement.ProcessActivity;

public class PrimeryRecordsTablePanelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		JFrame frame = new JFrame();
		JTable table = new BasicTable(new PrimeryRecordsTableModel(ProcessActivity.class));
		frame.add(new PrimeryRecordsTablePanel(table));
		frame.pack();
		frame.setVisible(true);
		return;
	}

}
