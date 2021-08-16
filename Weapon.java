/**
 * This class represents a singular weapon, with the variables representing attributes from the provided weapons.txt file.
 * @author Brad Meyn
 * @version ver 1.0.0
 */

public class Weapon
{
    private String weaponName;
    private int damage;
    private String strongAgainst;
    private String weakAgainst;
    private int cost;
    private int minFish;
    private int maxFish;

    /**
     * Default constructor which creates an object of the class Weapon (not used).
     *
     */
    public Weapon()
    {
        weaponName = "unknown";
        damage= 0;
        strongAgainst = "unknown";
        weakAgainst = "unknown";
        cost = 0;
        minFish = 0;
        maxFish = 0;
    }

    /**
     * Non-default constructor which creates an object derived from the Weapon class.
     *
     *@param weapon     Accepts a an array of Strings representing the weapon attributes
     */
    public Weapon(String[] weapon)
    {
        weaponName = weapon[0];
        damage = Integer.parseInt(weapon[1]);
        strongAgainst = weapon[2];
        weakAgainst = weapon[3];
        cost = Integer.parseInt(weapon[4]);
        minFish = Integer.parseInt(weapon[5]);
        maxFish = Integer.parseInt(weapon[6]);
    }

    /**
     * Method to display each variable for the Weapon object.
     *
     *@return       String containing each variable and current value.
     */
    public String display()
    {
        return "Name: " + weaponName + "\nDamage: " + damage + "\nStrong Against: " + strongAgainst + "\nWeak Against: " + weakAgainst + "\n Cost: " + cost + "\nMin Catch: " + minFish + "\nMax Catch: " + maxFish ;
    }


    /**
     * Accessor method for the cost variable.
     *
     *@return       Int representing the cost of the weapon.
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * Accessor method for the damage variable.
     *
     *@return       Int representing the damage of the weapon.
     */
    public int getDamage()
    {
        return damage;
    }

    /**
     * Accessor method for the strongAgainst variable.
     *
     *@return       String representing what fish the weapon is strong against.
     */
    public String getStrongAgainst()
    {
        return strongAgainst;
    }

    /**
     * Accessor method for the maxFish variable.
     *
     *@return       Int representing the maximum catchable fish for the weapon.
     */
    public int getMaxFish()
    {
        return maxFish;
    }

    /**
     * Accessor method for the minFish variable.
     *
     *@return       Int representing the minimum catchable fish for the weapon.
     */
    public int getMinFish()
    {
        return minFish;
    }

    /**
     * Accessor method for the weakAgainst variable.
     *
     *@return       String representing what fish the weapon is weak against.
     */
    public String getWeakAgainst()
    {
        return weakAgainst;
    }

    /**
     * Accessor method for the name variable.
     *
     *@return       String containing the weapon name.
     */
    public String getWeaponName()
    {
        return weaponName;
    }

     /**
     * Mutator method to set the weapon cost.
     *
     *@param    cost   An int representing the cost of the weapon
     */
    public void setCost(int cost)
    {
        this.cost = cost;
    }

     /**
     * Mutator method to set the weapon damage.
     *
     *@param    cost    An int representing the damage of the weapon
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * Mutator method to set which fish the weapon is strong against.
     *
     *@param    fish    A string representing the fish the weapon is strong against..
     */
    public void setStrongAgainst(String fish)
    {
        this.strongAgainst = fish;
    }

    /**
     * Mutator method to set the maximum catchable fish.
     *
     *@param    max    An int representing the maximum catchable fish.
     */
    public void setMaxFish(int max)
    {
        this.maxFish = max;
    }

    /**
     * Mutator method to set the minimum catchable fish.
     *
     *@param    min    An int representing the minimum catchable fish.
     */
    public void setMinFish(int min)
    {
        this.minFish = min;
    }

    /**
     * Mutator method to set which fish the weapon is weak against.
     *
     *@param    fish    A string representing the fish the weapon is weak against.
     */
    public void setWeakAgainst(String fish)
    {
        this.weakAgainst = fish;
    }

    /**
     * Mutator method to set the weapon name.
     *
     *@param    name    A string representing the weapon name.
     */
    public void setetWeaponName(String name)
    {
        this.weaponName = name;
    }



}
