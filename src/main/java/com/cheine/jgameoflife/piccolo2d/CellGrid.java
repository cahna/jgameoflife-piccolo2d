package com.cheine.jgameoflife.piccolo2d;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import org.piccolo2d.PCanvas;
import org.piccolo2d.PLayer;
import org.piccolo2d.PNode;
import org.piccolo2d.event.PBasicInputEventHandler;
import org.piccolo2d.event.PInputEvent;
import org.piccolo2d.nodes.PPath;

class GameCanvas extends PCanvas {
	private static final long serialVersionUID = 8797495177767220120L;
	
	public enum CellState {
		Alive,
		Dead,
		Random
	}
	
	private final PNode[][] nodeMap;
	private final Random rand;
	
	public GameCanvas(int canvasWidth, int canvasHeight, int cellsWidth, int cellsHeight) {
		this(canvasWidth, canvasHeight, cellsWidth, cellsHeight, CellState.Dead);
	}

	
	public GameCanvas(int canvasWidth, int canvasHeight, int cellsWidth, int cellsHeight, CellState initialPopulate) {
		rand = new Random();
		
		setPreferredSize(new Dimension(canvasWidth, canvasHeight));
		
		final float nWidth = canvasWidth / cellsWidth;
		final float nHeight = canvasHeight / cellsHeight;
		
		// Initialize and create a new layer for the nodes
		final PLayer nodeLayer = getLayer();
		nodeLayer.setPickable(false);
		getRoot().addChild(nodeLayer);
		
		nodeMap = populateNodes(nodeLayer, initialPopulate, cellsWidth, cellsHeight, nWidth, nHeight);
	}
	
	private PNode[][] populateNodes(PLayer nodeLayer, CellState mode, int cellsWidth, int cellsHeight, float nWidth, float nHeight) {
		// Create nodes
		PNode[][] map = new PNode[cellsHeight][cellsWidth];
		
		for(int y = 0; y < cellsHeight; y++) {
			for(int x = 0; x < cellsWidth; x++) {
				final PPath node = PPath.createRectangle(x*nWidth, y*nHeight, nWidth, nHeight);
				node.setPaint(Color.blue);
				node.setStroke(null);
				
				boolean living;				
				if(mode == CellState.Alive)
					living = true;
				else if(mode == CellState.Random)
					living = rand.nextBoolean();
				else
					living = false;
				
				node.addAttribute("isAlive", living);
				node.addAttribute("y", y);
				node.addAttribute("x", x);
				node.setTransparency(living ? 1.0F : 0.0F);				
				
				node.addInputEventListener(new PBasicInputEventHandler() {
		            public void mousePressed(final PInputEvent e) {
	            		PNode node = e.getPickedNode();
		                boolean isAlive = node.getBooleanAttribute("isAlive", false);
		                
		                if(isAlive) {
		                	node.addAttribute("isAlive", false);
		                	node.setTransparency(0.0F);
		                } else {
		                	node.addAttribute("isAlive", true);
		                	node.setTransparency(1.0F);
		                }
		            	
		                e.setHandled(true);
		            }
				});
				
				map[y][x] = node;
				nodeLayer.addChild(node);
			}
		}
		
		return map;
	}


	public final void nextGeneration() {
		final int height = nodeMap.length;
		final int width = nodeMap[0].length;
		
		boolean[][] nextState = new boolean[height][width];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				PNode n = nodeMap[y][x];
				boolean isAlive = n.getBooleanAttribute("isAlive", false);
				
				int liveNeighbors = numLivingNeighbors(nodeMap, x, y);
				
				if(isAlive) {
					// Under-population or Over-population kills
					if(liveNeighbors < 2 || liveNeighbors > 3)
						nextState[y][x] = false;
					else
						nextState[y][x] = isAlive;
					
				} else {
					nextState[y][x] = liveNeighbors == 3 ? true : isAlive;
				}
			}
		}
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				PNode n = nodeMap[y][x];
				
				if(nextState[y][x]) {
					n.addAttribute("isAlive", true);
					n.animateToTransparency(1.0F, 500L);
				} else {
					n.addAttribute("isAlive", false);
					n.animateToTransparency(0.0F, 500L);
				}
			}
		}
	}
	
	private static int numLivingNeighbors(final PNode[][] map, int x, int y) {
		int n = 0, height = map.length, width = map[0].length;

		int north = y-1 < 0       ? height-1 : (y-1);
		int south = y+1 >= height ? 0        : (y+1);
		int west  = x-1 < 0       ? width-1  : (x-1);
		int east  = x+1 >= width  ? 0        : (x+1);

		if(map[north][x].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[north][east].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[y][east].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[south][east].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[south][x].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[south][west].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[y][west].getBooleanAttribute("isAlive", false) == true)
			n++;
		if(map[north][west].getBooleanAttribute("isAlive", false) == true)
			n++;

		return n; 
	}
}
