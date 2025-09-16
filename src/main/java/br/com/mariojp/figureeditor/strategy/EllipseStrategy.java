package br.com.mariojp.figureeditor.strategy;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipseStrategy implements ShapeStrategy {

	@Override
	public Shape createShape(Point start, Point end) {

		return new Ellipse2D.Double(Math.min(start.getX(), end.getX()), Math.min(start.getY(), end.getY()),
				Math.abs(start.getX() - end.getX()), Math.abs(start.getY() - end.getY()));

	}

}
