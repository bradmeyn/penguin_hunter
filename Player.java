
/**
 * Class which stores the information relating to the player character such as name and fish balances.
 *
 * @author Brad Meyn
 * @version ver1.0.0
 */

public class Player
{
    private String playerName;
    private int currentFish;

    /**
     * Default constructor which creates an an object derived from the Player class.
     *
     */
    public Player()
    {
        playerName = "Oggy";
        currentFish = 10;
    }

    /**
     * Decreases the currentFish value
     *
     *@param    fish       int value representing the amount to decrease the currentFish variable by.
     */
    public void decreaseCurrentFish(int fish)
    {
        currentFish -= fish;
        System.out.println(Helper.lines() + "\nYour total fish balance has reduced by " + fish + "\n" + "\nTotal fish: " + currentFish + "\n");
        Helper.proceed();
    }

     /**
     * Prompts the user to enter a name, where if requirements are met player name will be reset to the entered name.
     *
     */
    public void inputName()
    {
        String selectedName = "";

        do 
        {
            selectedName = Helper.inputString(
            Helper.lines() +
            "\nPlease Enter your name: (minimum 3 characters, maximum 12)\n");
        }
        while(Helper.isBlank(selectedName) || !Helper.isValidLength(selectedName, 3, 12));

        playerName = selectedName;
        System.out.println(Helper.lines() + "\nWelcome " + playerName + "\n");
        Helper.proceed();
    }

    /**
     * Accessor method for the currentFish variable.
     *
     *@return       Int representing the players current fish total.
     */
    public int getCurrentFish()
    {
        return currentFish;
    }

    /**
     * Accessor method for the playerName variable.
     *
     *@return       String representing the players current set name.
     */
    public String getPlayerName()
    {
        return playerName;
    }

    /**
     * Increases the currentFish value
     *
     *@param    fish      int value representing the amount to increase the currentFish variable by.
     */
    public void increaseCurrentFish(int fish)
    {
        this.currentFish += fish;
        System.out.println(Helper.lines() + "\nYour total fish balance has increased by " + fish + "\n" + "\nTotal fish: " + currentFish + "\n");
    }

    /**
     * Mutator method for currentFish
     *
     *@param    fish      int value to set currentFish to.
     */
    public void setCurrentFish(int fish)
    {
        this.currentFish = fish;
    }


    /**
     * Mutator method for playerName
     *
     *@param    name      String value to set playerName variable.
     */
    public void setPlayerName(String name){
        this.playerName = name;
    }
}
