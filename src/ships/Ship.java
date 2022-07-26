
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;
import java.util.ArrayList;
import java.util.Collections;

import containers.Container;
import containers.HeavyContainer;
import containers.LiquidContainer;
import containers.RefrigeratedContainer;
import ports.Port;
import interfaces.IShip;



/**
 * Ship class has various fields and methods that are needed for simulation to operate. It implements IShip interface and Comparable interface to sort ArrayLists.
 * @author Hakan
 *
 */
public class Ship implements IShip, Comparable<Ship> {
	/**
	 * ID of the ship.
	 */
	private int ID;
	/**
	 * Current fuel of this ship.
	 */
	private double fuel;
	/**
	 * The current port this ship exists in.
	 */
	private Port currentPort;
	/**
	 * ArrayList of containers that are in this ship.
	 */
	private ArrayList<Container> Containers = new ArrayList<Container>();
	/**
	 * The total weight capacity of this ship.
	 */
	private int totalWeightCapacity;
	/**
	 * The maximum number of any type of containers this ship can contain.
	 */
	private int maxNumberOfAllContainers;
	/**
	 * The maximum number of heavy containers this ship can contain.
	 */
	private int maxNumberOfHeavyContainers;
	/**
	 * The maximum number of refrigerated containers this ship can contain.
	 */
	private int maxNumberOfRefrigeratedContainers;
	/**
	 * The maximum number of liquid containers this ship can contain.
	 */
	private int maxNumberOfLiquidContainers;
	/**
	 * The fuel consumption of this ship per KM.
	 */
	private double fuelConsumptionPerKM;
	
	/**
	 * The constructor for the ship.
	 * @param ID ID of the ship, generated starting from 0 by the simulation.
	 * @param p The port ship was initially created in.
	 * @param totalWeightCapacity The total weight capacity of this ship.
	 * @param maxNumberOfAllContainers The maximum number of any type of containers this ship can contain.
	 * @param maxNumberOfHeavyContainers The maximum number of heavy containers this ship can contain.
	 * @param maxNumberOfRefrigeratedContainers The maximum number of refrigerated containers this ship can contain.
	 * @param maxNumberOfLiquidContainers The maximum number of liquid containers this ship can contain.
	 * @param fuelConsumptionPerKM The fuel consumption of this ship per KM.
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		
	this.ID = ID;
	this.currentPort = p;
	this.totalWeightCapacity = totalWeightCapacity;
	this.maxNumberOfAllContainers = maxNumberOfAllContainers;
	this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
	this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
	this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
	this.fuelConsumptionPerKM = fuelConsumptionPerKM;
	this.fuel = 0.0;
	p.incomingShip(this);
	}
	
	/**
	 * Method for sorting the ArrayList of ships.
	 * @param other The other ship to compare to this ship.
	 */
	@Override
    public int compareTo(Ship other) {
        return this.ID - other.ID;
    }
	/**
	 * Gets the ArrayList of current containers that are on this ship.
	 * @return The ArrayList of current containers that are on this ship.
	 */
	public ArrayList<Container> getCurrentContainers() {
		Collections.sort(Containers);
		return Containers;
	}

	
	/**
	 * Gets the ID of this ship.
	 * @return The ID of this ship.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the current fuel in this ship.
	 * @return The current fuel in this ship.
	 */
	public double getFuel() {
		return fuel;
	}
	/**
	 * Calculates the total fuel consumption according to the consumption of containers that are on this ship and the initial consumption of this ship.
	 * @return The total consumption of this ship per KM.
	 */
	private double totalConsumptionPerKM() {
		double consumption=0;
		double containerConsumption=0;
		
		for (int c=0; c<Containers.size();c++)
			containerConsumption += Containers.get(c).consumption();
		
		consumption = containerConsumption + this.fuelConsumptionPerKM;
		return consumption;
	}
	/**
	 * Checks if the fuel on this ship is enough to get to the other port. Then sails if it is enough.
	 * @param p The port to sail to.
	 * @return True if the sailing was successful.
	 */
	@Override
	public boolean sailTo(Port p) {
		double fuelNeeded = totalConsumptionPerKM() * currentPort.getDistance(p);
		
		if(fuel >= fuelNeeded) {
			currentPort.outgoingShip(this);
			p.incomingShip(this);
			currentPort = p;
			fuel = fuel - fuelNeeded;
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * Adds the given fuel to the current fuel of this ship.
	 * @param newFuel The fuel to add.
	 */
	@Override
	public void reFuel(double newFuel) {
		fuel = fuel + newFuel;
	}
	/**
	 * Gets the number of refrigerated containers that are in this ship.
	 * @return The number of refrigerated containers that are in this ship.
	 */
	private int getNumRefrigerated() {
		int numref = 0;
		for (Container c : Containers) {
			if (c instanceof RefrigeratedContainer)
				numref += 1;
		}
		return numref;
	}
	/**
	 * Gets the number of liquid containers that are in this ship.
	 * @return The number of liquid containers that are in this ship.
	 */
	private int getNumLiquid() {
		int numliq = 0;
		for (Container c : Containers) {
			if (c instanceof LiquidContainer)
				numliq += 1;
		}
		return numliq;
	}
	
	/**
	 * Gets the number of heavy containers that are in this ship.
	 * @return The number of heavy containers that are in this ship.
	 */
	private int getNumHeavy() {
		int numHeavy = 0;
		for (Container c : Containers) {
			if (c instanceof HeavyContainer)
				numHeavy += 1;
		}
		return numHeavy;
	}
	/**
	 * Gets the number of all containers that are in this ship.
	 * @return The number of all containers that are in this ship.
	 */
	private int getNumTotContainer() {
		return Containers.size();
	}
	/**
	 * Gets the total weight of all containers in this ship.
	 * @return The total weight of all containers in this ship.
	 */
	private int currentContainerWeight() {
		int sumWeight = 0;
		for (Container c : Containers)
			sumWeight += c.getWeight();
		
		return sumWeight;
	}
	
	
	/**
	 * Loads the given container to this ship if the requirements are met.
	 * @param cont The container to load.
	 * @return True if loading was successful.
	 */
	@Override
	public boolean load(Container cont) {
		if(!(currentPort.getContainers().contains(cont)))
			return false;
		if(cont.getWeight() + currentContainerWeight() > totalWeightCapacity)
			return false;
		if(getNumTotContainer() == maxNumberOfAllContainers)
			return false;
		if(cont instanceof HeavyContainer && getNumHeavy()== maxNumberOfHeavyContainers)
			return false;
		if(cont instanceof RefrigeratedContainer && getNumRefrigerated() == maxNumberOfRefrigeratedContainers)
			return false;
		if(cont instanceof LiquidContainer && getNumLiquid() == maxNumberOfLiquidContainers)
			return false;

		
		Containers.add(cont);
		currentPort.removeContainer(cont);
		
		return true;
	}
	/**
	 * Unloads the container from this ship if the requirements are met.
	 * @param cont Container to unload.
	 */
	@Override
	public boolean unLoad(Container cont) {
		if(!(Containers.contains(cont)))
			return false;
		Containers.remove(cont);
		currentPort.addContainer(cont);
		return true;
	}
	
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

