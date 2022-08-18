package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculo {

	double x;
	double y;
	int ancho;
	int alto;
	Image obstaculo= Herramientas.cargarImagen("Imagenes/obstaculo.jpg");

	public Obstaculo(double x, double y, int ancho, int alto) {

		this.x=x;
		this.y=y;
		this.ancho = ancho;
		this.alto = alto;
	}

	public Obstaculo(int ancho, int alto) {
		
		this.ancho = ancho;
		this.alto = alto;
		Random rand = new Random();
		this.x = rand.nextInt(800-ancho/2);
		while(this.x>400-ancho/2-50 && this.x<400+ancho/2+50) {
			this.x = rand.nextInt(800-ancho/2);
		}
		this.y = rand.nextInt(600-alto/2);
		while(this.y>300-alto/2-50 && this.y<300+alto/2+50) {
			this.y = rand.nextInt(600-alto/2);
		}
	}

	public void dibujarse(Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.MAGENTA);
		entorno.dibujarImagen(obstaculo, x, y, 0);
	}
	
	public Punto getPunto() {
		return new Punto(x,y);
	}
}
