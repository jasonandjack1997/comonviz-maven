package au.uq.dke.comonviz.ui.data;

import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JDialog;

public class DialogUtils {
	
	public static JDialog getParentDialog(JComponent component){
		Container parent = component.getRootPane();
		while (!(parent instanceof JDialog) && parent !=null) {
			parent = parent.getParent();
		}
		return (JDialog) parent;
	
	}

}
