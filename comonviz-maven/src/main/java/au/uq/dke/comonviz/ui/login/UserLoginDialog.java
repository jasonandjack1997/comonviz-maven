package au.uq.dke.comonviz.ui.login;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPasswordField;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.layout.GridBagLayoutConfig;
import org.metawidget.swing.layout.SeparatorLayoutDecorator;
import org.metawidget.swing.layout.SeparatorLayoutDecoratorConfig;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import database.model.user.User;

public class UserLoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private User user;
	private SwingMetawidget modelWidget;
	private SwingMetawidget buttonsWidget;

	public UserLoginDialog() {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle("User log in");

		this.user = new User();

		modelWidget = new SwingMetawidget();
		modelWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		modelWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		modelWidget.setToInspect(user);
		modelWidget.setMaximumInspectionDepth(0);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setName("password");
		modelWidget.add(passwordField);
		
		buttonsWidget = new SwingMetawidget();
		buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		buttonsWidget.setMaximumInspectionDepth(0);
		GridBagLayoutConfig nestedLayoutConfig = new GridBagLayoutConfig().setNumberOfColumns( 2 ); 
		SeparatorLayoutDecoratorConfig layoutConfig = new SeparatorLayoutDecoratorConfig().setLayout(
			new org.metawidget.swing.layout.GridBagLayout( nestedLayoutConfig ));
		buttonsWidget.setMetawidgetLayout( new SeparatorLayoutDecorator( layoutConfig ));
		buttonsWidget.setToInspect(this);


		
		
		this.add(modelWidget, BorderLayout.NORTH);
		this.add(buttonsWidget, BorderLayout.SOUTH);
		this.pack();

	}

	@UiAction
	public void login() {

	}

	@UiAction
	@UiComesAfter("login")
	public void cancel() {

	}

	public static void main(String[] args) {
		new UserLoginDialog().setVisible(true);
	}

}
