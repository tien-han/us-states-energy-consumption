import java.util.Dictionary;
import java.util.Hashtable;

/**
 * States keeps track of all states that we have records for
 */

public class States {
    private Dictionary<String, State> states;

    /**
     * Constructs a dictionary with a starting number of states.
     */
    public States() {
        this.states = new Hashtable<>();

        //Create several starting states with energy consumption information for coal and solar
        //Washington 2019 commercial consumption
        State wa = new State("WA", "coal", 0.0);
        wa.setEnergy("solar", 264.0);
        //California 2019 commercial consumption
        State ca = new State("CA", "coal", 0.0);
        ca.setEnergy("solar", 33643.0);
        //Indiana 2019 commercial consumption
        State in = new State("IN", "coal", 1397.0);
        in.setEnergy("solar", 650.0);

        //Add our states into our state dictionary
        this.states.put("WA", wa);
        this.states.put("CA", ca);
        this.states.put("IN", in);
    }

    /**
     * Increases the consumption amount for a given state and energy type.
     * @param stateName the state
     * @param energy the energy type
     * @param amount the amount to increase
     */
    public void increase(String stateName, String energy, double amount) {
        State state = states.get(stateName);

        if (state == null) { //If we don't have the state yet, create a record for it
            this.states.put(stateName, new State(stateName, energy, amount));
        } else {
            state.increase(energy, amount);
        }
    }

    /**
     * Decreases the consumption amount for a given state and energy type.
     * @param stateName the state
     * @param energy the energy type
     * @param amount the amount to decrease
     */
    public void decrease(String stateName, String energy, double amount) {
        State state = states.get(stateName);

        if (state == null) { //If we don't have the state yet, create a record for it
            this.states.put(stateName, new State(stateName, energy, amount));
        } else {
            state.decrease(energy, amount);
        }
    }

    /**
     * Gets the balance for a given state and energy type
     * @param stateName the state
     * @param energy the energy type
     * @return the current energy consumption amount for the given state and energy type
     */
    public double getAmount(String stateName, String energy) {
        State state = states.get(stateName);

        if (state == null) { //If we don't have the state yet, create a record for it
            this.states.put(stateName, new State(stateName, energy, 0));
            return 0;
        }
        return state.getEnergy(energy);
    }

    @Override
    public String toString() {
        return "States{" +
                "states=" + states +
                '}';
    }
}
