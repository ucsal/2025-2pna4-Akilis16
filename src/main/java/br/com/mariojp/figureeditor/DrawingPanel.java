
package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;
    private final List<Shape> shapes = new ArrayList<>();
    private Point startDrag = null, endDrag = null;

    DrawingPanel() {
        
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            /*
        	@Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && startDrag == null) {
                    int size = Math.max(Math.min(DEFAULT_SIZE, DEFAULT_SIZE), 10);
                    Shape s =  new Ellipse2D.Double(e.getPoint().x, e.getPoint().y, size, size);
                    //return new Rectangle2D.Double(e.getPoint().x, e.getPoint().y, Math.max(DEFAULT_SIZE, 10), Math.max(DEFAULT_SIZE, 10));
                    shapes.add(s);
                    repaint();
                }
            }
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
            	double x = startDrag.getX() <= endDrag.getX() ? startDrag.getX() : endDrag.getX();
            	double y = startDrag.getY() <= endDrag.getY() ? startDrag.getY() : endDrag.getY();
            	double width = startDrag.getX() - endDrag.getX();
            	double height = startDrag.getY() - endDrag.getY();
            	
            	width = width < 0 ? (width * -1) : width;
            	height = height < 0 ? (height * -1) : height;
            	
            	Shape s = new Ellipse2D.Double(x, y, width, height);
            	shapes.add(s);
            	repaint();
            }
        };
        addMouseListener(mouse);        
        addMouseMotionListener(mouse);

    }

    void clear() {
        shapes.clear();
        repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if(startDrag != null && endDrag != null) {
        	double x = startDrag.getX() <= endDrag.getX() ? startDrag.getX() : endDrag.getX();
        	double y = startDrag.getY() <= endDrag.getY() ? startDrag.getY() : endDrag.getY();
        	double width = startDrag.getX() - endDrag.getX();
        	double height = startDrag.getY() - endDrag.getY();
        	
        	width = width < 0 ? (width * -1) : width;
        	height = height < 0 ? (height * -1) : height;
        	
        	Shape s = new Ellipse2D.Double(x, y, width, height);
        	Stroke dotted = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, new float[] {5}, 0);
        	
        	g2.setColor(Color.BLACK);
        	g2.setStroke(dotted);
        	g2.draw(s);
        }else {
            g2.setStroke(new BasicStroke(1.2f));
        }
        
        for (Shape s : shapes) {
            g2.setColor(new Color(30,144,255));
            g2.fill(s);
            g2.setColor(new Color(0,0,0,70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(s);
        }
        
        g2.dispose();
    }

}
