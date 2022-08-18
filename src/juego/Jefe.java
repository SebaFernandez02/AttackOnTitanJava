package juego;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Jefe {

	int lado;
	double x;
	double y;
	double velocidad;
	double angulo;
	int vidas;
	
	Random rand = new Random();
	private Clip grito;
	
	Image jefe= Herramientas.cargarImagen("Imagenes/Jefe.png");

	public Jefe(double x, double y, double velocidad, double angulo, int lado, int vidas) {

		this.x=x;
		this.y=y;
		this.lado=lado;
		this.velocidad=velocidad;
		this.vidas = vidas;
	}

	public Jefe(double velocidad, double angulo, int lado, int vida) {

		this.velocidad=velocidad;
		this.angulo = angulo;
		this.lado = lado;
		this.x = rand.nextInt(800-lado/2);
		while(this.x>400-50 && this.x<400+50) {
			this.x = rand.nextInt(800-lado/2);
		}
		this.y = rand.nextInt(600-lado/2);
		while(this.y>300-50 && this.y<300+50) {
			this.y = rand.nextInt(600-lado/2);
		}
		this.x = rand.nextInt(800);
		this.y = rand.nextInt(600);
		this.vidas = vida;
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(jefe, x, y, this.angulo, 3.5);
	}
	
	public void movimiento(Obstaculo[] obstaculos, Mikasa mikasa) {
		obstaculos(obstaculos, mikasa);
		this.x -= velocidad*Math.cos(this.angulo);
		this.y -= velocidad*Math.sin(this.angulo);
	}
	
	public void girar(Mikasa mikasa) {
		this.angulo = Math.atan2(this.y-mikasa.y, this.x - mikasa.x);
	}
	
	private void obstaculos(Obstaculo[] obstaculos, Mikasa mikasa) {
		for(Obstaculo obs : obstaculos) {
			if(y>obs.y-obs.alto/2-lado/2+1 && y<obs.y+obs.alto/2+lado/2-1
					&& x>=obs.x-obs.ancho/2-lado/2 && x<obs.x+obs.ancho/2+lado/2) {
				this.x += 30*velocidad*Math.cos(this.angulo);
				this.y -= 60*velocidad*Math.sin(this.angulo);
				if(this.y<mikasa.y) {
					this.y+=30;
				} else {
					this.y-=30;
				}
				return;
			}
			if(y>obs.y-obs.alto/2-lado/2+1 && y<obs.y+obs.alto/2+lado/2-1
					&& x>obs.x-obs.ancho/2-lado/2 && x<=obs.x+obs.ancho/2+lado/2) {
				this.x += 30*velocidad*Math.cos(this.angulo);
				this.y -= 60*velocidad*Math.sin(this.angulo);
				if(this.y<mikasa.y) {
					this.y+=30;
				} else {
					this.y-=30;
				}
				return;
			}
			if(x>obs.x-obs.ancho/2-lado/2+1 && x<obs.x+obs.ancho/2+lado/2-1
					&& y>=obs.y-obs.alto/2-lado/2 && y<obs.y+obs.alto/2+lado/2) {
				this.x -= 60*velocidad*Math.cos(this.angulo);
				this.y += 30*velocidad*Math.sin(this.angulo);
				if(this.x<mikasa.x) {
					this.x+=30;
				} else {
					this.x-=30;
				}
				return;
			}
			if(x>obs.x-obs.ancho/2-lado/2+1 && x<obs.x+obs.ancho/2+lado/2-1
					&& y>obs.y-obs.alto/2-lado/2 && y<=obs.y+obs.alto/2+lado/2) {
				this.x -= 60*velocidad*Math.cos(this.angulo);
				this.y += 30*velocidad*Math.sin(this.angulo);
				if(this.x<mikasa.x) {
					this.x+=30;
				} else {
					this.x-=30;
				}
				return;
			}
		}
	}
	
	public void gritar() {
		int num = rand.nextInt(4);
		if(num==0) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Jefe 1.wav");
			grito.start();
		}
		if(num==1) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Jefe 2.wav");
			grito.start();
		}
		if(num==2) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Jefe 3.wav");
			grito.start();
		}
		if(num==3) {
			grito = Herramientas.cargarSonido("Sonidos/Grito Jefe 4.wav");
			grito.start();
		}
	}
	
	public Punto getPunto() {
		return new Punto(x,y);
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
}
