
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * HeavyContainer is the class of containers with no R or L attributes and which are weight is bigger than 3000 KG.
 * @author Hakan
 *
 */

public class HeavyContainer extends Container {
	/**
	 * Constructor for the HeavyContainer.
	 * @param ID ID of the container.
	 * @param weight Weight of the container.
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
	}

	@Override
	public double consumption() {
		return getWeight() * 3.00;
	}


	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

