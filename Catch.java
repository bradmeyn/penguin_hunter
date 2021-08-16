
/**
 * Class which stores the information relating to the Catch action for the game.
 *
 * @author Brad Meyn
 * @version ver1.0.0
 */

public class Catch {

    private final String[] FISH = {"Sardine", "Mackerel", "Shrimp", "Cod"};
    private Player player;
    private Weapon currentWeapon;
    private String[][] availableWeapons;
    private int huntCount; 

     /**
     * Default constructor which creates an object derived from the Catch class (not used).
     *
     */
    public Catch()
    {
        player = new Player();
        currentWeapon = new Weapon();
        huntCount = 2; 
        availableWeapons = new String[4][7];

    }

    /**
     * Non-default constructor which creates an object derived from the Catch class.
     *
     *@param player     player object representing the player for this run of the game.
     *@param shark      shark object representing the shark for this run of the game.
     *@param weapons    multi-dimensional array containing each weapon and associated attirubtes
     */
    public Catch(Player player, String[][] weapons)
    {
        this.player = player;
        availableWeapons = weapons;
        huntCount = 2; 
    }

    /**
     * Method to check if the player can complete the Catch action. False if player has already hunted twice or fish balance is below the cheapest weapon cost.
     *
     *@return   Boolean representing if the player can hunt anymore this turn (true) or not (false).
     */
    public boolean canHunt()
    {
        boolean canHunt = true;
        if(huntCount <= 0)
        {
            canHunt = false;
        } 
        else if(player.getCurrentFish() < 2)
        {
            System.out.println(Helper.lines() + "\nYou do not have enough fish to purchase any of the available weapons\n");
            canHunt = false;
        }
        return canHunt;
    }


    /**
     * Core method for the catch fish action tying in all the other sub-methods.
     *
     */
    public void catchFish()
    {
        //update equipt weapon on player
        selectWeapon();
        Helper.proceed();
        //generate a random fish
        String fish = randomFish();
        //check attributes of the fish and weapon calculating fish caught
        calculateFishCaught(fish);
        //reduce hunt count
        lowerHuntCount();
    }
    
    /**
     * Method to loop through the available weapons array and attach to the associated key, returning string (used in the selectWeapons method).
     *
     *@return   String detailing the available weapons and the associated attributes.
     */
    public String displayWeapons()
    {
        String weapons = "";
        String[] weaponsKey = {"Name: ", "Damage: ", "Strong Against: ", "Weak Against: ", "Cost: ", "Min Catch: ", "Max Catch: "};
        for(int i = 0; i < availableWeapons.length; i++)
        {
            weapons = weapons + "\n" + (i + 1) + ": ";

            for(int j = 0; j < availableWeapons[i].length; j++)
            {
                weapons = weapons + weaponsKey[j] + availableWeapons[i][j] + ", ";
            }
            weapons = weapons + "\n";
        }
        return weapons;
    }

    /**
     * Accessor method to return HuntCount value.
     *
     *@return   int representing current huntCount.
     */
    public int getHuntCount()
    {
        return huntCount;
    }


    /**
     * Method to reduce the hunt count (occuring after each "hunt" which was assumed to be Catch).
     *
     */
    public void lowerHuntCount()
    {
        huntCount --;
        if(huntCount <= 0)
        {
            System.out.println(Helper.lines() + "\nYou are too tired to hunt anymore this turn\n");
        }
        else 
        {
            System.out.println(Helper.lines() + "\nYou have enough energy for " + huntCount + " more hunt.\n");
        }
        Helper.proceed();
    }

    /**
     * Method to generate random fish from the FISH array.
     *
     *@return   String representing the name of the selected fish.
     */
    public String randomFish()
    {
        String fish = FISH[Helper.randomNum(0,3)];
        System.out.println(Helper.lines() + "\nA new " + fish + " appeared\n");
        return fish;
    }

    /**
     * Method to present weapons to player and process if player has enough fish to use it. If successful new weapon object is created with the selection passed in.
     *
     */
    public void selectWeapon()
    {
        // Helper helper = new Helper();
        int selection;
        //while player selects a weapon too expensive for their current fish balance, prompt to reselect
        do 
        {
            //while the player selects an invalid option continue prompting to enter a weapon
            do 
            {
                selection = Helper.inputInt(Helper.lines() + "\nPlease select a weapon with the corresponding numerical key: \n" + displayWeapons());
            }
            while(!Helper.isValidNumOption(selection, 1, 4));
            //reduce selection by one to match relevant weapons row in the multi-dimensional array
            selection = selection -1;
            System.out.println(Helper.lines() + "\n" + availableWeapons[selection][0] + " selected\n");
            //create new weapon object, passing in the selected weapon
            Weapon weapon = new Weapon(availableWeapons[selection]);
            //assign new weapon as the currently active weapon
            currentWeapon = weapon;
            System.out.println(Helper.lines() + "\nWeapon cost: " + currentWeapon.getCost() + "\n\nCurrent Fish: " + player.getCurrentFish() + "\n");
            
            //assess whether the player has enough fish for the selected weapon cost
            if(currentWeapon.getCost() > player.getCurrentFish())
            {
                System.out.println(Helper.lines() + "\nThat weapon costs more than your current fish balance, choose again\n");
            }

        }
        while(currentWeapon.getCost() > player.getCurrentFish());
        //reduce fish total by weapon cost
        player.decreaseCurrentFish(currentWeapon.getCost());
    }


    /**
     * Mutator method to set the huntCount variable.
     *
     *@param   int representing the new huntCount value to be assigned.
     */
    public void setHuntCount(int count)
    {
        this.huntCount = count;
    }


    /**
     * Method to assess the value of fish caught based on random fish, min/max values of that fish and its relationship to the current weapon.
     *
     *@param   String representing the name of the selected fish (provided from the randomFish method).
     */
    public void calculateFishCaught(String fish)
    {
        //generate fish caught based on weapon min & max attributes
        int caughtFishNo = Helper.randomNum(currentWeapon.getMinFish(), currentWeapon.getMaxFish());

        //assess if strong/weak and modify caught fish based on outcome
        if(fish.equals(currentWeapon.getStrongAgainst()))
        {
            System.out.println(Helper.lines() + "\nYour " + currentWeapon.getWeaponName() + " is strong against " + fish + ". Your catch is doubled!\n");
            caughtFishNo *= 2;
      
        } 
        else if(fish.equals(currentWeapon.getWeakAgainst())) 
        {
            System.out.println(Helper.lines() + "\nYour " + currentWeapon.getWeaponName() + " is weak against " + fish + ". Your catch has been halved.\n");
            caughtFishNo = (int)(Math.round(caughtFishNo / 2));
        } 
        else 
        {
                System.out.println(Helper.lines() + "\nYour " + currentWeapon.getWeaponName() + " is neutral against " + fish + "\n");
        }
        Helper.proceed();
        System.out.println(Helper.lines() + "\nYou caught " + caughtFishNo + " fish\n");
        player.increaseCurrentFish(caughtFishNo);

    }
}
