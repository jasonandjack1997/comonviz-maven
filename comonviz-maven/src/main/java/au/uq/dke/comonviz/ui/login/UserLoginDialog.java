package au.uq.dke.comonviz.ui.login;

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.layout.GridBagLayoutConfig;
import org.metawidget.swing.layout.SeparatorLayoutDecorator;
import org.metawidget.swing.layout.SeparatorLayoutDecoratorConfig;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.misc.CustomRuntimeException;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.ontology.OntologyClass;
import database.model.user.User;
import database.model.user.UserRole;

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
		GridBagLayoutConfig nestedLayoutConfig = new GridBagLayoutConfig()
				.setNumberOfColumns(2);
		SeparatorLayoutDecoratorConfig layoutConfig = new SeparatorLayoutDecoratorConfig()
				.setLayout(new org.metawidget.swing.layout.GridBagLayout(
						nestedLayoutConfig));
		buttonsWidget.setMetawidgetLayout(new SeparatorLayoutDecorator(
				layoutConfig));
		buttonsWidget.setToInspect(this);

		this.add(modelWidget, BorderLayout.NORTH);
		this.add(buttonsWidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void login() {

		
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				
				// update bean
				modelWidget.getWidgetProcessor(BeansBindingProcessor.class).save(
						modelWidget);
				User user = modelWidget.getToInspect();
				// root
				if (user.getName().equals("root")) {
					EntryPoint.setCurrentLayoutStyle(EntryPoint.radicalLayoutStyle);


					EntryPoint.getFilterManager().getNodeLevelFilter()
							.updateNodeLevels(3);
					EntryPoint.getTopView().getNodeLevelFilterPanel().reload();

					
					EntryPoint.getTopView().getNodeBranchFilterPanel().setEnabled(true);
					EntryPoint.getFilterManager().getNodeBranchFilter()
					.setAllBranchesVisible();
					EntryPoint.getTopView().getNodeBranchFilterPanel().reload();
					EntryPoint.getFlatGraph().performLayout();




				} else {

					UserLoginDialog.this.user = (User) DatabaseUtils.findByUniqueName(User.class,
							user.getName());

					if (UserLoginDialog.this.user == null) {
						throw new CustomRuntimeException(
								"user is invalid, name or password is incorrect");
					}

					UserRole associatedRole = null;
					if (UserLoginDialog.this.user.getAssociatedRoles() != null
							&& UserLoginDialog.this.user.getAssociatedRoles().size() > 0) {

						associatedRole = (UserRole) UserLoginDialog.this.user.getAssociatedRoles()
								.toArray()[0];
					} else {
						throw new CustomRuntimeException(
								"user associated role does not exist");
					}

					OntologyClass associatedOntologyClass = null;

					if (associatedRole != null
							&& associatedRole.getAssociatedOntologyClasses() != null
							&& associatedRole.getAssociatedOntologyClasses().size() > 0) {
						associatedOntologyClass = (OntologyClass) associatedRole
								.getAssociatedOntologyClasses().toArray()[0];

					} else {
						throw new CustomRuntimeException(
								"user associated ontology class does not exist");
					}

					// get the branch node

					DefaultGraphNode branchNode = null;
					branchNode = (DefaultGraphNode) EntryPoint.getGraphModel()
							.findGraphNode(associatedOntologyClass);

					// set the check boxes of filter panel
					// set the filter, then call the filter panel to reload to refresh
					// check status
					EntryPoint.setCurrentLayoutStyle(EntryPoint.treeLayoutStyle);
					EntryPoint.getFilterManager().getNodeBranchFilter()
							.setAllBranchesInvisible();
					EntryPoint.getFilterManager().getNodeBranchFilter()
							.setNodeBranchVisible(branchNode, true);
					Collection levels = EntryPoint.getFilterManager()
							.getNodeLevelFilter().getNodeLevels();
					for (Object level : levels) {
						EntryPoint.getFilterManager().getNodeLevelFilter()
								.setNodeLevelVisible(level, true);
					}
					EntryPoint.getTopView().getNodeLevelFilterPanel().reload();

					// make filter panel un-editable
					EntryPoint.getTopView().getNodeBranchFilterPanel()
							.setEnabled(false);
					EntryPoint.getTopView().getNodeBranchFilterPanel().reload();
					EntryPoint.getFlatGraph().performLayout();
				}

			}
		});



		this.dispose();

	}

	@UiAction
	@UiComesAfter("login")
	public void cancel() {

	}

	public static void main(String[] args) {
		List<OntologyClass> classes = DatabaseUtils
				.findAll(OntologyClass.class);

		// DatabaseUtils.getSession().beginTransaction();
		// for(OntologyClass cls : classes){
		// if(EntryPoint.getOntologyRelationshipService().findChildren(cls).size()
		// == 0){
		// cls.setHasTable(true);
		// cls.setHasDashboard(false);
		// }else{
		// cls.setHasTable(false);
		// cls.setHasDashboard(true);
		// }
		// }
		// DatabaseUtils.getSession().getTransaction().commit();

		List<User> users = DatabaseUtils.findAll(User.class);
		DatabaseUtils.getSession().beginTransaction();
		for (User user : users) {
			DatabaseUtils.getSession().delete(user);
		}
		DatabaseUtils.getSession().getTransaction().commit();

		users = DatabaseUtils.findAll(User.class);

		addUser("ProcessManagerA", "ProcessManager",
				"Business Process Management");
		addUser("ProgramManagerA", "ProgramManager", "Program");
		addUser("ObligationManagerA", "ObligationManager", "Obligation");
		users = DatabaseUtils.findAll(User.class);

		new EntryPoint().start();

	}

	private static void addUser(String userName, String roleName,
			String ontologyClassName) {

		DatabaseUtils.getSession().beginTransaction();
		User user1 = new User(userName);
		UserRole role = new UserRole(roleName);

		OntologyClass ontologyClass = null;

		ontologyClass = (OntologyClass) DatabaseUtils.findByUniqueName(
				OntologyClass.class, ontologyClassName);

		if (ontologyClass != null) {
			DatabaseUtils.getSession().save(ontologyClass);
			role.getAssociatedOntologyClasses().add(ontologyClass);
			role.persist();
			user1.getAssociatedRoles().add(role);
			user1.persist();
		} else {
			throw new CustomRuntimeException("ontology Class not valid");
		}
		DatabaseUtils.getSession().getTransaction().commit();
	}

}
