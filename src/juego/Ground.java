package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Ground {
	private int x, y ,width ,height ,desplazamiento;
	private Image foreground;
	public Ground(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.width = 500;
		this.height = 82;
		this.desplazamiento=2;
		this.foreground=  Herramientas.cargarImagen("Foreground.png");
	}
	public void dibujar (Entorno e) {
		e.dibujarImagen(foreground,x,y,0);
	}
	public void mover(Entorno e) {
		x -= desplazamiento;
		if(x < -200) {
			x=1200;
		}
	}
	public Rectangle bounds () {
		return (new Rectangle (x-(width/2),y-((height/2)),width,height));
	}
	
}
