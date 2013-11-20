package au.uq.dke.comonviz.graph.node;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.zest.layouts.constraints.BasicEntityConstraint;
import org.eclipse.zest.layouts.constraints.LabelLayoutConstraint;
import org.eclipse.zest.layouts.constraints.LayoutConstraint;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;

import au.uq.dke.comonviz.EntryPoint;
import au.uq.dke.comonviz.ui.StyleManager;
import ca.uvic.cs.chisel.cajun.graph.arc.GraphArc;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNodeStyle;
import database.model.ontology.OntologyClass;
import edu.umd.cs.piccolo.PCamera;
import edu.umd.cs.piccolo.PLayer;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.nodes.PText;
import edu.umd.cs.piccolo.util.PBounds;
import edu.umd.cs.piccolo.util.PPaintContext;
import edu.umd.cs.piccolox.util.PFixedWidthStroke;

/**
 * Default graph node implementation. Displays some text and possible an
 * image/icon.
 * 
 * @author Chris Callendar
 * @since 30-Oct-07
 */
public class DefaultGraphNode extends PNode implements GraphNode {

	@Override
	public boolean equals(Object obj) {
		OntologyClass thisCls = (OntologyClass) this.getUserObject();
		OntologyClass tgtCls = (OntologyClass) ((DefaultGraphNode)obj).getUserObject();
		return thisCls.equals(tgtCls);
		
		// TODO Auto-generated method stub
	}

	private static final long serialVersionUID = 3223950711940456476L;

	protected static final int MAX_TEXT_CHARS = 10;
	protected static final int MAX_TOOLTIP_CHARS_IN_A_LINE = 50;
	protected static final int MAX_LINES = 5;

	private Object userObject;
	private String fullText;
	private Object type;
	private String tooltip;

	private Map<Icon, PImage> overlayIconMap = new HashMap<Icon, PImage>();
	private Collection<Icon> overlayIcons = new ArrayList<Icon>();

	private GraphTextNode textNode;
	private PImage pImage;
	private int iconWidth = 0;
	private int iconHeight = 0;

	private GraphNodeStyle style;

	private boolean selected;
	private boolean highlighted;
	private boolean matching;
	private boolean fixedLocation;

	private double xInLayout = 0;
	private double yInLayout = 0;
	protected double wInLayout = 0;
	protected double hInLayout = 0;
	private Object layoutInformation;

	private double xFactor, yFactor;
	private Object graphData;

	private List<ChangeListener> changeListeners;

	private Collection<GraphArc> arcs;

	private Ellipse2D ellipse;
	private double BOUNDS_ELLIPSE_FACTOR = 3.0; // the bound is bigger than the
												// text
	// size so we can draw a ellipse in
	private double ELLIPSE_FACTOR = 1.2f; // the distance eclipse is within each
											// side of the bounds

	private HiddenChildrenCountIcon childrenCountIcon;

	private final static int MAX_TOOLTIP_LINES = 20;

	// This nodes uses an internal Ellipse2D to define its shape.
	public Ellipse2D getEllipse() {
		if (ellipse == null)
			ellipse = new Ellipse2D.Double();
		return ellipse;
	}

	public DefaultGraphNode(Object userObject) {
		this(userObject, String.valueOf(userObject));
	}

	public DefaultGraphNode(Object userObject, String text) {
		this(userObject, text, null);
	}

	public DefaultGraphNode(Object userObject, String text, Icon icon) {
		this(userObject, text, icon, null);
	}

