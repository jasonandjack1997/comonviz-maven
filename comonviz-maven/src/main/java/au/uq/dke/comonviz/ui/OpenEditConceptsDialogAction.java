package au.uq.dke.comonviz.ui;

import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.actions.CajunAction;
import au.uq.dke.comonviz.ui.ontologyRefactor.ClassesListDialog;
import ca.uvic.cs.chisel.cajun.resources.ResourceHandler;

public class OpenEditConceptsDialogAction extends CajunAction {
	private static final long serialVersionUID = 1L;

	private static final String ACTION_NAME = "edit ontology classes";

	public OpenEditConceptsDialogAction() {
		super(ACTION_NAME, new ImageIcon(OpenEditConceptsDialogAction.class.getResource("/open.gif")));
	}

	@Override
	public void doAction() {
		new ClassesListDialog(EntryPoint.getOntologyTreeRoot());
	}
}
