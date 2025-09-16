package br.com.mariojp.figureeditor;

import java.awt.Color;
import java.awt.Shape;

public class ColoredShape {
	private Shape shape;
	private Color color;

	public ColoredShape() {
	}

	public ColoredShape(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
