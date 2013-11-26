package au.uq.dke.comonviz.ui.refactor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;
import org.metawidget.util.CollectionUtils;
import org.metawidget.util.simple.StringUtils;

public class RelTypeCRUDDialogDirectEdit extends JDialog {

	ListTableModel<Communication> mCommunicationsModel;

	public RelTypeCRUDDialogDirectEdit() {
		setSize(new Dimension(800, 600));
		getContentPane().setBackground(Color.white);

		final List cl = new ArrayList();
		cl.add(new Communication("1", "11"));
		cl.add(new Communication("2", "22"));

		mCommunicationsModel = new ListTableModel<Communication>(
				Communication.class, cl, "Type", "Value");
		final JTable communicationsTable = new JTable(mCommunicationsModel);
		communicationsTable.putClientProperty("terminateEditOnFocusLost",
				Boolean.TRUE);
		communicationsTable.setDefaultEditor(Object.class,
				new CommunicationEditor());

		final JPopupMenu menuPopup = new JPopupMenu();
		menuPopup.add(new AbstractAction("delete") {

			public void actionPerformed(ActionEvent event) {

				menuPopup.setVisible(false);

				int rowAtPoint = communicationsTable.rowAtPoint(menuPopup
						.getLocation());

				if (rowAtPoint < 0
						|| rowAtPoint >= mCommunicationsModel.getRowCount()) {
					return;
				}

				Communication communication = mCommunicationsModel
						.getValueAt(rowAtPoint);
				mCommunicationsModel.importCollection(cl);
			}
		});
		communicationsTable.add(menuPopup);

		communicationsTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent event) {

				if (event.isPopupTrigger()) {

					Component source = (Component) event.getSource();

					// (source will not be 'showing on screen' during unit
					// tests)

					if (source.isShowing()) {
						Point screenLocation = source.getLocationOnScreen();
						menuPopup.setLocation(screenLocation.x + event.getX(),
								screenLocation.y + event.getY());
					}

					if (communicationsTable.getCellEditor() != null) {
						communicationsTable.getCellEditor().stopCellEditing();
					}

					menuPopup.setVisible(true);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(communicationsTable);
		scrollPane.setName("communications");
		scrollPane.setPreferredSize(new Dimension(250, 250));
		this.add(scrollPane, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);

	}

	private static class CommunicationEditor extends AbstractCellEditor
			implements TableCellEditor {

		//
		// Private members
		//

		private SwingMetawidget mEditor;

		private String mColumnName;

		//
		// Constructor
		//

		public CommunicationEditor() {

			mEditor = new SwingMetawidget();
			mEditor.setMetawidgetLayout(new org.metawidget.swing.layout.BoxLayout());
			mEditor.addWidgetProcessor(new BeansBindingProcessor(
					new BeansBindingProcessorConfig()));
			mEditor.addWidgetProcessor(new ReflectionBindingProcessor());
		}

		//
		// Public methods
		//

		public Object getCellEditorValue() {

			return mEditor.getValue(mColumnName);
		}

		@SuppressWarnings("unchecked")
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean selected, int row, int column) {

			Communication communication = ((ListTableModel<Communication>) table
					.getModel()).getValueAt(row);

			if (communication == null) {
				communication = new Communication();
			}

			mEditor.setToInspect(communication);
			mColumnName = StringUtils.decapitalize(table.getModel()
					.getColumnName(column));
			mEditor.setPath(Communication.class.getName()
					+ StringUtils.SEPARATOR_FORWARD_SLASH_CHAR + mColumnName);
			mEditor.setValue(value, mColumnName);

			// Metawidget just creates regular components, so we can modify them
			// if we need. Here,
			// we remove the border so that the component looks nicer when used
			// as a CellEditor

			((JComponent) mEditor.getComponent(0)).setBorder(null);

			return mEditor;
		}
	}

	public static void main(String args[]) {
		new RelTypeCRUDDialogDirectEdit();
	}
}
