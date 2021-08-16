
/**
 * Class which stores the information relating to the borrow action.
 *
 * @author Brad Meyn
 * @version ver1.0.0
 */

public class Borrow
{

    private Player player;
    private Shark shark;

    /**
     * Default constructor which creates an an object derived from the Borrow class.
     *
     */
    public Borrow(Player player, Shark shark)
    {
        this.player = player;
        this.shark = shark;
    }

    /**
     * The core method of the class, prompting for a loan amount and validating this against relevant rules. A new loan is created if the player has the capacity for additional borrowing..
     *
     */
    public void borrowFish()
    {
        //prompt for loan value
        int loanAmount = Helper.inputInt(Helper.lines() + "\nPlease enter the amount of fish you would like to borrow\n");
        //check if player can currently borrow
        if(canBorrow())
        {
            //check amount vs max available
            loanAmount = processRequest(loanAmount);
            //create new loan
            shark.createLoan(loanAmount);
            Helper.proceed();
            //add amount to player total fish
            player.increaseCurrentFish(loanAmount);
            Helper.proceed();
        }
    }

    /**
     * Method to check whether the player can borrow more fish.
     *
     *@return       Boolean indicating if player can borrow (true) or not (false).
     */
    public boolean canBorrow()
    {
        boolean canBorrow = true;

        //player cant borrow if totalLoan is 100 or more
        if(shark.getTotalLoan() >= 100)
        {
            canBorrow = false;     
            System.out.println(Helper.lines() + "You have reached your maximum loan limit of 100 fish, you will not be able to borrow until some of the balance is repaid\n");
        } 
        //cant borrow if already borrowed 30 this turn
        else if( shark.getTurnLoan() >= 30)
        {
            canBorrow = false;     
            System.out.println(Helper.lines() + "\nYou have reached your maximum per turn loan limit of 30 fish, you will not be able to borrow anymore fish this turn\n");
        }

        return canBorrow;
    }

    /**
     * Method to check requested loan amount vs what has already been borrowed.
     *
     *@return       Int converting the requested loan into the most the player can borrow.
     */
    public int processRequest(int amount)
    {

        if(amount + shark.getTurnLoan() > 30)
        {
            amount = 30 - shark.getTurnLoan();
            System.out.println(Helper.lines() + "\nSorry you have already borrowed " + shark.getTurnLoan() + " this round, the maximum you can borrow for the current round is " + amount + "\n");
        };

        if(amount + shark.getTotalLoan() > 100)
        {
            amount = 100 - shark.getTotalLoan();
            System.out.println(Helper.lines() + "\nSorry you currently owe " + shark.getTotalLoan() + " the max you can borrow is " + amount + "\n");
        };

        return amount;

    }

}
