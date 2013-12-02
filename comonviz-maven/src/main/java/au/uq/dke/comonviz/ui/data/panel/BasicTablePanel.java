package au.uq.dke.comonviz.ui.data.panel;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;
import org.metawidget.util.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.uq.dke.comonviz.utils.ReflectionUtil;
import database.model.data.BasicRecord;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.model.data.bussinesProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class BasicTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	@UiHidden
	public JTable getTable() {
		return table;
	}

	private JScrollPane mainScrollPane;

	/**a panel with a scrollable table
	 * @param mainListTable
	 */
	public BasicTablePanel(JTable table) {
		this.setVisible(true);
		this.table = table;
		mainScrollPane = new JScrollPane(this.table);
		this.add(mainScrollPane, BorderLayout.CENTER);
	}

}
