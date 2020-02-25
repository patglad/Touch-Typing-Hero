package main;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * Gra Touch Typing Hero - projekt z przedmiotu Współczesne Języki Programowania.
 * @author Patrycja Gładkowska
 */
public class Game {
	
	/**
	 * Metoda uruchamiająca grę: ustawienie rozmiarów okna gry, pobranie wymiarów ekranu,
	 * ustawenie okna gry na środku ekranu.
	 * @param args 		program uruchamiamy bez parametrów
	 */
	public static void main(String[] args)
	{
		
		int gameWidth = 1024;
		int gameHeight = 768;

		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

		int xCenter = (screenWidth-gameWidth)/2;
		int yCenter = (screenHeight-gameHeight)/2;
		
		GameWindow gw = new GameWindow(gameWidth, gameHeight, xCenter, yCenter);
		
	}

}
