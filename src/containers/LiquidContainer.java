
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * LiquidContainer is a subclass of HeavyContainer.
 * @author Hakan
 *
 */
public class LiquidContainer extends HeavyContainer{
	/**
	 * Constructor for LiquidContainer.
	 * @param ID ID of the container.
	 * @param weight Weight of the container.
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
	}

	@Override
	public double consumption() {
		return getWeight() * 4.00;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