	public DefaultGraphNode(Object userObject, String text, Icon icon,
			Object type) {
		super();

		this.userObject = userObject;

		this.changeListeners = new ArrayList<ChangeListener>();

		// this.style = new DefaultGraphNodeStyle();
		this.style = new CustomGraphNodeStyle1();
		this.selected = false;
		this.highlighted = false;
		this.matching = false;

		this.arcs = new ArrayList<GraphArc>();

		this.setPickable(true);
		this.setChildrenPickable(false);

		textNode = new GraphTextNode();
		textNode.setHorizontalAlignment(Component.CENTER_ALIGNMENT);
		// make this node match the text size
		textNode.setConstrainWidthToTextWidth(true);
		textNode.setConstrainHeightToTextHeight(true);
		textNode.setPickable(false);

		childrenCountIcon = new HiddenChildrenCountIcon(this, "1");
		childrenCountIcon.setHorizontalAlignment(Component.CENTER_ALIGNMENT);
		// make this node match the text size
		childrenCountIcon.setConstrainWidthToTextWidth(true);
		childrenCountIcon.setConstrainHeightToTextHeight(true);
		childrenCountIcon.setPickable(false);

		addChild(textNode);
		addChild(childrenCountIcon);

		setText(text);
		setType(type);
		updateBounds();

	}

	public void removeChangeListener(ChangeListener l) {
		changeListeners.remove(l);
	}

	public void addChangeListener(ChangeListener l) {
		changeListeners.add(l);
	}

	public Object getUserObject() {
		return userObject;
	}

	public GraphNodeStyle getNodeStyle() {
		return style;
	}

