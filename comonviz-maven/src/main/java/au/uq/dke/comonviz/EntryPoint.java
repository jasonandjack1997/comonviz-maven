package au.uq.dke.comonviz;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uk.ac.manchester.cs.bhig.util.MutableTree;
import au.uq.dke.comonviz.actions.LayoutAction;
import au.uq.dke.comonviz.filter.FilterManager;
import au.uq.dke.comonviz.handler.NodeExpandCollapseListener;
import au.uq.dke.comonviz.misc.OwlApi;
import au.uq.dke.comonviz.model.AnnotationManager;
import au.uq.dke.comonviz.treeUtils.TreeInfoManager;
import au.uq.dke.comonviz.ui.StyleManager;
import ca.uvic.cs.chisel.cajun.constants.LayoutConstants;
import ca.uvic.cs.chisel.cajun.graph.AbstractGraph;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import database.service.OntologyAxiomService;
import database.service.OntologyClassService;
import database.service.OntologyRelationshipService;

public class EntryPoint {
	private final String dataBaseFileName = "database.h2.db";
	private final String outerDatabaseDirectory = "C:/comonviz/";
	private final File outerDatabaseFile = new File(outerDatabaseDirectory
			+ dataBaseFileName);
	private static LayoutAction radicalLayoutAction;

	static ConfigurableApplicationContext ctx;
	private static OntologyAxiomService ontologyAxiomService;
	private static OntologyClassService ontologyClassService;
	private static OntologyRelationshipService ontologyRelationshipService;

	public static OntologyAxiomService getOntologyAxiomService() {
		return ontologyAxiomService;
	}

	public static OntologyClassService getOntologyClassService() {
		return ontologyClassService;
	}

	public static OntologyRelationshipService getOntologyRelationshipService() {
		return ontologyRelationshipService;
	}

	public static OWLOntology ontology = null;
	private final String internalOWLFilePath = "/COMON_v8_HenryNewRel.owl";

	/** the graph object, performs layouts and renders the model */
	private static FlatGraph flatGraph;

	/** the model representation of the graph, nodes and edges */
	private static NewGraphModel graphModel;

	private static TopView topView;

	private static GraphController graphController;

	private static JFrame jFrame;
	private static FilterManager filterManager;

	private static StyleManager styleManager;

	public static FilterManager getFilterManager() {
		return filterManager;
	}

	public static JFrame getjFrame() {
		return jFrame;
	}

	private static MutableTree ontologyTree;

	public static Dimension frameSize;

	private static AnnotationManager annotationManager;

	public static AnnotationManager getAnnotationManager() {
		return annotationManager;
	}

