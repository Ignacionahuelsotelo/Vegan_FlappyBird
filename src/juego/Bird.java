package juego;
 
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Image;//pongo clase imagen


public class Bird {
	private int x,y,width,height, desplazamiento;
	
	private Image birdPistola; // su imagen  11
	
	private double birdgira; //girando cuando cae o salta
	
	
	public Bird (int startX, int startY) {
		this.x=startX;
		this.y=startY;
		this.width=50;
		this.height=40;
		this.desplazamiento=2;
		this.birdgira=0;
		this.birdPistola = Herramientas.cargarImagen("Bird.png"); //cargamos imagen
	}
	
	public void dibujarBird(Entorno e) {
		e.dibujarImagen(birdPistola, x, y-5, birdgira);
	}
	
	public void mover(Entorno e) {
		x -= desplazamiento;
		if (x==-20) {
			x=1200;
		}
	}
	public Rectangle bounds () {
		return (new Rectangle (x-(width/2),y-(height/2),width,height));
	}
	public void caer() {
		y=y+1;
		
	}
	public void volar(Entorno e) {
		if (y < e.alto()-20 && y > 20) {
			y -= (3* desplazamiento);
		}else {
			y += desplazamiento;
		}
	}
	
	public void girarAntihorario() {   //izquierda cuando salto gira en game
		birdgira -= 0.1;
		if (birdgira<-1) {
			birdgira=-1;
		}
		
	}
	
	public void girarHorario() {   // derecha cuando caigo gira
		birdgira += 0.017;
		if (birdgira>0) {
			birdgira=0;
			
		}
	}

}