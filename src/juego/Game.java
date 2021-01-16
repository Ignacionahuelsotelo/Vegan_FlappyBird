package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Game extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private int WIDTH=800, HEIGHT=600;
	private Image background;
	
	private Ground pasto;
	private Ground[] foreground;
	
	public static Bird bird;
	private Pipe []pipe;
	private Shot []shot;
	private Food [] comidas;
	private boolean started;
	boolean gameOver = false;
	private int score, life, scoreMax;
	private Image gameOverImagen, stats;
	private Weapon weapon;
	
	public Game() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Vegan Bird", WIDTH, HEIGHT);
		this.gameOverImagen = Herramientas.cargarImagen("gameOver.png");
		this.stats = Herramientas.cargarImagen("Stats.png");
		background = Herramientas.cargarImagen("background.png");
		//
		pasto = new Ground (entorno.ancho()/2, 559);
		//
		foreground= new Ground[3];
		    foreground[0]= new Ground (260,559);
		    foreground[1]= new Ground (779,559);
		    foreground[2]= new Ground (1200,559);
		//
		bird = new Bird (200,300);
		weapon = new Weapon (200+8 ,300-15,0);
		//
		pipe=new Pipe[8];
		    pipe[0] = new Pipe (850,-150);
		    pipe[1] = new Pipe (850,400);
		    pipe[2] = new Pipe (1200,-100);
		    pipe[3] = new Pipe (1200,450);
		    pipe[4] = new Pipe (1500,25);
		    pipe[5] = new Pipe (1500,HEIGHT-50);
		    pipe[6] = new Pipe (600,25);
		    pipe[7] = new Pipe (600,HEIGHT-50);

		//
		shot=new Shot[10];
		//
		comidas= Food.agregarNuevas(50);
		//
		score=0;
		scoreMax=0;
		life=3;
		// Inicializar lo que haga falta para el juego
		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		entorno.dibujarImagen(background, WIDTH/2, HEIGHT/2, 0);
		bird.dibujarBird(entorno);
		weapon.dibujararma(entorno);
		Rectangle ave = bird.bounds();
		
		if(started) {
			for(int i=0; i<pipe.length; i++) {
				pipe[i].dibujar(entorno, i);
				if(!gameOver) {
					pipe[i].mover(entorno);
					Rectangle rect = pipe[i].bounds();
					
					if(ave.intersects(rect)) { 
			        	gameOver=true;
			        }
				}
			}
			
			for (int i=0; i<comidas.length; i++) {
				while(comidas[i].estaEnObstaculo(comidas[i], pipe)==true) {
					comidas[i]=new Food();
				}
				
				comidas[i].dibujar(entorno);
				if(!gameOver) {
					comidas[i].mover();
					if (comidas[i].getX()<0) {
						comidas[i]=new Food();
					}
					Rectangle vegetal = comidas[i].bounds();
						
					if(vegetal.intersects(ave)){
						if(comidas[i].getSioNoVegetal()==1 ) {
							score=score+5;
							}else {
								if(comidas[i].getSerONoSer()==false) {
									score=score-5;
								} else {
									score=score+3;
								}
							}
							comidas[i]=new Food();
						}

				}
			}
			
			if(!gameOver) {
				weapon.caer();
				bird.caer();
				bird.girarHorario(); //derecha
				
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
					weapon.volar(entorno);
					bird.volar(entorno);
					bird.girarAntihorario(); //izquierda
				}
				
				if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) { //pongo el giro del arma solo la foto
					weapon.girarAntihorario();	
				}
				
				if(entorno.estaPresionada(entorno.TECLA_DERECHA)) { //pongo el giro del arma solo la foto
					weapon.girarHorario();	
				}
				
				
				if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					
					Shot d=weapon.disparar();
					for(int i=0; i<shot.length; i++) {
						
						if(shot[i]==null) {
							shot[i]=d;
							break;
						}
					}
				}
					
				for(int i=0; i<shot.length; i++) {
					
					if(shot[i]!=null) {
						shot[i].dibujar(entorno);
						shot[i].mover();
						
						if(shot[i].getX()>=entorno.ancho()-50) {
							shot[i]=null;
						}
					}
					
					if(shot[i]!=null) {
						if(shot[i].disparoChoca(comidas, shot[i])) {
							shot[i]=null;
						}
					}
				}
				
				if(pasto.bounds().intersects(ave)) {
					gameOver=true;
				}
			}
		}
		
		
		for(int i=0; i<foreground.length; i++) {
			foreground[i].dibujar(entorno);
			if(foreground[i].bounds().intersects(ave) ) {
				gameOver=true;
			}
			if(!gameOver) {
				foreground[i].mover(entorno);
			}
		}
		if(!started) {
			entorno.cambiarFont("Courier", 30, Color.BLACK);
			entorno.escribirTexto("Para Iniciar el Juego, apreta la tecla Enter", 5, entorno.alto()-10);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				started=true;
			}
		}
		if(!gameOver) {
			entorno.cambiarFont("sans", 25, Color.WHITE);
			entorno.escribirTexto("score: " + score, 15, 25);
			entorno.escribirTexto("Life: " + life, 725, 25);
		}
		
		if (gameOver) {
			if(score>scoreMax) {
				scoreMax=score;
			}
			entorno.dibujarImagen(gameOverImagen, 400, 150, 0);
			entorno.dibujarImagen(stats, 400, 325, 0);
			
			entorno.cambiarFont("sans", 40, Color.WHITE);
			entorno.escribirTexto("" + score, 515, 315);
			entorno.escribirTexto("" + scoreMax, 515, 400);
			entorno.escribirTexto("" + (life -1), 265, 315);
			if(life>1) {
				if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					life=life-1;
					started=false;
					gameOver=false;
					bird = new Bird (200,300);
					weapon = new Weapon (200+8 ,300-15,0);
					pipe=new Pipe[8];
				    pipe[0] = new Pipe (850,-150);
				    pipe[1] = new Pipe (850,400);
				    pipe[2] = new Pipe (1200,-100);
				    pipe[3] = new Pipe (1200,450);
				    pipe[4] = new Pipe (1500,25);
				    pipe[5] = new Pipe (1500,HEIGHT-50);
				    pipe[6] = new Pipe (600,25);
				    pipe[7] = new Pipe (600,HEIGHT-50);
					for(int i=0; i<shot.length; i++) {
						if(shot[i]!=null) {
							shot[i]=null;
						}
					}
					comidas= Food.agregarNuevas(50);
					score=0;
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game juego = new Game();
	}

}
