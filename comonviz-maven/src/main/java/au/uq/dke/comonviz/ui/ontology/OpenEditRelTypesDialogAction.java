package au.uq.dke.comonviz.ui.ontology;

import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.actions.CajunAction;
import au.uq.dke.comonviz.ui.ontology.refact.ClassesListDialog;
import au.uq.dke.comonviz.ui.ontology.refact.RelTypeListDialog;
import ca.uvic.cs.chisel.cajun.resources.ResourceHandler;

public class OpenEditRelTypesDialogAction extends CajunAction {
	private static final long serialVersionUID = 1L;

	private static final String ACTION_NAME = "edit relationship types";

	public OpenEditRelTypesDialogAction() {
		super(ACTION_NAME, new ImageIcon(OpenEditRelTypesDialogAction.class.getResource("/open.gif")));
	}

	@Override
	public void doAction() {
		new RelTypeListDialog();
	}
}
