package main;

import java.awt.*;
import javax.swing.*;

/**
 * Okno główne gry.
 * @author Patrycja Gładkowska
 */
public class GameWindow extends JFrame
{
	/**
	 * Główny konstruktor klasy - ustawienie parametrów gry i jej rozpoczęcie
	 * @param gWidth	szerokość okna 
	 * @param gHeight	wysokość okna
	 * @param x			polozenie x lewego górnego narożnika okna
	 * @param y			polozenie y lewego górnego naroznika okna
	 */
	public GameWindow(int gWidth, int gHeight, int x, int y) 
	{
		super();
		setSize(gWidth, gHeight);
		setLocation(x, y);
		setResizable(false);			// zablokuj mozliwosc zmian rozmiaru okna
		setUndecorated(true);			//usuniecie ramki okna
		gameInterface(gWidth, gHeight);
		setVisible(true);
		Animation(20);				//do funkcji podajemy wartosc thread.sleep
									
	}
	
	/**
	 * Metoda tworząca interfejs graficzny użytkownika.
	 * @param width		szerokosc okna
	 * @param height	wysokosc okna
	 */
	private void gameInterface (int width, int height) 
	{
        setLayout(new GridLayout(1,1));
        add(new GamePanel(width,height)); 
    }
	
	/**
	 * Metoda, będąca główną pętlą gry.
	 * @param sleep
	 */
	private void Animation(int sleep)
	{
		while (true)
		{
			repaint();
			try {
				Thread.sleep(sleep);
			}catch (InterruptedException e){
				System.out.println("Wyjatek: " + e);
			}
		}
	}

}
