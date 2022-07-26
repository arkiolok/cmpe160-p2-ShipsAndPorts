
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * BasicContainer is the class of containers with no R or L attributes and which are weight is less than or equal to 3000 KG.
 * @author Hakan
 *
 */
public class BasicContainer extends Container {
	/**
	 * Constructor for the BasicContainer.
	 * @param ID ID of the container.
	 * @param weight Weight of the container.
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	@Override
	public double consumption() {
		return getWeight() * 2.50;
	}



}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

