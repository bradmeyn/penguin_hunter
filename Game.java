
import java.util.*;
import java.io.*;

/**
 * This class the core functionality of the game mechanics integrating all the other components.
 * @author Brad Meyn
 * @version ver 1.0.0
 */

public class Game
{
    private int turnCount;
   
    private boolean storyMode;

    private int requiredFish;
    private int predatorPremium;

    private Player player;
    private Shark shark;
    private Borrow borrowAction;
    private Catch catchAction;;

    int StoryModeTurns;
    int StoryModeFishTarget;

    private String[][] availableWeapons;
    
    /**
     * Default constructor which creates an object derived from the Game class. Creating the game object triggers the generate weapons method which will read the weapons.txt file
     *
     *
     */
    public Game()
    {
        player = new Player();
        shark = new Shark();
        storyMode = false;
        availableWeapons = this.generateWeapons();
        borrowAction = new Borrow(player, shark);
        catchAction = new Catch(player, availableWeapons);
    }

    
     /**
     * If the passed in selection is valid the relevant object will be created and core method started
     *
     *@param    selection       int representing action selection (1 catch fish, 2 borrow fish, 3 end turn)
     */
    public void actionTurn(int selection)
    {
        if(selection == 3) 
        {
            endTurn();
        } 
        else if(!isActionValid(selection))
        {
            System.out.println("please select again\n");
            selectAction();
        }
        else
        {
            switch(selection)
            {
                case 1:
                catchAction.catchFish();
                break;              

                case 2:
                borrowAction.borrowFish();
                break;
            }
            selectAction();
        }
    }


     /**
     * Trigger final steps of the game, writing to the results file
     *
     */
    public void endGame(){

        if(!storyMode)
        {
            System.out.println(Helper.lines() + "\nThe turn has finished and you are out of fish. Game Over\n");
        }

        
        String storyResult = "";
        //if story mode, check if player has met WIN conditions
        if(storyMode){
            player.decreaseCurrentFish(shark.repayAllLoans());
            boolean winner = winStoryMode();
            storyResult = winner ? "Fish target within +/-10%: SUCCESS" : "Fish target outside +/-10%: FAIL";
        }

        System.out.println(Helper.lines() + "\nNow that the game is now over your information will be saved in the results file\n");
        //construct the string to write to the file
        String result = "";
        if(storyMode)
        {
            result = "\nGame Mode: Story, " + "Player Name: " + player.getPlayerName()  + ", Turns Set: " + StoryModeTurns + ", Fish Goal: " + StoryModeFishTarget + ", Actual Fish: " + player.getCurrentFish() + ", result: " + storyResult;
        } 
        else 
        {
            result = "\nGame Mode: Arcade, " + "Player Name: " + player.getPlayerName() + ", Turns Played: " + turnCount;
        }

        //write the file results to the file
        try (FileWriter writer = new FileWriter("results.txt", true))
        {
            writer.append(result);
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(Helper.lines() + "\nThere was an error when writing to the file\n");
        }
    }


    /**
     * Method ends turn and starts sequence of checking game over conditions
     *
     */
    public void endTurn()
    {

        //feed family/pay predator premium
        System.out.println(Helper.lines() + "\nTurn " + turnCount + " is now over. The predator premium (" + predatorPremium + ") and required fish (" + requiredFish + ") will now be deducted from your balance\n");
        player.decreaseCurrentFish(requiredFish + predatorPremium);
        Helper.proceed();

        //check if anyloans are due
        boolean loanDue = shark.loanDue();

        if(loanDue)
        {
            //retrieve grossed up loan balance
            int grossedUpRepayment = shark.dueRepayment();

            //remove grossed up loan balance from player fish count
            player.decreaseCurrentFish(grossedUpRepayment);
        } 
        else 
        {
            System.out.println(Helper.lines() + "\nYou currently have no loans to repay\n");
        }

        //reduce borrowed fish in current turn from num to zero
        shark.setTurnLoan(0);
        //decrease turns remaining on the loans
        shark.decreaseLoanTurn();

        //check gameover conditions
        if(isGameOver())
        {
            endGame();
        } 
        else 
        {
            System.out.println(Helper.lines() + "\nOn to the next day\n");
            //reset action count
            catchAction.setHuntCount(2);
            Helper.proceed();
            startTurn();
        }
    }

