package model;

import java.awt.*;
import java.util.*;

public class ShapeContainer implements ShapeComponent {
	private Vector v;

	public ShapeContainer() {
		v=new Vector();
	}

	public void draw(Graphics g) {
		ShapeComponent t;
		Enumeration e=v.elements();
		while(e.hasMoreElements()) {
			t=(ShapeComponent) e.nextElement();
			t.draw(g);
		}
	}
	public void add(ShapeComponent s) {
		v.add(s);
	}
	public void remove(ShapeComponent s) {
		v.remove(s);
	}
	public ShapeComponent getContainer() {
		return this;
	}
}