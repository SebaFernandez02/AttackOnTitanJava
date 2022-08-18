package juego;

import java.awt.Color;

import entorno.Entorno;

public class Hud {

	int vidas;
	public Hud() {
		this.vidas=0;
	}
	public Hud(int vidas) {
		this.vidas=vidas;
		
	}
	public void dibujarse(Entorno entorno, int vidas,int spawn) {		
		entorno.cambiarFont("Arial", 30, Color.WHITE);	
		entorno.escribirTexto("Vidas: "+ vidas, 500, 600);
		entorno.escribirTexto("Enemigos Eliminados: "+ spawn, 100, 600);
	}
}
