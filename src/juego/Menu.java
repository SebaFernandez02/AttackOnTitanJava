package juego;
import java.awt.Color;
import java.awt.Image;

import javax.sound.sampled.Clip;
import entorno.Entorno;
import entorno.Herramientas;

public class Menu {
	
	int cambio;
	int elegir;
	int vidas;
	int elegirpausa;
	boolean switche;
	boolean juego;
	
	Image fondoStart = Herramientas.cargarImagen("Imagenes/fondoStart.jpg");
	Image fondoStart1 = Herramientas.cargarImagen("Imagenes/fondoStart1.jpg");
	Image fondoStart2 = Herramientas.cargarImagen("Imagenes/fondoStart2.jpg");
	Image botonJugar = Herramientas.cargarImagen("Imagenes/jugar.png");
	Image botonSalir= Herramientas.cargarImagen("Imagenes/salir.png");
	Image botonJugar2= Herramientas.cargarImagen("Imagenes/jugar2.png");
	Image botonComojugar = Herramientas.cargarImagen("Imagenes/comoJugar.png");
	Image botonComojugar2= Herramientas.cargarImagen("Imagenes/comoJugar2 .png");
	Image botonSalir2= Herramientas.cargarImagen("Imagenes/salir2.png");
	Image fondoPausa = Herramientas.cargarImagen("Imagenes/pausa.jpg");
	Image botonVolverAJugar= Herramientas.cargarImagen("Imagenes/volver a jugar.png");
	Image botonVolverMenu= Herramientas.cargarImagen("Imagenes/volver al menu.png");
	Image botonVolverAJugar2= Herramientas.cargarImagen("Imagenes/volver a jugar2.png");
	Image botonVolverMenu2= Herramientas.cargarImagen("Imagenes/volver al menu 2.png");
	Image historia= Herramientas.cargarImagen("Imagenes/historia1.png");
	Image historia2=Herramientas.cargarImagen("Imagenes/historia2.png");
	Image suelo=Herramientas.cargarImagen("Imagenes/suelo.jpg");
	Image victoria = Herramientas.cargarImagen("Imagenes/pantallaVictoria.jpg");
	Image derrota = Herramientas.cargarImagen("Imagenes/pantallaDerrota.jpg");
	Image fondoComoJugar= Herramientas.cargarImagen("Imagenes/fondoComoJugar.jpg");
	
	private Clip fuego=Herramientas.cargarSonido("Sonidos/fuego.wav");
	private Clip menuSong = Herramientas.cargarSonido("Sonidos/menuSong.wav");
	private Clip juegoSong=Herramientas.cargarSonido("Sonidos/juego.wav");
	Clip victoriaSong=Herramientas.cargarSonido("Sonidos/victoria.wav");
    Clip derrotaSong = Herramientas.cargarSonido("Sonidos/derrota.wav");

	public Menu() {
		this.cambio=1;
		this.elegir=1;
		this.elegirpausa=1;
		this.switche=true;

	}
	public void run(Entorno entorno,int vidas) {
		this.vidas=vidas;
		switch(cambio) {
		case 1:  
				  entorno.dibujarImagen(fondoStart1, 405, 305, 0);
				  entorno.cambiarFont("Californian FB", 40, Color.WHITE);	
				  entorno.escribirTexto("PRESS START", 310, 300);
			      if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			    	  
			    	  setCambio(2);
			      }	  
			  break;	  
		case 2:
			entorno.dibujarImagen(historia, 405, 305, 0);
			entorno.cambiarFont("Californian FB", 30, Color.BLACK);	
			entorno.escribirTexto("La noche mas larga habia llegado..", 20, 500);
			fuego.start();
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
					 
		    	  setCambio(3);
		      }	  
		  break;
		case 3:
			entorno.dibujarImagen(historia2, 405, 305, 0);
			entorno.cambiarFont("Californian FB", 30, Color.BLACK);	
			entorno.escribirTexto("Era hora de defender a los ultimos humanos...", 100, 600);
			
			
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				fuego.stop();
					
