package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

import javax.sound.sampled.Clip;

public class Mikasa {

	double x;
	double y;
	int radio;
	int vidas;
	int velocidad = 1;
	double angulo;
	boolean esKyojina = false;
	Image mikasa1 = Herramientas.cargarImagen("Imagenes/mikasa1.png");
	Image mikasa2= Herramientas.cargarImagen("Imagenes/mikasa2 .png");
	Image mikasaKyojina= Herramientas.cargarImagen("Imagenes/Mikasa Mutante.png");
	Bala bala;
	boolean recargo = true;
	boolean invulnerable = false;
	
	private Clip grito;

	public Mikasa(int x,int y,int radio, double angulo, int vidas) {

		this.x=x;
		this.y=y;
		this.radio=radio;
		this.angulo=angulo;
		this.vidas=vidas;
		
	}

	public void dibujarse(Entorno entorno) {
		Color c=new Color(255, 0, 0, 0);

		entorno.dibujarCirculo(this.x, this.y , this.radio, c);
		if (bala==null && !this.esKyojina) {
			entorno.dibujarImagen(mikasa1, x, y, this.angulo+1.6, 3);
		}
		if (bala!=null && !this.esKyojina) {
			entorno.dibujarImagen(mikasa2, x, y, this.angulo+1.6, 3);
		}
		if(this.esKyojina) {
			entorno.dibujarImagen(mikasaKyojina, x, y, angulo, 4);
		}
	}

	public void esKyojina() {
		if(esKyojina) {
			radio = 85;
		} else if(!esKyojina) {
			radio=50;
		}
	}
	
	public void moverAdelante(Entorno entorno, Obstaculo[] obstaculos) {
		if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			bordes();
			obstaculos(obstaculos);
			this.x += Math.cos(this.angulo)*velocidad;
			this.y += Math.sin(this.angulo)*velocidad;

		}
	}

	public void moverAtras(Entorno entorno, Obstaculo[] obstaculos) {
		if(entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			bordes();
			obstaculos(obstaculos);
			this.x -= Math.cos(this.angulo)*velocidad;
			this.y -= Math.sin(this.angulo)*velocidad;
		}
	}

	public void girar(double modificador) {
		this.angulo = this.angulo + modificador;
		if(this.angulo < 0) {
			this.angulo +=2*Math.PI;
		}
		if(this.angulo > 2*Math.PI) {
			this.angulo -=2*Math.PI;
		}
	}

	private void bordes() {
		if(x>=775)
			x=775;
		if(x<=25)
			x=25;
		if(y>=535)
			y=535;
		if (y<=25)
			y=25;
	}
	
	private void obstaculos(Obstaculo[] obstaculos) {
		for(Obstaculo obs : obstaculos) {
			if(y>obs.y-obs.alto/2+1-radio/2 && y<obs.y+obs.alto/2-1+radio/2) {
				if(x>=obs.x-obs.ancho/2-radio/2 && x<obs.x+obs.ancho/2-1+radio/2) {
					x=obs.x-obs.ancho/2-radio/2;
				}
				if(x<=obs.x+obs.ancho/2+radio/2 && x>obs.x-obs.ancho/2+1-radio/2) {
					x=obs.x+obs.ancho/2+radio/2;
				}
			}
			if(x>obs.x-obs.ancho/2+1-radio/2 && x<obs.x+obs.ancho/2-1+radio/2) {
				if(y>=obs.y-obs.alto/2-radio/2 && y<obs.y+obs.alto/2-1+radio/2) {
					y=obs.y-obs.alto/2-radio/2;
				}
				if(y<=obs.y+obs.alto/2+radio/2 && y>obs.y-obs.alto/2+1-radio/2) {
					y=obs.y+obs.alto/2+radio/2;
				}
			}
		}
	}

	public void disparar(Entorno entorno) {
		if(this.bala == null && entorno.estaPresionada(entorno.TECLA_ESPACIO) &&
				recargo) {
			this.bala = new Bala(x,y,this.angulo,10);
			recargo = false;
			bala.sonidoBala();
		}
	}
	
	public void gritar() {
		Random rand = new Random();
		int num = rand.nextInt(3);
		if(num==0) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Mikasa 1.wav");
			grito.start();
		}
		if(num==1) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Mikasa 2.wav");
			grito.start();
		}
		if(num==2) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Mikasa 3.wav");
			grito.start();
		}
	}
	
	public Punto getPunto() {
		return new Punto(x,y);
	}

	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas += vidas;
	}
}