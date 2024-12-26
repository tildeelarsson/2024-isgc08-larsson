package model;

import java.awt.*;
import java.io.Serializable;

public interface ShapeComponent extends Serializable {
	void draw(Graphics g);
	void add(ShapeComponent s);
	void remove(ShapeComponent s);
	ShapeComponent getContainer();
}