		    	  setCambio(4);
		      }	  
		  break;
		  
		case 4:			
			 entorno.dibujarImagen(fondoStart2, 405, 305, 0);
			 menuSong.start();
			 botones(entorno);
			 if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
				 this.elegir+=1;
				 Herramientas.play("Sonidos/boton.wav");
				 if (this.elegir==5) {
					 this.elegir=4;
				 }			 
			 }
			 if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				 this.elegir-=1;
				 Herramientas.play("Sonidos/boton.wav");
				 if (this.elegir==0) {
					 this.elegir=1;
				 }
				 
			 }
			 	 break;
		case 5:		
			  juegoSong.start();
			  entorno.dibujarImagen(suelo, 405,305 , 0);
			  if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				  Herramientas.play("Sonidos/boton.wav");
				  setCambio(6);
				  this.elegir=4;
			  }
		       break;
		case 6:
			entorno.dibujarImagen(fondoPausa, 405, 305, 0);		
			botones(entorno);
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
				Herramientas.play("Sonidos/boton.wav");
				 if (this.elegir>=4 && this.elegir<6) {
					 
					 this.elegir+=1;
					 }
				 else if(this.elegir==6) {
					 this.elegir=6;
				 } 
				 }	 
			 if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				 Herramientas.play("Sonidos/boton.wav");
				 if (this.elegir >4 && this.elegir<=6) {
					       this.elegir-=1;
					 }				 
					 else if(this.elegir==4) {
						 this.elegir=4;					 
					 }				 
				 }	 
			  break;
		 case 7:
		   entorno.dibujarImagen(derrota, 405, 305, 0);
		   entorno.cambiarFont("Californian FB", 50, Color.WHITE);	
		   entorno.escribirTexto("El mundo fue destruido", 180, 200);
		   botones(entorno);
		   juegoSong.stop();
		   derrotaSong.start();
				 if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
						Herramientas.play("Sonidos/boton.wav");
						 if (this.elegir>=4 && this.elegir<6) {
							 
							 this.elegir+=1;
							 }
						 else if(this.elegir==6) {
							 this.elegir=6;
						 } 
						 }	 
					 if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
						 Herramientas.play("Sonidos/boton.wav");
						 if (this.elegir >4 && this.elegir<=6) {
							       this.elegir-=1;
							 }				 
							 else if(this.elegir==4) {
								 this.elegir=4;					 
							 }				 
						 }	 
					  break; 
		 case 8:
			 entorno.dibujarImagen(victoria, 405, 305, 0);
			 entorno.cambiarFont("Californian FB", 50, Color.WHITE);	
			 entorno.escribirTexto("Salvaste a la humanidad", 180, 200);
			   botones(entorno);
			   juegoSong.stop();
			   victoriaSong.start();
					 if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
							Herramientas.play("Sonidos/boton.wav");
							 if (this.elegir>=4 && this.elegir<6) {
								 
								 this.elegir+=1;
								 }
							 else if(this.elegir==6) {
								 this.elegir=6;
							 } 
							 }	 
						 if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
							 Herramientas.play("Sonidos/boton.wav");
							 if (this.elegir >4 && this.elegir<=6) {
								       this.elegir-=1;
								 }				 
								 else if(this.elegir==4) {
									 this.elegir=4;					 
								 }				 
							 }	 
						  break; 
			  
			  
			 }
		
	   
		}	
	public void botones(Entorno entorno) {
		

		switch(this.elegir) {
		
		case 1:
			 entorno.dibujarImagen(botonJugar2, 400, 250, 0);
			 entorno.dibujarImagen(botonComojugar, 400, 300, 0);
			 entorno.dibujarImagen(botonSalir, 400, 350, 0);
	         if (entorno.sePresiono(entorno.TECLA_ENTER)) {
	        	 Herramientas.play("Sonidos/boton.wav");
	        	 menuSong.stop();
	        	 setCambio(5);
	         }
	     break;
		case 2:
			 entorno.dibujarImagen(botonJugar, 400, 250, 0);
			 entorno.dibujarImagen(botonComojugar2, 400, 300, 0);
			 entorno.dibujarImagen(botonSalir, 400, 350, 0);
			 if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				
				 this.elegir=7;
				 }
			
	
		     break;
		case 3:
			entorno.dibujarImagen(botonJugar, 400, 250, 0);
			entorno.dibujarImagen(botonComojugar, 400, 300, 0); 
			entorno.dibujarImagen(botonSalir2, 400, 350, 0);
			 if (entorno.sePresiono(entorno.TECLA_ENTER)) {
	        	 System.exit(0);
	         }
			 break;
					 
		case 4:
			entorno.dibujarImagen(botonVolverAJugar2, 400, 300, 0);
			entorno.dibujarImagen(botonVolverMenu, 400, 350, 0);
			entorno.dibujarImagen(botonSalir, 400, 400, 0);
			if(entorno.sePresiono(entorno.TECLA_ENTER) && switche) {
				Herramientas.play("Sonidos/boton.wav");
				setCambio(5);
				
			}
			if(entorno.sePresiono(entorno.TECLA_ENTER) && !switche) {
				Herramientas.play("Sonidos/boton.wav");
				
				this.juego=true;
				
			}
			break;
		case 5:
			entorno.dibujarImagen(botonVolverAJugar, 400, 300, 0);
			entorno.dibujarImagen(botonVolverMenu2, 400, 350, 0);
			entorno.dibujarImagen(botonSalir, 400, 400, 0);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				Herramientas.play("Sonidos/boton.wav");
				juegoSong.stop();			
			}
			if((entorno.sePresiono(entorno.TECLA_ENTER) && !switche) || (entorno.sePresiono(entorno.TECLA_ENTER) && this.vidas>=1)) {
				Herramientas.play("Sonidos/boton.wav");
			
				this.juego=true;
			}
			break;
		case 6:
			entorno.dibujarImagen(botonVolverAJugar, 400, 300, 0);
			entorno.dibujarImagen(botonVolverMenu, 400, 350, 0);
			entorno.dibujarImagen(botonSalir2, 400, 400, 0);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				Herramientas.play("Sonidos/boton.wav");
				System.exit(0);
			}
			break;
		
		case 7:
			
			entorno.dibujarImagen(fondoComoJugar, 405, 305, 0);	
			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				this.elegir=1;
			}
		   break;
	
			}
		
	}
	
	public int getCambio() {
		return cambio;
	}

	public void setCambio(int cambio) {
		this.cambio = cambio;
	}
	
	public int getElegir() {
		return elegir;
	}

	public void setElegir(int elegir) {
		this.elegir = elegir;
	}	
}