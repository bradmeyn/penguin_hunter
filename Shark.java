import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class which stores information of any loans the player creates.
 *
 * @author Brad Meyn
 * @version ver1.0.0
 */

public class Shark
{

    private int totalLoan;
    private int turnLoan;
    private ArrayList<Integer> loanTurns;
    private ArrayList<Integer> loanValues;

    /**
     * Default constructor which to create objects derived from the Shark class.
     *
     */
    public Shark()
    {
        this.totalLoan = 0;
        this.turnLoan = 0;
        loanTurns = new ArrayList<>();
        loanValues = new ArrayList<>();
    }

    
    /**
     * Creates a new loan, adding the amount to the loanValues array and adding a new turn value to the loanTurns array.Both the turnLoan and totalLoan variables are increased.
     *
     *@param    loanValue       int value representing the value of the new loan.
     */
    public void createLoan(int loanValue)
    {
        System.out.println(Helper.lines() + "\nA new loan of " + loanValue + " fish has been created. " + (int)(Math.round(loanValue * 1.5)) + " fish will be payable in 3 turns time\n");
        loanValues.add(loanValue);
        loanTurns.add(3);
        this.turnLoan += loanValue;
        this.totalLoan += loanValue;
        System.out.println("\nFish borrowed this round: " + turnLoan + "\n");
        System.out.println("Total fish loan: " + totalLoan + "\n");
    }

    
    /**
     * Checks if any loans are present, then decreases each value in the loan turns array by 1 (triggered at the end of each round).
     *
     *
     */
    public void decreaseLoanTurn()
    {
        if(loanTurns.size() > 0)
        {
            int turnsRemaining = 0;
            int newTurnsRemaining = 0;
            int i = 0;
            System.out.println(Helper.lines() + "\nYour loans are one turn closer to being due:\n");
            for(Integer loan : loanTurns)
            {
                turnsRemaining = loan;
                //reduce by one
                newTurnsRemaining = turnsRemaining - 1;
                loanTurns.set(i, newTurnsRemaining);
                System.out.println("Loan Amount: " + loanValues.get(i) + "\nTurns Remaining: " + newTurnsRemaining + "\n");
                i++;

            }
        }
    } 

    /**
     * Decreases the balance of the totalLoan variable.
     *
     *@param    amount       int value representing the amount to reduce the totalLoan value by..
     */
    public void decreaseTotalLoan(int amount)
    {
            System.out.println(Helper.lines() + "\nYour total debt has decreased by " + amount + " fish\n");
            totalLoan -= amount;
            System.out.println("Fish borrowed this round: " + turnLoan + "\n");
            System.out.println("Total borrowed fish: " + totalLoan + "\n"); 
    }

     
    /**
     * This method iterates through the loanTurns array, checking if any are equal to zero. If they are they are removed & the index is removed from the loanValues array.
     *
     *@return        int value representing the total grossed up amount to be removed from the players fish balance
     */
    public int dueRepayment()
    {
        int totalRepayment = 0;
        int i = 0;
        Iterator iterator = loanTurns.iterator();
        int turn = 0;
        while(iterator.hasNext())
        {
            turn = (int)(iterator.next());
            //check if loan due
            if(turn == 0)
            {
                System.out.println(Helper.lines() + "\nA loan of " + loanValues.get(i) + " fish has come due. This amount x1.5 will be deducted from your fish balance\n");
                //remove loan turn from loanTurns
                iterator.remove();
                //get the loan amount from the loanValues array
                totalRepayment += loanValues.get(i);
                //remove the loan from the loanValues array
                loanValues.remove(i);
            }
            i++;
        }
        //decrease total loan debt
        decreaseTotalLoan(totalRepayment);
        //increase by the repayable 1.5
        int grossedUpRepayment = (int)(Math.round(totalRepayment * 1.5));

        return grossedUpRepayment;
    }

    /**
     * Accessor method for the loan turns array list.
     *
     *@return       ArrayList of integer representing the turns remaining for each loan.
     */
    public ArrayList getLoanTurns()
    {
        return loanTurns;
    }

    /**
     * Accessor method for the loan values array list.
     *
     *@return       ArrayList of integer representing the non grossed up loan values for each loan.
     */
    public ArrayList getLoanValues()
    {
        return loanValues;
    }

    /**
     * Accessor method for the totalLoan variable.
     *
     *@return       int value representing the total outstanding fish borrowed.
     */
    public int getTotalLoan()
    {
        return totalLoan;
    }

    /**
     * Accessor method for the turnLoan variable.
     *
     *@return       int value representing the amount borrowed this turn.
     */
    public int getTurnLoan()
    {
        return turnLoan;
    }


    /**
     * Checks if any current loans withing the loanTurns array equals 0 (meaning they are due)
     *
     *@return       Boolean representing if any loans are due (true) or not (false).
     */
    public boolean loanDue()
    {
        boolean due = false;
        for (int remaining : loanTurns)
        {
            if(remaining == 0)
            {
                due = true;

            }
        }
        return due;
    }

    /**
     * Used in story mode to repay all outstanding loans
     *
     *@return       int value representing the total grossed up amount to be deducted from the players fish balane.
     */
    public int repayAllLoans()
    {
        int grossedUpRepayment = 0;

            if(loanValues.size() > 0)
            {
                System.out.println(Helper.lines() + "\nNow that your turns have finished, any outstanding loans will be deducted from your fish total.\n");

                int totalRepayment = 0;
                //for each loan add value to total repayment
                for (int loan : loanValues)
                {
                    totalRepayment += loan;
                }
                //gross up the repayment
                grossedUpRepayment = (int)(Math.round(totalRepayment * 1.5));
            }
        Helper.proceed();
        return grossedUpRepayment;
    }

    /**
     * Mutator method for the loanTurns array.
     *
     *@param    turns       ArrayList of Integer representing loan turns remaining until due.
     */
    public void setLoanTurns(ArrayList<Integer> turns)
    {
        this.loanTurns = turns;
    }

    /**
     * Mutator method for the loanValues array.
     *
     *@param    values       ArrayList of Integer representing the individual loans outstanding.
     */
    public void setLoanValues(ArrayList<Integer> values)
    {
        this.loanTurns = values;
    }

    /**
     * Mutator method for the totalLoan variable.
     *
     *@param    loan       int representing the total outstanding loan for the player (not grossed up).
     */
    public void setTotalLoan(int loan)
    {
        totalLoan = loan;
    }

    /**
     * Mutator method for the turnLoan variable.
     *
     *@param    loan       int representing the total borrowed this turn for the player (not grossed up).
     */
    public void setTurnLoan(int loan)
    {
        turnLoan = loan;
    }
}
