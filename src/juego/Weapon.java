package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;


public class Weapon {
	private int x, y,width,height, desplazamiento;
	private Image fotoarma;
	private double direccion;
    public Weapon(int x, int y, int direccion) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.desplazamiento = 2;
        
        this.fotoarma = Herramientas.cargarImagen("Weapon.png"); //cargamos imagen
    }
    
    public void dibujararma(Entorno e) {
		e.dibujarImagen(fotoarma, x, y, direccion);
	} 
    
    public void mover(Entorno e) { //esto tambien puse
		
		x=x-desplazamiento;
		if (x==-20) {
			x=1200;
		}
	}
      
    public void caer() { //tambie puse
		y=y+1;
		
	}
	public void volar(Entorno e) {   // tambien puse
		if (y < e.alto()-30 && y > -1) {
			y -= (3* desplazamiento);
			
		}else {
			y += desplazamiento;
			
		}
	}
	
    
    public Shot disparar() {
        return new Shot(x,y,direccion); //cambio direccion por disparo para disparar en diagonal

    }
    public void girarAntihorario() {   //izquierda cuando salto gira en game
        direccion -= 0.04;
        if (direccion<-1) {
        	direccion=-1;
        }
    }

    public void girarHorario() {   // derecha cuando caigo gira
        direccion += 0.04;
        if (direccion>0.9) {
        	direccion=0.9;
        }
    }

}
