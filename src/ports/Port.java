
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;
import java.util.ArrayList;
import java.util.Collections;

import containers.Container;
import interfaces.IPort;
import ships.Ship;

/**
 * Port class is consistent of several methods and fields. It implements IPort interface.
 * @author Hakan
 *
 */
public class Port implements IPort{
	/**
	 * ID of this port.
	 */
	private int ID;
	/**
	 * X coordinate of this port.
	 */
	private double X;
	/**
	 * Y coordinate of this port.
	 */
	private double Y;
	/**
	 * ArrayList of containers that are in this port.
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * ArrayList of ships that are currently on this port.
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * ArrayList of ships that has visited this port.
	 */
	private ArrayList<Ship> current = new ArrayList<Ship>();
		
/**
 * 
 * Constructor for port.
 * @param ID ID of the port. Generated starting from 0 by the simulation.
 * @param X X coordinate of the port.
 * @param Y Y coordinate of the port.
 */
public Port(int ID, double X, double Y) {
	this.ID = ID;
	this.X = X;
	this.Y = Y;
	
}

/**
 * Gets the number of ships that are on this port.
 * @return Returns the number of ships that are on this port.
 */
public int getCurrentShipCount() {
	return current.size();
}

/**
 * Gets the ArrayList of ships currently on this port.
 * @return Returns the ArrayList of ships currently on this port.
 */
public ArrayList<Ship> getCurrentShips() {
	Collections.sort(current);
	return current;
}

/**
 * Gets the containers that are on this port
 * @return The containers that are on this port.
 */
public ArrayList<Container> getContainers() {
	Collections.sort(containers);
	return containers;
}
/**
 * Gets the distance from the port to the other port.
 * @param other The port to measure the distance.
 * @return Returns the distance from the port to the other port.
 */
public double getDistance(Port other) {
	double distance = Math.sqrt( Math.pow((this.X - other.X), 2)  + Math.pow((this.Y - other.Y), 2) );
	return distance;
}

/** Adds the ship to the current ArrayList of port.
 * @param s The ship to add. 
 */
@Override
public void incomingShip(Ship s) {
	current.add(s);
}
/** 
 * Removes the ship from the current ArrayList and adds it (if it doesn't exist) in the history ArrayList.
 * @param s The ship to remove.
 */
@Override
public void outgoingShip(Ship s) {
	if (!history.contains(s)) {
		history.add(s);
	}
	current.remove(s);
}

/**
 * Adds the container to the containers ArrayList of this port.
 * @param c The container to add.
 */

public void addContainer(Container c) {
	containers.add(c);
}
/**
 * Removes the container from the containers ArrayList of this port.
 * @param c The container to remove.
 */
public void removeContainer(Container c) {
	containers.remove(c);
}

/**Gets the x coordinate of this port.
 * @return The x coordinate of this port.
 */
public double getX() {
	return X;
}

/**
 * Gets the y coordinate of this port.
 * @return The y coordinate of this port.
 */
public double getY() {
	return Y;
}


}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

