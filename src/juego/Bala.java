package juego;

//import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Bala {
	
	double x;
	double y;
	double angulo;
	int radio;
	Image bala = Herramientas.cargarImagen("Imagenes/Bala.png");
	private Clip sonidoBala;
	
	public Bala(double x, double y, double angulo, int radio) {
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		this.radio = radio;
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarImagen(bala, x, y, angulo, 0.03);
	}
	
	public boolean obstaculos(Obstaculo[] obstaculos) {
		for(Obstaculo obs : obstaculos) {
			if(y>obs.y-obs.alto/2+1 && y<obs.y+obs.alto/2-1) {
				if(x>=obs.x-obs.ancho/2 && x<obs.x+obs.ancho/2-1) {
					return true;
				}
				if(x<=obs.x+obs.ancho/2 && x>obs.x-obs.ancho/2+1) {
					return true;
				}
			}
			if(x>obs.x-obs.ancho/2+1 && x<obs.x+obs.ancho/2-1) {
				if(y>=obs.y-obs.alto/2 && y<obs.y+obs.alto/2-1) {
					return true;
				}
				if(y<=obs.y+obs.alto/2 && y>obs.y-obs.alto/2+1) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void sonidoBala() {
		sonidoBala = Herramientas.cargarSonido("Sonidos/Bala.wav");
		sonidoBala.start();
	}
	
	public Punto getPunto() {
		return new Punto(x,y);
	}
}
