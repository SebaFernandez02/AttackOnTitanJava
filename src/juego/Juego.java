package juego;

import java.util.Random;

import entorno.Entorno;

import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	public Entorno entorno;
	Enemigo[] enemigos;
	Mikasa mikasa;
	Suero[] sueros;
	Obstaculo[] obstaculos;
	Jefe jefe;
	Hud hud;
	Menu menu;
	Vida vida=null;
	int contTicksVida = 0;
	int contTicksBala = 0;
	int contTicksInv = 0;
	int contSpawn=21;
	int cantEnemigosEliminados = 0;

	public Juego() {
		// Inicializa el objeto entorno
		mikasa = new Mikasa(800/2,600/2,50,0,3);

		obstaculos = new Obstaculo[4];

		this.enemigos = new Enemigo[5];

		sueros = new Suero[5];

		this.entorno = new Entorno(this,
				"Attack on Titan: Final Season - Grupo N°7 - Antunez - Cayetano Garcia - Fernandez - Sanchez Morais ", 800, 600);

		for (int i = 0; i < enemigos.length; i ++) {
			enemigos[i] = spawnEnemigo();
		}

		for (int i = 0; i < sueros.length; i ++) {	
			sueros[i] = new Suero(40);
		}
		
		for(int i = 0; i<obstaculos.length; i++) {
			Random rand=new Random();
			int x = rand.nextInt(650);
			int y = rand.nextInt(250);
			if(i==0) {
				obstaculos[i] = new Obstaculo(x+80,100,120,70);
			}
			if(i==1) {
				obstaculos[i] = new Obstaculo(200,y+180,120,70);
			}
			if(i==2) {
				obstaculos[i] = new Obstaculo(600,y+180,120,70);
			}
			if(i==3) {
				obstaculos[i] = new Obstaculo(x+80,530,120,70);
			}
		}
		menu = new Menu();
	
		hud = new Hud(mikasa.vidas);
		this.entorno.iniciar();
	}

	public void dibujarObstaculos() {

		for(int i = 0; i<obstaculos.length; i++) {
			obstaculos[i].dibujarse(entorno);
		}
	}

	public void dibujarEnemigos() {

		for(int i=0;i<enemigos.length;i++) {		
			enemigos[i].dibujarse(entorno);
		}
		
	}
	
	public void dibujarSueros() {

		for(int i=0;i<sueros.length;i++) {
			sueros[i].dibujarse(entorno);
		}
	}

	public void tickSuero() {

		for (int i=0; i < sueros.length;i++) {
			if(Punto.seTocan(mikasa.getPunto(), sueros[i].getPunto(),
					mikasa.radio) && !mikasa.esKyojina) {
				sueros[i].sonidoSuero();
				sueros[i]=sueros[sueros.length-1];
				Suero[] nuevo = new Suero[sueros.length-1];
				for(int j=0;j<nuevo.length;j++){
					nuevo[j] = sueros[j];
				}
				this.sueros = nuevo;
				mikasa.esKyojina=true;
			}
		}
	}
	
	public void tickVida() {
		contTicksVida++;
		
		if(vida==null && contTicksVida==500) {
			vida=new Vida(40);
		}

		if(vida!=null) {
			contTicksVida=0;
			if(Punto.seTocan(mikasa.getPunto(), vida.getPunto(),
					mikasa.radio)) {
				vida.sonidoVida();
				mikasa.vidas++;
				vida=null;
			}
		}
	}

	public void movimientoEnemigo() {

		for (int i = 0; i < enemigos.length;i++) {
			enemigos[i].movimiento(obstaculos,enemigos,mikasa);
			enemigos[i].girar(mikasa);	
			if(Punto.seTocan(enemigos[i].getPunto(),
					mikasa.getPunto(), mikasa.getRadio()) && mikasa.esKyojina){
				enemigos[i].gritar();
				if(contSpawn>0) {
					contSpawn--;
					enemigos[i] = spawnEnemigo();
				} else {
					enemigos[i] = enemigos[enemigos.length-1];
					Enemigo[] nuevo = new Enemigo[enemigos.length-1];
					for(int j = 0;j<nuevo.length;j++) {
						nuevo[j]=enemigos[j];
					}
					this.enemigos = nuevo;
				}
				cantEnemigosEliminados++;
				mikasa.esKyojina=false;
				return;
			}
			if(Punto.seTocan(enemigos[i].getPunto(),
					mikasa.getPunto(), mikasa.getRadio()) && !mikasa.esKyojina
					&& !mikasa.invulnerable){
				
				mikasa.vidas-=1;
				mikasa.invulnerable=true;
				mikasa.gritar();
				return;
			}
			if(mikasa.bala!=null) {
				if(Punto.seTocan(enemigos[i].getPunto(), mikasa.bala.getPunto(),
						enemigos[i].lado/2)) {
					enemigos[i].gritar();
					if(contSpawn>0) {
						contSpawn--;
						enemigos[i] = spawnEnemigo();
					} else {
						enemigos[i] = enemigos[enemigos.length-1];
						Enemigo[] nuevo = new Enemigo[enemigos.length-1];
						for(int j = 0;j<nuevo.length;j++) {
							nuevo[j]=enemigos[j];
						}
						this.enemigos = nuevo;
					}
					cantEnemigosEliminados++;
					mikasa.bala = null;
				}
			}
		}
	}
	
	public void movimientoJefe(Entorno entorno) {
		jefe.dibujarse(entorno);
		jefe.movimiento(obstaculos,mikasa);
		jefe.girar(mikasa);	
		if(Punto.seTocan(jefe.getPunto(),
				mikasa.getPunto(), mikasa.getRadio()) && mikasa.esKyojina){
			jefe.gritar();
			jefe.vidas--;
			jefe.x += 30*jefe.velocidad*Math.cos(jefe.angulo);
			jefe.y += 30*jefe.velocidad*Math.sin(jefe.angulo);
			mikasa.esKyojina=false;
			return;
		}
		if(Punto.seTocan(jefe.getPunto(),
				mikasa.getPunto(), mikasa.getRadio()) && !mikasa.esKyojina
				&& !mikasa.invulnerable){
			mikasa.gritar();
			mikasa.vidas-=1;
			mikasa.invulnerable=true;
			return;
		}
		if(mikasa.bala!=null) {
			if(Punto.seTocan(jefe.getPunto(), mikasa.bala.getPunto(),
					jefe.lado/2)) {
				jefe.gritar();
				jefe.vidas--;
				jefe.x += 10*jefe.velocidad*Math.cos(jefe.angulo);
				jefe.y += 10*jefe.velocidad*Math.sin(jefe.angulo);
				mikasa.bala = null;
			}
		}
	}
	
	private Enemigo spawnEnemigo() {
		Random rand = new Random();
		int num = rand.nextInt(3);
		Enemigo enemigo = null;
		if(num==0) {
			int nuevoY = rand.nextInt(500);
			enemigo = new Enemigo(-30,nuevoY,0.5,0,60);
		}
		if(num==1) {
			int nuevoY = rand.nextInt(500);
			enemigo = new Enemigo(830,nuevoY,0.5,0,60);
		}
		if(num==2) {
			int nuevoX = rand.nextInt(700);
			enemigo = new Enemigo(nuevoX,-30,0.5,0,60);
		}
		return enemigo;
	}
	
	private Jefe spawnJefe() {
		Random rand = new Random();
		int num = rand.nextInt(3);
		Jefe jefe = null;
		if(num==0) {
			int nuevoY = rand.nextInt(600);
			jefe = new Jefe(-30,nuevoY,1.5,0,100,10);
		}
		if(num==1) {
			int nuevoY = rand.nextInt(600);
			jefe = new Jefe(830,nuevoY,1.5,0,100,10);
		}
		if(num==2) {
			int nuevoX = rand.nextInt(800);
			jefe = new Jefe(nuevoX,-30,1.5,0,100,10);
		}
		return jefe;
	}

	public void tickBala() {
		double velocidad=20;

		mikasa.bala.x+=Math.cos(mikasa.bala.angulo)*velocidad;
		mikasa.bala.y+=Math.sin(mikasa.bala.angulo)*velocidad;

		if(mikasa.bala.x>800 || mikasa.bala.x<0 ||
				mikasa.bala.y>600 || mikasa.bala.y<0) {
			mikasa.bala=null;
			return;
		}
		if(mikasa.bala.obstaculos(obstaculos)) {
			mikasa.bala=null;
			return;
		}
	}

	public void tick() {

		menu.run(entorno,mikasa.vidas);

		if (menu.cambio==5) {
			tickSuero();
			dibujarSueros();
			tickVida();
			if(vida!=null) {
				vida.dibujarse(entorno);
			}
			mikasa.dibujarse(entorno);
			mikasa.moverAdelante(entorno,obstaculos);
			mikasa.moverAtras(entorno,obstaculos);
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				mikasa.girar(2*Math.PI/180);
			}
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				mikasa.girar(2*-Math.PI/180);
			}
			mikasa.disparar(entorno);
			if(mikasa.bala!=null) {
				mikasa.bala.dibujarse(entorno);
				tickBala();
			}
			if(!mikasa.recargo) {
				contTicksBala++;
				if(contTicksBala == 50) {
					mikasa.recargo = true;
					contTicksBala = 0;
				}
			}
			if(mikasa.invulnerable) {
				contTicksInv++;
				if(contTicksInv==100) {
					mikasa.invulnerable=false;
					contTicksInv=0;
				}
			}
			mikasa.esKyojina();
			dibujarEnemigos();
			movimientoEnemigo();
			if(enemigos.length==0 && jefe==null) {
				jefe = spawnJefe();
			}
			if(jefe!=null ) {
				movimientoJefe(entorno);
			}
			dibujarObstaculos();
			hud.dibujarse(entorno, mikasa.vidas,cantEnemigosEliminados);
			
		}
		if(mikasa.vidas==0 && menu.switche) {
			if(jefe!=null) {
				jefe=null;
			}
			menu.cambio=7;
			menu.elegir=4;
			menu.switche=false;
		}
		if(jefe!=null) {
			if(jefe.vidas == 0 && menu.switche) {
				cantEnemigosEliminados++;
				jefe=null;
				menu.cambio=8;
				menu.elegir=4;
				menu.switche=false;
			}
		}
		
		if(menu.juego) {
			menu.victoriaSong.stop();
			menu.derrotaSong.stop();
			cantEnemigosEliminados=0;
			mikasa = new Mikasa(800/2,600/2,50,0,3);
			obstaculos = new Obstaculo[4];
			this.enemigos = new Enemigo[5];
			sueros = new Suero[5];
			for (int i = 0; i < enemigos.length; i ++) {		
				enemigos[i] = spawnEnemigo();
			}
			for (int i = 0; i < sueros.length; i ++) {	
				sueros[i] = new Suero(40);
			}
			for(int i = 0; i<obstaculos.length; i++) {
				Random rand=new Random();
				int x = rand.nextInt(650);
				int y = rand.nextInt(250);
				if(i==0) {
					obstaculos[i] = new Obstaculo(x+80,100,120,70);
				}
				if(i==1) {
					obstaculos[i] = new Obstaculo(200,y+180,120,70);
				}
				if(i==2) {
					obstaculos[i] = new Obstaculo(600,y+180,120,70);
				}
				if(i==3) {
					obstaculos[i] = new Obstaculo(x+80,530,120,70);
				}
			}
			menu = new Menu();
			menu.setCambio(4);
			menu.elegir=1;
			this.contSpawn=21;
			hud = new Hud(mikasa.vidas);	   	
		}		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
		
	}

}