	public void setNodeStyle(GraphNodeStyle style) {
		if ((style != null) && (this.style != style)) {
			// this.style = style;
			invalidateFullBounds();
			invalidatePaint();
		}
	}

	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = (type == null ? UNKNOWN_TYPE : type);
	}

	public boolean isVisible() {
		return getVisible();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		// hide or show the arcs for this node
		for (GraphArc arc : arcs) {
			// this method handles whether or not to show the arc
			// checks if the src and dest nodes are visible
			arc.setVisible(visible);
		}
	}

	public Collection<GraphArc> getArcs() {
		return arcs;
	}

	public Collection<GraphArc> getArcs(boolean incoming, boolean outgoing) {
		Collection<GraphArc> graphArcs;
		if (incoming && outgoing) {
			graphArcs = getArcs();
		} else if (!incoming && !outgoing) {
			graphArcs = Collections.emptyList();
		} else {
			graphArcs = new ArrayList<GraphArc>();
			for (GraphArc arc : getArcs()) {
				if (incoming && (arc.getDestination() == this)) {
					graphArcs.add(arc);
				} else if (outgoing && (arc.getSource() == this)) {
					graphArcs.add(arc);
				}
			}
		}
		return graphArcs;
	}

	public void addArc(GraphArc arc) {
		if (!this.arcs.contains(arc)) {
			this.arcs.add(arc);
		}
	}

	public void removeArc(GraphArc arc) {
		this.arcs.remove(arc);
	}

	public Collection<GraphNode> getConnectedNodes() {
		ArrayList<GraphNode> connectedNodes = new ArrayList<GraphNode>();
		for (GraphArc arc : getArcs()) {
			GraphNode src = arc.getSource();
			GraphNode dest = arc.getDestination();
			GraphNode nodeToAdd = null;
			if (this == src) {
				nodeToAdd = dest;
			} else if (this == dest) {
				nodeToAdd = src;
			}
			if ((nodeToAdd != null) && !connectedNodes.contains(nodeToAdd)) {
				connectedNodes.add(nodeToAdd);
			}
		}
		return connectedNodes;
	}

	public boolean hasAttribute(Object key) {
		return (getAttribute(key) != null);
	}

	public void removeAttribute(Object key) {
		addAttribute(key, null);
	}

	public String getText() {
		return fullText;
	}

	public void setText(String s) {
		if (s == null) {
			s = "";
		}

		if (s.contains("#")) {
			s = s.substring(s.lastIndexOf('#') + 1);
		}
		this.fullText = s;
		// TODO let user choose between eliding the label and splitting into
		// lines?

		textNode.setText(splitTextIntoLines(s, MAX_LINES, MAX_TEXT_CHARS));
		// updateBounds();
	}

	/**
	 * Restricts the number of characters in the text node. If the string is too
	 * long it is chopped and appended with "...".
	 * 
	 * @param text
	 *            the string to possibly elide
	 * @return the elided string, or the original if text isn't longer than the
	 *         max allowed chars
	 */
	protected String elideText(String text, int maxCharsPerLine) {
		if (text.length() > maxCharsPerLine) {
			return new String(text.substring(0, maxCharsPerLine).trim() + "...");
		}
		return text;
	}

	/**
	 * Splits the text into lines. Attempts to split at word breaks if possible.
	 * Also puts a cap on the max number of lines.
	 */
	protected String splitTextIntoLines(String text, int maxLines,
			int maxCharsPerLine) {
		text = text.replace('_', ' ').trim();
		StringBuffer buffer = new StringBuffer(text.length() + 10);
		if (text.length() > maxCharsPerLine) {
			int lines = 0;
			while ((text.length() > 0) && (lines < maxLines)) {
				// base case #1 - text is short
				if (text.length() < maxCharsPerLine) {
					buffer.append(text);
					break;
				}
				// base case #2 - added max lines
				if ((lines + 1) == maxLines) {
					// elide the remaining text (s) instead of just the current
					// line
					buffer.append(elideText(text, maxCharsPerLine));
					break;
				}

				// find a space and break on it
				int end = findWhiteSpace(text, maxCharsPerLine);
				if (end == -1) {
					end = Math.min(text.length(), maxCharsPerLine);
				}
				String line = text.substring(0, end).trim();
				if (line.length() == 0) {
					break;
				}

				buffer.append(line);
				buffer.append('\n');
				lines++;
				text = text.substring(end).trim();
			}
			return buffer.toString().trim();
		}
		return text;
	}

	private int findWhiteSpace(String s, int end) {
		int ws = -1;
		// look 2 characters past the end for a space character
		// and work backwards
		for (int i = Math.min(s.length() - 1, end + 2); i >= 0; i--) {
			if (Character.isWhitespace(s.charAt(i))) {
				ws = i;
				break;
			}
		}
		return ws;
	}

	@Override
	public String toString() {
		return ((OntologyClass)this.getUserObject()).getName();

	}

	public String getTooltip() {
		if (tooltip == null) {
			Collection<OWLAnnotation> owlAnnotationSet = ((OWLClass) userObject)
					.getAnnotations(EntryPoint.ontology);
			if (owlAnnotationSet.size() != 0) {
				String annotation = ((OWLAnnotation) owlAnnotationSet.toArray()[0])
						.getValue().toString();
				annotation = annotation.substring(1, annotation.length() - 1);


				annotation = this.splitTextIntoLines(annotation,
						MAX_TOOLTIP_LINES, MAX_TOOLTIP_CHARS_IN_A_LINE);

//				annotation = EntryPoint.getAnnotationManager()
//						.getStylizedAnnotation(annotation);
				

				return annotation;

			}
		}
		return "";
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public void setIcon(Icon icon) {
		if (pImage != null) {
			pImage.removeAllChildren();
			pImage.removeFromParent();
			pImage.getImage().flush();
		}
		if ((icon != null) && (icon instanceof ImageIcon)) {
			iconWidth = icon.getIconWidth();
			iconHeight = icon.getIconHeight();
			pImage = new PImage(((ImageIcon) icon).getImage());
			pImage.setPickable(false);
			addChild(pImage);
			updateBounds();
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		if (this.selected != selected) {
			this.selected = selected;
			updateArcs();
			textNode.invalidatePaint();
			childrenCountIcon.invalidatePaint();
			invalidatePaint();
		}
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		if (this.highlighted != highlighted) {
			this.highlighted = highlighted;
			bubbleNode();
			textNode.invalidatePaint();
			childrenCountIcon.invalidatePaint();
			invalidatePaint();
		}
	}

	public boolean isMatching() {
		return matching;
	}

	public void setMatching(boolean matching) {
		if (this.matching != matching) {
			this.matching = matching;
			invalidatePaint();
		}
	}

	/**
	 * Scales the node back to normal size if the canvas is currently scaled
	 * below the regular size.
	 */
	protected void bubbleNode() {
		PCamera camera = ((PLayer) this.getParent()).getCamera(0);
		double viewScale = camera.getViewScale();

		if (highlighted) {
			if (viewScale < 1.0) {
				double scaleFactor = 1.0 / viewScale;

				double unscaledWidth = this.getGlobalBounds().width;
				double unscaledHeight = this.getGlobalBounds().height;
				double scaledWidth = this.getGlobalBounds().width * viewScale;
				double scaledHeight = this.getGlobalBounds().height * viewScale;

				this.scaleAboutPoint(scaleFactor, getX(), getY());

				xFactor = (unscaledWidth - scaledWidth) / 2;
				yFactor = (unscaledHeight - scaledHeight) / 2;
				this.translate(-1 * xFactor, -1 * yFactor);
			}
		} else {
			if (xFactor > 0) {
				this.translate(xFactor, yFactor);
				this.scaleAboutPoint(1.0 / getScale(), getX(), getY());
			}

			xFactor = 0;
			yFactor = 0;
		}
	}

	private void updateArcs() {
		for (GraphArc arc : arcs) {
			if (isSelected()) {
				arc.setSelected(true);
			} else {
				GraphNode src = arc.getSource();
				GraphNode dest = arc.getDestination();
				if (src == dest) {
					arc.setSelected(false);
				} else if (this == src) {
					arc.setSelected(dest.isSelected());
				} else if (this == dest) {
					arc.setSelected(src.isSelected());
				}
			}
		}
	}

	private void fireChangeListeners() {
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : changeListeners) {
			listener.stateChanged(event);
		}
	}

	private void updateArcLocations() {
		for (GraphArc arc : arcs) {
			arc.updateArcPath();
		}
	}

	/**
	 * Sets the bounds of this node based on the icon and text size. Takes into
	 * consideration the maximum node width too.
	 */
	private void updateBounds() {
		PBounds textBounds = textNode.getBounds();

		double tw = textBounds.getWidth();
		double th = textBounds.getHeight();

		if (tw > th) {
			getEllipse().setFrameFromCenter(textBounds.getCenterX(),
					textBounds.getCenterY(), textBounds.getX() - 5,
					textBounds.getY() - (tw - th) / 2 - 5);

		} else {
			getEllipse().setFrameFromCenter(textBounds.getCenterX(),
					textBounds.getCenterY(),
					textBounds.getX() - (th - tw) / 2 - 5,
					textBounds.getY() - 5);

		}

		double ew = getEllipse().getWidth();
		double eh = getEllipse().getHeight();

		double bw = ew * BOUNDS_ELLIPSE_FACTOR;
		double bh = eh * BOUNDS_ELLIPSE_FACTOR;

		this.setSize(bw, bh);

	}

	// This method is important to override so that the geometry of
	// the ellipse stays consistent with the bounds geometry.
	public boolean setBounds(double x, double y, double width, double height) {
		if (super.setBounds(x, y, width, height)) {

			double centerX = this.getCenterX();
			double centerY = this.getCenterY();

			// PBounds textBounds = textNode.getBounds();
			// double tw = textBounds.getWidth();
			// double th = textBounds.getHeight();

			textNode.setBounds(centerX - textNode.getWidth() / 2, centerY
					- textNode.getHeight() / 2, textNode.getWidth(),
					textNode.getHeight());

			getEllipse().setFrame(centerX - getEllipse().getWidth() / 2,
					centerY - getEllipse().getHeight() / 2,
					getEllipse().getWidth(), getEllipse().getHeight());

			// this.setBounds(x - getWidth()/2, y - getHeight()/2, getWidth(),
			// getHeight());

			// if (tw > th) {
			// getEllipse().setFrameFromCenter(textBounds.getCenterX(),
			// textBounds.getCenterY(), textBounds.getX() - 5,
			// textBounds.getY() - (tw - th) / 2 - 5);
			//
			// } else {
			// getEllipse().setFrameFromCenter(textBounds.getCenterX(),
			// textBounds.getCenterY(),
			// textBounds.getX() - (th - tw) / 2 - 5,
			// textBounds.getY() - 5);
			//
			// }
			//
			// double ew = getEllipse().getWidth();
			// double eh = getEllipse().getHeight();
			//
			// double w = ew * BOUNDS_ELLIPSE_FACTOR;
			// double h = eh * BOUNDS_ELLIPSE_FACTOR;
			//
			// textNode.setBounds(getX() + (BOUNDS_ELLIPSE_FACTOR / 2 - 0.5) *
			// tw,
			// getY() + (BOUNDS_ELLIPSE_FACTOR / 2 - 0.5) * th, tw, th);

			double cw = childrenCountIcon.getWidth();
			double ch = childrenCountIcon.getHeight();
			double d = Math.max(cw, ch);
			// childrenCountIcon.setBounds(getX(), getY() + getHeight()/2, d,
			// d);
			childrenCountIcon.setBounds(getEllipse().getX() - d / 2,
					getEllipse().getY() + getEllipse().getHeight() / 2 - d / 2,
					d, d);

			updateArcLocations();
			invalidatePaint();

			fireChangeListeners();
			return true;
		}
		return false;
	}

	// Non rectangular subclasses need to override this method so
	// that they will be picked correctly and will receive the
	// correct mouse events.
	public boolean intersects(Rectangle2D aBounds) {
		return getEllipse().intersects(aBounds);
	}

	public boolean setLocation(double x, double y) {
		setHighlighted(false);

		return setBounds(x, y, getWidth(), getHeight());
	}

	public boolean setSize(double w, double h) {
		return setBounds(getX(), getY(), w, h);
	}

	public double getCenterX() {
		return (getX() + (getWidth() / 2));
	}

	public double getCenterY() {
		return (getY() + (getHeight() / 2));
	}

	public Object getGraphData() {
		return graphData;
	}

	public void setGraphData(Object data) {
		this.graphData = data;
	}

	public double getXInLayout() {
		return xInLayout;
	}

	public double getYInLayout() {
		return yInLayout;
	}

	public double getWidthInLayout() {
		// return wInLayout;
		return getBounds().width;
	}

	public double getHeightInLayout() {
		// return hInLayout;
		return getBounds().height;
	}

	public void setLocationInLayout(double x, double y) {
		xInLayout = x;
		yInLayout = y;
	}

	public Object getLayoutInformation() {
		return layoutInformation;
	}

	public void setLayoutInformation(Object layoutInformation) {
		this.layoutInformation = layoutInformation;
	}

	public void setSizeInLayout(double width, double height) {
		wInLayout = width;
		hInLayout = height;
	}

	public boolean hasPreferredLocation() {
		return false;
	}

	/**
	 * Populate the specified layout constraint
	 */
	public void populateLayoutConstraint(LayoutConstraint constraint) {
		if (constraint instanceof LabelLayoutConstraint) {
			LabelLayoutConstraint labelConstraint = (LabelLayoutConstraint) constraint;
			labelConstraint.label = fullText;
			labelConstraint.pointSize = 18;
		} else if (constraint instanceof BasicEntityConstraint) {
			BasicEntityConstraint basicEntityConstraint = (BasicEntityConstraint) constraint;
			if (this.hasPreferredLocation()) {
				basicEntityConstraint.hasPreferredLocation = true;
				basicEntityConstraint.preferredX = this.getX();
				basicEntityConstraint.preferredY = this.getY();
			}
		}
	}

	public int compareTo(Object o) {
		if (o instanceof DefaultGraphNode) {
			DefaultGraphNode node = (DefaultGraphNode) o;
			return this.fullText.compareToIgnoreCase(node.fullText);
		}
		return 0;
	}

	@Override
	protected void paint(PPaintContext paintContext) {
		Graphics2D g2 = paintContext.getGraphics();

		// this.setB
		// shrink the bounds slightly to avoid painting outside the bounds

		// can't be null

		// these can be null

		Paint bg = style.getBackgroundPaint(this);
		Paint borderPaint = style.getBorderPaint(this);
		Stroke borderStroke = style.getBorderStroke(this);

//		Paint bg = Color.red;
//		Paint borderPaint = Color.red;
//		Stroke borderStroke = new PFixedWidthStroke(3f);


		Shape drawShape = getEllipse();

		// 1. paint the background shape
		if (bg != null) {
			g2.setPaint(bg);
			g2.fill(drawShape);
		}

		// 2. paint the border
		if ((borderPaint != null) && (borderStroke != null)) {
			g2.setPaint(borderPaint);
			g2.setStroke(borderStroke);
			g2.draw(drawShape);
		}

		childrenCountIcon.setBounds(
				getEllipse().getX() - childrenCountIcon.getWidth() / 2,
				getEllipse().getY() + getEllipse().getHeight() / 2
						- childrenCountIcon.getHeight() / 2,
				childrenCountIcon.getWidth(), childrenCountIcon.getHeight());

		super.paint(paintContext);
		// addOverlayIcons(style.getOverlayIcons(this));
	}

	/**
	 * @depriate
	 * @param paintContext
	 */

	/**
	 * If necessary, creates the overlay icons as PImage's and adds them to this
	 * node as a child object. If it is already created, the overlayIcon is
	 * repositioned.
	 * 
	 * @param icon
	 *            The icon to set as the overlayIcon.
	 */
	private void addOverlayIcons(Collection<Icon> icons) {
		if (icons == null || !icons.equals(overlayIcons)) {
			for (PImage overlayIcon : overlayIconMap.values()) {
				removeChild(overlayIcon);
			}
			overlayIconMap.clear();
		}
		if (icons != null) {
			for (Icon icon : icons) {
				PImage overlayIcon = overlayIconMap.get(icon);
				if (overlayIcon == null && icon != null) {
					overlayIcon = new PImage(((ImageIcon) icon).getImage());
					overlayIcon.setPickable(false);
					addChild(overlayIcon);
					overlayIconMap.put(icon, overlayIcon);
				}

				if (overlayIcon != null) {
					Point2D iconPosition = style.getOverlayIconPosition(this,
							icon);
					overlayIcon.setX(iconPosition.getX());
					overlayIcon.setY(iconPosition.getY());
				}
			}
		}

		overlayIcons = icons;
	}

	private GradientPaint updateGradientPaintPoints(GradientPaint gp) {
		int x = (int) getX();
		int y = (int) getY();
		int h = (int) getHeight();
		GradientPaint gradient = new GradientPaint(x, y, gp.getColor1(), x, y
				+ h, gp.getColor2(), gp.isCyclic());
		return gradient;
	}

	class GraphTextNode extends PText {
		private static final long serialVersionUID = -871571524212274580L;

		private boolean ignoreInvalidatePaint = false;

		@Override
		public Font getFont() {
			Font font = style.getFont(DefaultGraphNode.this);
			if (font == null) {
				font = DEFAULT_FONT;
			}
			font = font
					.deriveFont(StyleManager.getStyleManager().DEFAULT_NODE_TEXT_FONT_SIZE);
			return font;
		}

		@Override
		public Paint getTextPaint() {
			Paint paint = style.getTextPaint(DefaultGraphNode.this);
			if (paint == null) {
				paint = Color.black;
			}
			return paint;
		}

		@Override
		protected void paint(PPaintContext paintContext) {
			// update the text paint - the super paint method doesn't call our
			// getTextPaint() method
			Paint p = getTextPaint();
			if (!p.equals(super.getTextPaint())) {
				ignoreInvalidatePaint = true;
				setTextPaint(getTextPaint());
				ignoreInvalidatePaint = false;
			}
			// the font is never set in the super paint class?
			paintContext.getGraphics().setFont(getFont());
			super.paint(paintContext);
		}

		@Override
		public void invalidatePaint() {
			if (!ignoreInvalidatePaint) {
				super.invalidatePaint();
			}
		}

	}

	public boolean isFixedLocation() {
		return fixedLocation;
	}

	public void setFixedLocation(boolean fixedLocation) {
		this.fixedLocation = fixedLocation;
	}
}
