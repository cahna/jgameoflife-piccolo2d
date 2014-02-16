
package com.cheine.jgameoflife.piccolo2d;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.piccolo2d.PCanvas;
import org.piccolo2d.event.PMouseWheelZoomEventHandler;
import org.piccolo2d.extras.PFrame;

public class Gui extends PFrame {

	private static final long serialVersionUID = -4405489107172438835L;

	public Gui() {
        this(null);
    }

    public Gui(final PCanvas aCanvas) {
        super("Conway's Game Of Life", false, aCanvas);
    }
	
	public void initialize() {
		// Create the game of life canvas
        final GameCanvas graphEditor = new GameCanvas(500, 500, 50, 50, GameCanvas.CellState.Random);
        
        // Switch to mouse wheel zoom
        final PMouseWheelZoomEventHandler mouseWheelZoomEventHandler = new PMouseWheelZoomEventHandler();
 		graphEditor.removeInputEventListener(graphEditor.getZoomEventHandler());
        graphEditor.addInputEventListener(mouseWheelZoomEventHandler);
        graphEditor.removeInputEventListener(graphEditor.getZoomEventHandler());
		
		final JToolBar toolBar = new JToolBar();
		final JButton runButton = new JButton("Run");
        final JButton stepButton = new JButton("Step");
        final ButtonGroup buttonGroup = new ButtonGroup();
        
        buttonGroup.add(runButton);
        buttonGroup.add(stepButton);
        toolBar.add(runButton);
        toolBar.add(stepButton);
        toolBar.setFloatable(false);
 
        runButton.addActionListener(new ActionListener() {
            /** {@inheritDoc} */
            public void actionPerformed(final ActionEvent event) {
                graphEditor.nextGeneration();
            }
        }); 
        
        stepButton.addActionListener(new ActionListener() {
            /** {@inheritDoc} */
            public void actionPerformed(final ActionEvent event) {
                graphEditor.nextGeneration();
            }
        });        
        
        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add("North", toolBar);
        contentPane.add("Center", graphEditor);
        setContentPane(contentPane);
        
        validate();
	}
	
	public static void main(String args[]) {
        new Gui();
    }
}
