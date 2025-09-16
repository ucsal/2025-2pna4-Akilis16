package br.com.mariojp.figureeditor.strategy;

import java.awt.Point;
import java.awt.Shape;

public interface ShapeStrategy {

	Shape createShape(Point start, Point end);

}
