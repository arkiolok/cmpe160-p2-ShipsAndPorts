
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * Class of containers used to store goods.
 * @author Hakan
 *
 */
public abstract class Container implements Comparable<Container>{
	/**
	 * ID of this container.
	 */
	private int ID;
	/**
	 * Weight of this container.
	 */
	private int weight;
	
	/**
	 * The constructor for the container.
	 * @param ID ID of the container.
	 * @param weight Weight of the container.
	 */
	Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	
	
	
	/**
	 * Returns the weight of this container
	 * @return The weight of this container.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Method for calculating the fuel consumption of this container per KM.
	 * @return The fuel consumption of this container per KM.
	 */
	public abstract double consumption();
	/**
	 * Method for comparing two containers.
	 * @param other The container to compare to.
	 * @return Returns true if they are identical.
	 */
	public boolean equals(Container other) {
		if (getClass() == other.getClass() && ID == other.getID() && weight == other.getWeight()) {
		return true;
		} else 
			return false;
	}
    public int compareTo(Container other) {
        return this.ID - other.ID;
    }

	/**
	 * Gets the ID of the container.
	 * @return The ID of the container.
	 */
	public int getID() {
		return ID;
	}

	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

