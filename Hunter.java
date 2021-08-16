
/**
 * This is the main class where the game is started.
 * @author Brad Meyn
 * @version ver 1.0.0
 */

public class Hunter
{

    /**
     * Default constructor which creates the object derived from the Hunter class
     *
     */
    public Hunter()
    {

    }
   
   /**
     * The main method where the game is started. Incorporates the option to continue playing after the game has been finished.
     *
     */
    public static void main(String[] args)
    {
        boolean playAgain = true;
        while(playAgain)
        {
            Hunter hunter = new Hunter();
            hunter.run();

            int selection;
            do
            {
                selection = Helper.inputInt(
                Helper.lines() +
                "\nWould you like to play again? \n 1: Lets go! \n 2: No thanks\n");
            }
            while(!Helper.isValidNumOption(selection, 1, 2));

            playAgain = selection == 1 ? true : false;
        }
    }

    /**
     * Create game object and start game
     *
     *
     */
    public void run()
    {
        Game game = new Game();
        game.startMainGame();
        
    }
}


