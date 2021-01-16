package juego;

import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;

public class Pipe {
	private int x,y,width,height, desplazamiento;
	private int guardarY;
	private Image tubeDown, tubeUp;
	
	
	public Pipe (int x, int y) {
		this.x=x;
		this.y=y;
		this.width=60;
		this.height=400;
		this.tubeDown=  Herramientas.cargarImagen("Tube_Down.png");//cargo imagen de abajo
		this.tubeUp= Herramientas.cargarImagen("Tube_Up.png"); // puse esto
		guardarY=y;
		this.desplazamiento=2;
	}
	
	public void dibujar(Entorno e, int i) {
		if(i%2==0) {
			e.dibujarImagen(tubeUp,x,y,0);
		} else {
			e.dibujarImagen(tubeDown,x,y,0); 
		}
	}
	
	public  int getX() {
		return x;
	}
	
	public int getWidth () {
		return width;
	}
	
	public void mover(Entorno e) {
		x -= desplazamiento;
		if (x==0) {
			x=1200;
		}
	}
	
	public Rectangle bounds () {
		return (new Rectangle (x-(width/2),y-(height/2),width,height));
	}
}
