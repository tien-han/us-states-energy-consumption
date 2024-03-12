import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * State represents a singular state and it's energy consumption data.
 * These energy consumption amounts can be changed by increases and decreases.
 */
public class State {
    private final String name;
    private double coal;
    private double solar;
    private final Lock energyChangeLock;

    /**
     * Constructs a new state with no name or energy consumption data.
     */
    public State() {
        this(null);
    }

    /**
     Constructs a new state with a name but no energy consumption data.
     @param name the name of the state
     */
    public State(String name) {
        this(name, "coal", 0);
    }

    /**
     Constructs a new state with a name and energy consumption data for one energy type.
     @param name the name of the state
     @param energy the type of energy
     @param amount the amount of energy consumption
     */
    public State(String name, String energy, double amount) {
        this.energyChangeLock = new ReentrantLock();
        this.name = name;
        this.coal = (energy.equals("coal") ? setEnergy(energy, amount) : 0);
        this.solar = (energy.equals("solar") ? setEnergy(energy, amount) : 0);
    }

    /**
     * Gets the current energy consumption amount for a given energy type.
     * @param energy the type of energy
     * @return the amount of energy consumption for the given energy type
     */
    public double getEnergy(String energy) {
        this.energyChangeLock.lock();
        try {
            if (energy.equals("coal")) {
                return this.coal;
            } else if (energy.equals("solar")) {
                return this.solar;
            }
        } finally {
            this.energyChangeLock.lock();
        }
        return 0;
    }

    /**
     * Sets the current energy consumption amount for a particular energy type
     * @param energy the type of energy
     * @param amount the amount of energy consumption
     */
    public double setEnergy(String energy, double amount) {
        this.energyChangeLock.lock();
        try {
            if (energy.equals("coal")) {
                this.coal = amount;
            } else if (energy.equals("solar")) {
                this.solar = amount;
            }
        } finally {
            this.energyChangeLock.unlock();
        }
        return amount;
    }

    /**
     * Increases the consumption amount for a given energy type.
     * @param energy the energy type
     * @param amount the amount to increase
     */
    public void increase(String energy, double amount) {
        this.energyChangeLock.lock();
        try {
            double currEnergyAmt = getEnergy(energy) + amount;
            setEnergy(energy, currEnergyAmt);
        } finally {
            this.energyChangeLock.unlock();
        }
    }

    /**
     * Decreases the consumption amount for a given energy type.
     * @param energy the energy type
     * @param amount the amount to decrease
     */
    public void decrease(String energy, double amount) {
        this.energyChangeLock.lock();
        try {
            double currEnergyAmt = getEnergy(energy) - amount;
            if (currEnergyAmt < 0) {
                currEnergyAmt = 0;
            }
            setEnergy(energy, currEnergyAmt);
        } finally {
            this.energyChangeLock.unlock();
        }
    }

    /**
     * Print the state and state attributes in a human-readable format
     * @return a String showing the state details as an object
     */
    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", coal=" + coal +
                ", solar=" + solar +
                '}';
    }
}