    /**
     * Method ends turn and starts sequence of checking game over conditions
     *
     *@return       boolean representing if gameover conditions are met(true) or not (false)
     */
    public boolean isGameOver(){
        boolean gameOver = false;
        //if story mode and set turn count has be met
        if(storyMode && turnCount == StoryModeTurns)
        {
            System.out.println(Helper.lines() + "\nYou have reached the target of " + StoryModeTurns + " turns. Game Over\n");
            gameOver = true;
        }
        else if(!storyMode)
        {
            gameOver = (player.getCurrentFish() <= 0) ? true : false;

        }

        Helper.proceed();
        return gameOver;
    }

    /**
     * Method to read the weapons.txt file and return a mult-dimensional array which is passed to the available weapons variable
     *
     *@return   Mulit-dimensional array containing each weapon and it's attributes
     */
    public String[][] generateWeapons(){
        
        String[][] weapons = new String[4][7];
        int index = 0;

        try
        {
            FileReader reader = new FileReader("weapons.txt");
            
            try 
            {
                Scanner input = new Scanner(reader);
                while(input.hasNextLine())
                {
                    String data = input.nextLine();
                    String[] weapon = data.split(",");
                    weapons[index] = weapon;
                    index++;
                }

            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch(Exception e)
                {
                    System.out.println(Helper.lines() + "\nThere was an error reading the file\n");
                } 
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(
            Helper.lines() + "\nThe weapons.txt file has not been found\n");
        }
        catch(IOError e)
        {
            System.out.println(Helper.lines() + "\nThere was an error reading the file\n");
        }    
          
        System.out.println(Helper.lines() + "\nYou weapons have been loaded\n");
        return weapons;
    }

    /**
     * Accessor method to return current turnCount value
     *
     *@return   int representing current turn count
     */
    public int getTurnCount()
    {
            return turnCount;
    }

    /**
     * Method to assess whether selected action is available
     *
     *@param   selection    int value representing the selected action (1 catchfish) (2 borrow fish)
     *@return               Boolean representing whether player can perform the selected action (true) or not (false)
     */
    public boolean isActionValid (int selection)
    {
        boolean isValid = true;

        //if player cant hunt and selected 1 this is invalid
        if(!catchAction.canHunt() && selection == 1){
            isValid = false;
            System.out.println(Helper.lines() + "\nYou are too tired to hunt anymore this turn\n");
        }
        //if player cant borrow and selected 2 this is invalid
        else if(!borrowAction.canBorrow() && selection == 2){
            isValid = false;
            System.out.println(Helper.lines() + "\nYou cannot borrow anymore fish right now\n");
        }
        Helper.proceed();
        return isValid;
    }

    /**
     * Method to present player with which mode they would like to play. If story mode is selected the storyMode boolean is set to true.
     *
     */
    public void selectMode()
    {
        // Helper helper = new Helper();

        int selection = 0;

        do 
        {
            selection = Helper.inputInt(Helper.lines() + "\nPlease select a game mode with the corresponding numerical key: \n\n 1: Arcade \n 2: Story\n");
        } 
        while(!Helper.isValidNumOption(selection,1, 2));

        switch(selection){
            case 1:
            System.out.println(Helper.lines() + "\nArcade mode selected\n");
            break;

            case 2:
            System.out.println(Helper.lines() + "\nStory mode selected\n");
            storyMode = true;
            break;
        }
        Helper.proceed();
    }
    
    /**
     * Core method for starting the game running various submethods.
     *
     */
    public void startMainGame()
    {
        //assign player name
        player.inputName();
        //prompt to select mode
        selectMode();
        //set story mode parameters
        if(storyMode)
        {
            //prompt and validate no. of turns for storymode
            do 
            {
                StoryModeTurns = Helper.inputInt(Helper.lines() + "\nHow many rounds would you like to play? (must be 5 or greater)\n");
                    if(StoryModeTurns < 5)
                    {
                        System.out.println("\n!!!!! Turn target must be 5 or more !!!!!\n");
                    }
            }
            while(StoryModeTurns < 5);

            //prompt and validate no. of turns for storymode
            do {
                StoryModeFishTarget = Helper.inputInt(Helper.lines() + "\nHow many fish do you think will be remaining after " + StoryModeTurns + " turns? (must be 25 or greater)\n");
                    if(StoryModeFishTarget < 25)
                    {
                        System.out.println("\n!!!!! Fish target must be 25 or more !!!!!\n");
                    }
            }
            while(StoryModeFishTarget < 25);
        }

       startTurn();
    }

    
    /**
     * Core method for starting each turn, incrementting turn counter and incorporating elements that happen at start of each turn..
     *
     */
    public void startTurn()
    {
        turnCount++;
        if(storyMode && turnCount == StoryModeTurns)
        {
            System.out.println(Helper.lines() + "\nFINAL TURN has started\n\nTurn: " + turnCount + "\n" + "\nCurrent fish: " + player.getCurrentFish() + "\n");
        }
        else
        {
            System.out.println(Helper.lines() + "\nNew turn has started\n\nTurn: " + turnCount + "\n" + "\nCurrent fish: " + player.getCurrentFish() + "\n"); 
        }
        Helper.proceed();
        
        //set fish target
        rollFishTarget();
        //set predator premium
        rollPredatorPremium();
        Helper.proceed();
        //run chance of natural disaster
        rollNaturalDisaster();
        Helper.proceed();
        //present turn actions
        selectAction();
    }

    /**
     * Generate a number between 10 & 20 and set fish target to this amount
     *
     */
    public void rollFishTarget()
    {
        requiredFish = Helper.randomNum(10, 20);
        System.out.println(Helper.lines() + "\nDaily Fish Target:\n\nToday your family needs " + requiredFish + " fish\n");
    }

    /**
     * Generate a number between 1 & 100, if it matches number then 1% natural disaster occurs.
     *
     */
    public void rollNaturalDisaster()
    {

        int num1 = Helper.randomNum(1, 100);
        int penalty = Helper.randomNum(50,100);
        int num2 = 50;
        Boolean onePercent = num1 == num2 ? true : false;

        System.out.println(Helper.lines() + "\nWill a natural disaster happen today?");

        if(onePercent){

            System.out.println("\n OH NO!!! A natural disaster has occured, causing you to lose " + penalty + " fish\n");
            player.decreaseCurrentFish(penalty);
        } 
        else 
        {
            System.out.println("\nPhew, no disaster today\n");
        }
    }

    /**
     * Generate a number between 1 & 10 and set predator premium to this amount
     *
     */
    public void rollPredatorPremium(){
        predatorPremium = Helper.randomNum(1, 10);
        System.out.println(Helper.lines() + "\nPredator Premium: \n\nToday it will cost " + predatorPremium + " fish to hunt\n");

    }

    /**
     * Present player with the 3 core options each turn, passing the selection into the actionTurn method
     *
     */
    public void selectAction()
    {   
        int selection;
        //take input
        do 
        {
            selection = Helper.inputInt(Helper.lines() +"\nPlease select an action to perform: \n\n 1: Catch fish \n 2: Borrow from the loan shark \n 3: End Turn\n");

            switch(selection)
            {
                case 1:
                System.out.println(Helper.lines() + "\nCatch Fish selected\n");
                break;              

                case 2:
                System.out.println(Helper.lines() + "\nBorrow from Loan Shark selected\n");
                break;

                case 3:
                System.out.println(Helper.lines() + "\nEnd Turn selected\n");
                break;
            }
        }
        while(!Helper.isValidNumOption(selection, 1, 3));
        actionTurn(selection);
    }

    /**
     * Check if players final fish count is within 10% range of target set
     *
     *@return       boolean  representing if the player achieve the story mode conditions set
     */
    public boolean winStoryMode()
    {
        
        int upper = (int)Math.round(StoryModeFishTarget * 1.10);
        int lower = (int)Math.round(StoryModeFishTarget * 0.90);

        boolean win = player.getCurrentFish() >= lower && player.getCurrentFish() <= upper ? true : false;

        if(win)
        {
            System.out.println(Helper.lines() + "\nCongratulations, your reached your set target of " + StoryModeTurns + " turns with a total fish score of " + player.getCurrentFish() + ". This is within the +/-10% range of your targeted " + StoryModeFishTarget + " fish\n");
        }
        else
        {
            System.out.println(Helper.lines() + "\nYou reached your set target of " + StoryModeTurns + " turns with a total fish score of " + player.getCurrentFish() + ". Unfortunatly this is outside the +/-10% range of your targed " + StoryModeFishTarget + " fish\n");
        }
         return win;
    }
}
