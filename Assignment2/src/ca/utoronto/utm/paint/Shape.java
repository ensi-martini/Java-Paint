package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Shape object that all other shapes will inherit from.
 * Has a thickness field that representing the thickness of the lines
 * for this. Will always be >0. Has a rgb field representing by a Color object
 * that will determine this color. A boolean field that represents whether or
 * not this shape is an outline or filled in.
 * @author csc207student
 *
 */
public class Shape{
	
	private int thickness;
	private Color rgb;
	private boolean filled;
	
	/**
	 * No-arg constructor that sets default values
	 * of the fields of this. Sets the default thickness of this
	 * to 1. The default color to black. And its filled boolean to false.
	 */
	public Shape() {
		this.thickness = 1;
		this.rgb = new Color(0, 0, 0);
		this.filled = false;
	}
	
	/**
	 * Creates a Shape object with the given parameters.
	 * @param t
	 * 		An integer representing the thickness of this.
	 * @param c
	 *		A color object representing the color of this.
	 * @param f
	 * 		A boolean representing whether this is filled.
	 */
	public Shape(int t, Color c, boolean f) {
		this.thickness = t;
		this.rgb = c;
		this.filled = f;
	}
	
	/**
	 * Return the thickness value of this.
	 * @return
	 * 		Integer value representing thickness of this.
	 */
	public int getThickness() {
		return this.thickness;
	}
	
	/**
	 * Return the color of this.
	 * @return
	 * 		Color object representing color of this.
	 */
	public Color getColor() {
		return this.rgb;
	}
	
	/**
	 * Returns whether this is filled.
	 * @return
	 * 		Boolean of this if it is filled or not.
	 */
	public boolean getIsFilled() {
		return this.filled;
	}
	
	/**
	 * Set this to be filled.
	 */
	public void fill() {
		this.filled = true;
	}
	
	/**
	 * Set the color of this.
	 * @param c
	 * 		Color object that will represent the color of this.
	 */
	public void setColor(Color c) {
		this.rgb = c;
	}
	
	/**
	 * Set the thickness value of this.
	 * @param t
	 * 		Integer value that will represent the thickness of this,
	 */
	public void setStroke(int t) {
		this.thickness = t;
	}
	
}
