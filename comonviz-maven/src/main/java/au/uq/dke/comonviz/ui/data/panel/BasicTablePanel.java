package au.uq.dke.comonviz.ui.data.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiHidden;

import au.uq.dke.comonviz.misc.CustomRuntimeException;
import au.uq.dke.comonviz.ui.data.tableModel.BasicTableModel;

public class BasicTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	@UiHidden
	public JTable getTable() {
		return table;
	}

	@UiHidden
	public BasicTableModel getTableModel() {
		if (getTable() != null) {
			return (BasicTableModel) getTable().getModel();
		} else {
			throw new CustomRuntimeException("no table");
		}
	}

	private JScrollPane mainScrollPane;

	/**
	 * a panel with a scrollable table
	 * 
	 * @param mainListTable
	 */
	public BasicTablePanel(JTable table) {
		init(table);
	}

	public BasicTablePanel() {
		// empty
	}

	/**
	 * fake constructor if subclass called the default constructor, it must call
	 * the init to construct
	 * 
	 * @param table
	 */
	public void init(JTable table) {
		this.setVisible(true);
		this.table = table;
		mainScrollPane = new JScrollPane(this.table);
		this.add(mainScrollPane, BorderLayout.CENTER);

	}

}
