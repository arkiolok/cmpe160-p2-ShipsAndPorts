
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * RefrigeratedContainer is a subclass of HeavyContainer.
 * @author Hakan
 *
 */
public class RefrigeratedContainer extends HeavyContainer {
	
	/**
	 * Constructor for RefrigeratedContainer.
	 * @param ID ID of the container.
	 * @param weight Weight of the container.
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
	}
	
	@Override
	public double consumption() {
		return getWeight() * 5.00;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

