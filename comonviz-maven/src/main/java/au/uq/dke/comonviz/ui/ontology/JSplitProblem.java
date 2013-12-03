package au.uq.dke.comonviz.ui.ontology;

import java.awt.*;
import javax.swing.*;

public class JSplitProblem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JSplitPane mainSplittedPane;
	JPanel bottomPanel = new JPanel();

	public JSplitProblem() {
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		JPanel red = new JPanel();
		red.setBackground(Color.red);
		leftPanel.add(red);
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JPanel blue = new JPanel();
		blue.setBackground(Color.blue);
		rightPanel.add(blue);
		upperPanel.add(leftPanel);
		upperPanel.add(rightPanel);
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.green);
		bottomPanel.setPreferredSize(new Dimension(100, 300));
		bottomPanel.setMinimumSize(new Dimension(200, 300));

		mainSplittedPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				upperPanel, bottomPanel);
		mainSplittedPane.setOneTouchExpandable(true);

		add(mainSplittedPane);
		setPreferredSize(new Dimension(400, 300));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		pack();
		restoreDefaults();
	}

	private void restoreDefaults() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// mainSplittedPane.setDividerLocation(mainSplittedPane.getSize().height
				// /2);
				bottomPanel.setMinimumSize(new Dimension(200, 300));
				mainSplittedPane.setDividerLocation(0.9f);
				bottomPanel.setMinimumSize(new Dimension(200, 300));
			}
		});
	}

	public static void main(String[] args) {
		JSplitProblem jSplitProblem = new JSplitProblem();
	}
}