	/**
     * 
     */
	@SuppressWarnings({ "unused", "static-access", "static-access" })
	public void start() {

		try {
			initDataBase();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ontologyAxiomService = (OntologyAxiomService) ctx
				.getBean("ontologyAxiomService");
		ontologyClassService = (OntologyClassService) ctx
				.getBean("ontologyClassService");
		ontologyRelationshipService = (OntologyRelationshipService) ctx
				.getBean("ontologyRelationshipService");

		filterManager = new FilterManager();
		jFrame = new JFrame("CoMOnViz");
		graphModel = new NewGraphModel();
		this.getFilterManager().getArcTypeFilter().updateArcTypes();
		flatGraph = new FlatGraph();
		topView = new TopView();
		annotationManager = new AnnotationManager();
		// graphController = new GraphController();
		radicalLayoutAction = new LayoutAction(LayoutConstants.LAYOUT_RADIAL,
				null, new RadialLayoutAlgorithm(1), EntryPoint.getFlatGraph());

		graphModel.init();
		topView.initialize();

		styleManager = StyleManager.getStyleManager();

		flatGraph.addInputEventListener(new NodeExpandCollapseListener());
		filterManager.addListeners();
		graphModel.addListeners();
		topView.addListeners();
		flatGraph.addListeners();

		this.filterManager.getNodeLevelFilter().updateNodeLevels();
		this.topView.hideSubclassArcType();
		this.topView.getArcTypeFilterPanel().reload();
		this.topView.getNodeLevelFilterPanel().reload();

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Display the window.
		jFrame.setMinimumSize(new Dimension(800, 600));
		jFrame.pack();
		jFrame.setExtendedState(jFrame.getExtendedState()
				| JFrame.MAXIMIZED_BOTH);
		jFrame.setVisible(true);

		this.flatGraph.performLayout();
		// radicalLayoutAction.doAction();
		// LayoutAction layoutAction = ((AbstractGraph)
		// graphController.getGraph())
		// .getLayout(LayoutConstants.LAYOUT_RADIAL);

		// URL ontologyURL = null;
		// try {
		//
		// ontologyURL = this.getClass().getResource(internalOWLFilePath);
		// //ontologyURL = this.getClass().getResource("/annotationTest.owl");
		// } catch (NullPointerException e3) {
		// e3.printStackTrace();
		// }
		//
		// try {
		// loadOntologyFile(ontologyURL.toURI());
		// } catch (URISyntaxException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public static LayoutAction getRadicalLayoutAction() {
		return radicalLayoutAction;
	}

	public static OWLOntology getOntology() {
		return ontology;
	}

	public static FlatGraph getFlatGraph() {
		return flatGraph;
	}

	public static NewGraphModel getGraphModel() {
		return graphModel;
	}

	public static TopView getTopView() {
		return topView;
	}

	public static GraphController getGraphController() {
		return graphController;
	}

	public static JFrame getFrame() {
		return jFrame;
	}

	public static MutableTree getOntologyTree() {
		return ontologyTree;
	}

	public static void loadOntologyFile(URI uri) {

		OwlApi owlapi = new OwlApi();
		try {
			ontology = owlapi.openOntology(uri);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "This is an invalid owl file!",
					"Error", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (OWLOntologyCreationException | OWLOntologyStorageException
				| IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Create and set up the window.
		annotationManager = new AnnotationManager();
		graphController.getModel().owlOntology = EntryPoint.ontology;
		// LayoutAction layoutAction =
		// ((AbstractGraph)gc.getGraph()).getLayout(LayoutConstants.LAYOUT_SPRING);
		LayoutAction layoutAction = ((AbstractGraph) graphController.getGraph())
				.getLayout(LayoutConstants.LAYOUT_RADIAL);

		for (OWLClass cls : ontology.getClassesInSignature()) {
			graphController.getModel().generateNodesAndArcs(
					cls,
					((AbstractGraph) graphController.getGraph())
							.getFilterManager());
		}

		Collection nodes = null;
		nodes = graphController.getModel().getAllNodes();
		TreeInfoManager treeInfoManager = TreeInfoManager.getTreeManager();
		treeInfoManager.generateTreeInfo(nodes);

		ontologyTree = treeInfoManager.getTreeRoot();
		DefaultMutableTreeNode root = null;
		root = TreeInfoManager.convertFromManchesterToUITreeNode(ontologyTree);
		TopView topView = graphController.getView();
		topView.getTreeModel().setRoot(root);

		// StyleManager.initStyleManager(treeInfoManager.getBranchNodes(),
		// graphController.getModel().getArcTypes());

		// for (OWLClass cls : ontology.getClassesInSignature()) {
		//
		// if (treeInfoManager.getLevel(cls) >= 2) {
		// graphController.getModel().removeNode(cls);
		// } else {
		// graphController.getModel().hideAllDesendants(
		// graphController.getModel().getNode(cls));
		// graphController.getModel().show(cls,
		// EntryPoint.getFlatGraph().getFilterManager());
		// }
		// }

		topView.changeDividerLocation();
		layoutAction.doAction();

		Collection arcs = EntryPoint.getGraphModel().getAllArcs();
		Collection visableArcs = EntryPoint.getGraphModel().getVisibleArcs();
		Collection arcTypes = EntryPoint.getGraphModel().getArcTypes();
		return;
	}

	public static void main(String[] args) throws URISyntaxException,
			IOException {
		EntryPoint entryPoint = new EntryPoint();

		entryPoint.start();

	}

	private void initDataBase() throws URISyntaxException, IOException {
//		File innerDatabaseResourceFile = new File(this.getClass()
//				.getResource("/database/" + dataBaseFileName).toURI());
		
		InputStream innerSourceFileStream = this.getClass()
				.getResourceAsStream("/database/" + dataBaseFileName);
		FileUtils.forceMkdir(new File(outerDatabaseDirectory));
		if (outerDatabaseFile.exists()) {
//			if (FileUtils.isFileNewer(innerDatabaseResourceFile,
//					outerDatabaseFile)) {
//				FileUtils
//						.copyFile(innerDatabaseResourceFile, outerDatabaseFile);
//			}
		} else {
			FileUtils.copyInputStreamToFile(innerSourceFileStream, outerDatabaseFile);
			//FileUtils.copyFile(innerDatabaseResourceFile, outerDatabaseFile);
		}
	}
}
