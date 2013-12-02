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
import au.uq.dke.comonviz.ui.ontologyRefactor.RelTypeListDialog;
import au.uq.dke.comonviz.ui.ontologyRefactor.RelationshipListDialog;
import ca.uvic.cs.chisel.cajun.resources.ResourceHandler;

public class OpenEditRelationshipsDialogAction extends CajunAction {
	private static final long serialVersionUID = 1L;

	private static final String ACTION_NAME = "edit relationships";

	public OpenEditRelationshipsDialogAction() {
		super(ACTION_NAME, new ImageIcon(OpenEditRelationshipsDialogAction.class.getResource("/open.gif")));
	}

	@Override
	public void doAction() {
		new RelationshipListDialog();
	}
}
