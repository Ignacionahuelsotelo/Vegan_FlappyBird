package juego;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import entorno.Entorno;

public class Shot {
	private double x, y, direccion, velocidad;
	private int width, height;

	
	public Shot(double x, double y, double direccion) {
		
		this.x = x+5;
		this.y = (y-(y/9) +25);
		this.direccion = direccion;
		this.velocidad = 5;
		this.width = 10;
		this.height = 10;
	}
	
	public void dibujar (Entorno e) {
		e.dibujarRectangulo(x, y, width, height, 0, Color.RED);
	}
	public  double getX() {
		return x;
	}
	
	public void mover ( ) {
		y += velocidad * Math.sin(direccion);
		x += velocidad * Math.cos(direccion);
	}
	public Rectangle2D bounds () {
		return (new Rectangle2D.Double (x-(width/2),y-((height/2)-5),width,height) );
	}
	public boolean disparoChoca (Food[] comida, Shot shot) {
		Rectangle2D bala = shot.bounds();
		for (int i=0; i<comida.length; i++) {
			Rectangle carne = comida[i].bounds();
			if(comida[i].getSioNoVegetal()==0 && comida[i].getSerONoSer()==false) {
				if (carne.intersects(bala)) {
					comida[i].change(true);
					return true;
				}
			}
		}
		return false;
	}
}
