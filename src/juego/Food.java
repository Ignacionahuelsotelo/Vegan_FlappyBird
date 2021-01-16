package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Food {
	private int x,y,width,height, vegetableOburger;
	private int direccion;
	private int desplazamiento;
	private Random random, rand ;
	private int randX, randY;
	private boolean vegetaria_HamburgerYes;
	private Image vegetable;
	private Image burger;
	private Image vegetableHamburger;
	
	public Food (){
		rand = new Random();
		vegetableOburger = rand.nextInt(2);
		random = new Random();
		randX=random.nextInt(900);
		randY=random.nextInt(500);
		this.x=(800+randX);
		this.y=(y+randY);
		this.width=25;
		this.height=25;
		this.direccion=0;
		this.desplazamiento=2;
		vegetaria_HamburgerYes=false;
		this.vegetable = Herramientas.cargarImagen("Vegetable.png");
		this.burger = Herramientas.cargarImagen("Burger.png");
		this.vegetableHamburger = Herramientas.cargarImagen("Vegetarian_Hamburger.png");
	}
	
	public void dibujar(Entorno e) {
		if (vegetableOburger==1 && vegetaria_HamburgerYes==false) {
			e.dibujarImagen(vegetable, x, y, 0);
		} else {
			e.dibujarImagen(burger, x, y, 0);
		}
		if(vegetableOburger==0 && vegetaria_HamburgerYes ==true) {
			e.dibujarImagen(vegetableHamburger, x, y, 0);
		}
		
	}	
	
	public void mover() {
		x -= desplazamiento;
	}
	public  int getX() {
		return x;
	}
	
	public int getSioNoVegetal() {
		return vegetableOburger;
	}
	
	public boolean getSerONoSer() {
		return vegetaria_HamburgerYes;
	}
	
	public void change(boolean vegetariano) {
		vegetaria_HamburgerYes=vegetariano;
	}

	public static Food[] agregarNuevas(int cantidad) {
		Food [] comidas = new Food [cantidad];
		for (int i=0; i<comidas.length; i++) {
			comidas[i]=new Food();
			}
		return comidas;
	}


	public Rectangle bounds () {
		return (new Rectangle (x-(width/2),y-((height/2)),width,height));
	}
	
	public boolean estaEnObstaculo(Food comida, Pipe [] obstaculo) {
		Rectangle vegetal = comida.bounds();
		for(int i =0; i<obstaculo.length;i++) {
			Rectangle obs = obstaculo[i].bounds();
			if (obs.intersects(vegetal)) {
				return true;
			}
		}
		return false;
	}
	}
