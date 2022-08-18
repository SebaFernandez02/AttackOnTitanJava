package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Suero {

	double x;
	double y;
	int radio;
	Image suero= Herramientas.cargarImagen("Imagenes/suero.png");
	private Clip sonidoSuero;

	public Suero(double x, double y, int radio) {
		
		this.x=x;
		this.y=y;
		this.radio=radio;
	}
	
	public Suero(int radio) {
		
		this.radio = radio;
		Random rand = new Random();
		this.x = rand.nextInt(800-radio);
		while(this.x>400-50 && this.x<400+50) {
			this.x = rand.nextInt(800-radio);
		}
		this.y = rand.nextInt(600-radio);
		while(this.y>300-50 && this.y<300+50) {
			this.y = rand.nextInt(600-radio);
		}
	}

	public void dibujarse(Entorno entorno) {
		
		entorno.dibujarCirculo(this.x,this.y,this.radio-15, Color.green);
		entorno.dibujarImagen(suero, this.x, this.y, 0);
	}
	
	public void sonidoSuero() {
		sonidoSuero = Herramientas.cargarSonido("Sonidos/Sonido Suero.wav");
		sonidoSuero.start();
	}
	
	public Punto getPunto() {
		return new Punto(x,y);
	}
}
