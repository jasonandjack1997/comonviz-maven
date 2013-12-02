package au.uq.dke.comonviz.ui.data.table;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import org.metawidget.util.CollectionUtils;

public class BasicTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**A basic Table with presets
	 * @param tableModel
	 */
	public BasicTable(TableModel tableModel){
		super(tableModel);
		this.setAutoCreateColumnsFromModel(true);

		this.setDefaultRenderer(Set.class,
				new DefaultTableCellRenderer() {
					private static final long serialVersionUID = 1L;

					@Override
					public void setValue(Object value) {

						setText(CollectionUtils.toString((Set<?>) value));
					}
				});

		this.setRowHeight(25);
		this.setShowVerticalLines(true);
		this.setCellSelectionEnabled(false);
		this.setRowSelectionAllowed(true);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				if (event.getClickCount() != 2) {
					return;
				}
			}
		});


	}
}
