package main;

/**
 * Klasa zawierająca metody dotyczące stanu gry.
 * @author Patrycja Gładkowska
 *
 */
public class Status {
	
	/**Liczba zdobytych punktów*/
	public static int points;
	/**Numer poziomu*/
	public static int level;
	/**Liczba pominiętych liter*/
	public static int missed;
	
	/**
     * Zerowanie parametrów gry
     */
	public void resetGame()
	{
		points=0;
		level=1;
		missed=0;
	}
	
	/**
	 * Zwiększenie numeru poziomu
	 */
	public void nextLevel()
	{
		level++;
	}
	
	/**
	 * Zwiększenie liczby pominiętych liter
	 */
	public void incMissed()
	{
		missed++;
	}
	
	/**
	 * Zwiększenie liczby zdobytych punktów
	 */
	public void incPoints()
	{
		points++;
	}

}
