package br.com.mariojp.figureeditor.command;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JColorChooser;

import br.com.mariojp.figureeditor.DrawingPanel;

public class ChangeColorCommand implements Command {

	private final Component parentComponent;
	private final DrawingPanel drawingPanel;

	public ChangeColorCommand(Component parentComponent, DrawingPanel drawingPanel) {
		this.parentComponent = parentComponent;
		this.drawingPanel = drawingPanel;
	}

	@Override
	public void executar() {
		Color chosen = JColorChooser.showDialog(parentComponent, "Escolher Cor", drawingPanel.getCurrentColor());
		if (chosen != null) {
			drawingPanel.setCurrentColor(chosen);
		}

	}

}
