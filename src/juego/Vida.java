package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

import entorno.Entorno;
import entorno.Herramientas;

public class Vida {
	
	int x;
	int y;
	int radio;
	
	private Image vida = Herramientas.cargarImagen("Imagenes/Vida.png");
	private Clip sonidoVida;
	
	public Vida(int radio) {
		this.radio = radio;
		Random rand = new Random();
		this.x = rand.nextInt(800-radio);
		while(this.x>400-75 && this.x<400+75) {
			this.x = rand.nextInt(800-radio);
		}
		this.y = rand.nextInt(600-radio);
		while(this.y>300-75 && this.y<300+75) {
			this.y = rand.nextInt(600-radio);
		}
	}
	
	public void dibujarse(Entorno entorno) {
		entorno.dibujarCirculo(x, y, radio, Color.black);
		entorno.dibujarImagen(vida, x, y, 0, 0.3);
	}
	
	public void sonidoVida() {
		sonidoVida = Herramientas.cargarSonido("Sonidos/Sonido Vida.wav");
		sonidoVida.start();
	}
	
	public Punto getPunto() {
		return new Punto(x,y);
	}

}
