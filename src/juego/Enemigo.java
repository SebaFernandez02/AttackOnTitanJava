package juego;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {

	int lado;
	double x;
	double y;
	double velocidad;
	double angulo;
	Image kyojin= Herramientas.cargarImagen("Imagenes/kojin.png");
	private Clip gritoKyojin;
	Random rand = new Random();

	public Enemigo(double x, double y, double velocidad, double angulo, int lado) {

		this.x=x;
		this.y=y;
		this.lado=lado;
		this.velocidad=velocidad;
	}

	public Enemigo(double velocidad, double angulo, int lado) {

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
	}

	public void dibujarse(Entorno entorno) {	

		Color c=new Color(0, 0, 255, 0);
		entorno.dibujarRectangulo(x, y, lado, lado, angulo, c);	
		entorno.dibujarImagen(kyojin, x, y, this.angulo-1.6, 2.5);
	
		
	}
	
	public void gritar() {
		int num = rand.nextInt(3);
		if(num == 0) {
			gritoKyojin = Herramientas.cargarSonido("Sonidos/Grito Kyojin 1.wav");
		}
		if(num == 1) {
			gritoKyojin = Herramientas.cargarSonido("Sonidos/Grito Kyojin 2.wav");
		}
		if(num == 2) {
			gritoKyojin = Herramientas.cargarSonido("Sonidos/Grito Kyojin 3.wav");
		}
		gritoKyojin.start();
	}
	
	public void movimiento(Obstaculo[] obstaculos, Enemigo[] enemigos, Mikasa mikasa) {
		obstaculos(obstaculos, mikasa);
		enemigos(enemigos);
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
					this.y+=15;
				} else {
					this.y-=15;
				}
				return;
			}
			if(y>obs.y-obs.alto/2-lado/2+1 && y<obs.y+obs.alto/2+lado/2-1
					&& x>obs.x-obs.ancho/2-lado/2 && x<=obs.x+obs.ancho/2+lado/2) {
				this.x += 30*velocidad*Math.cos(this.angulo);
				this.y -= 60*velocidad*Math.sin(this.angulo);
				if(this.y<mikasa.y) {
					this.y+=15;
				} else {
					this.y-=15;
				}
				return;
			}
			if(x>obs.x-obs.ancho/2-lado/2+1 && x<obs.x+obs.ancho/2+lado/2-1
					&& y>=obs.y-obs.alto/2-lado/2 && y<obs.y+obs.alto/2+lado/2) {
				this.x -= 60*velocidad*Math.cos(this.angulo);
				this.y += 30*velocidad*Math.sin(this.angulo);
				if(this.x<mikasa.x) {
					this.x+=15;
				} else {
					this.x-=15;
				}
				return;
			}
			if(x>obs.x-obs.ancho/2-lado/2+1 && x<obs.x+obs.ancho/2+lado/2-1
					&& y>obs.y-obs.alto/2-lado/2 && y<=obs.y+obs.alto/2+lado/2) {
				this.x -= 60*velocidad*Math.cos(this.angulo);
				this.y += 30*velocidad*Math.sin(this.angulo);
				if(this.x<mikasa.x) {
					this.x+=15;
				} else {
					this.x-=15;
				}
				return;
			}
		}
	}
	
	private void enemigos(Enemigo[] enemigos) {
		for(Enemigo ene : enemigos) {
			if(!this.equals(ene)) {
				if(y>ene.y-ene.lado+1 && y<ene.y+ene.lado-1) {
					if(x>=ene.x-ene.lado && x<ene.x+ene.lado-1) {
						x=ene.x-ene.lado;
					}
					if(x<=ene.x+ene.lado && x>ene.x-ene.lado+1) {
						x=ene.x+ene.lado;
					}
				}
				if(x>ene.x-ene.lado+1 && x<ene.x+ene.lado-1) {
					if(y>=ene.y-ene.lado && y<ene.y+ene.lado-1) {
						y=ene.y-ene.lado;
					}
					if(y<=ene.y+ene.lado && y>ene.y-ene.lado+1) {
						y=ene.y+ene.lado;
					}
				}	
			}
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
