
package br.com.mariojp.figureeditor;

import javax.swing.*;

import br.com.mariojp.figureeditor.strategy.EllipseStrategy;
import br.com.mariojp.figureeditor.strategy.ShapeStrategy;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_SIZE = 60;
	private final List<ColoredShape> shapes = new ArrayList<>();
	private Point startDrag = null, endDrag = null;
	private ShapeStrategy strategy = new EllipseStrategy();
	private Color currentColor = new Color(30, 144, 255);

	DrawingPanel() {

		setBackground(Color.WHITE);
		setOpaque(true);
		setDoubleBuffered(true);

		var mouse = new MouseAdapter() {
			/*
			 * @Override public void mouseClicked(MouseEvent e) { if (e.getClickCount() == 1
			 * && startDrag == null) { int size = Math.max(Math.min(DEFAULT_SIZE,
			 * DEFAULT_SIZE), 10); Shape s = new Ellipse2D.Double(e.getPoint().x,
			 * e.getPoint().y, size, size); //return new Rectangle2D.Double(e.getPoint().x,
			 * e.getPoint().y, Math.max(DEFAULT_SIZE, 10), Math.max(DEFAULT_SIZE, 10));
			 * shapes.add(s); repaint(); } }
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				startDrag = e.getPoint();
				endDrag = startDrag;
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				endDrag = e.getPoint();
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				Shape s = strategy.createShape(startDrag, endDrag);
				shapes.add(new ColoredShape(s, currentColor));

				startDrag = null;
				endDrag = null;
				
				repaint();
			}
		};
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (startDrag != null && endDrag != null) {

			Shape s = strategy.createShape(startDrag, endDrag);

			Stroke dotted = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, new float[] { 5 }, 0);

			g2.setColor(Color.BLACK);
			g2.setStroke(dotted);
			g2.draw(s);
		}

		for (ColoredShape  cs : shapes) {
			g2.setColor(cs.getColor());
			g2.fill(cs.getShape());
			g2.setColor(cs.getColor());
			g2.setStroke(new BasicStroke(1.2f));
			g2.draw(cs.getShape());
		}

		g2.dispose();
	}

	
	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}

	public Color getCurrentColor() {
		return this.currentColor;
	}
	
	void clear() {
		shapes.clear();
		repaint();
	}

}
