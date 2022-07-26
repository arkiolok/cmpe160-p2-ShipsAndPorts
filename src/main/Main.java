
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import containers.*;
import ports.*;
import ships.*;


/**
 * Main class which enables to read from the data, run the simulation and write to the file again.
 * @author Hakan
 *
 */
public class Main {
	/**
	 * Main method which enables to read from the data, run the simulation and write to the file again.
	 * @param args Arguments are the input and output file.
	 * @throws FileNotFoundException If specified files are not found.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));

		
		int N;
		N = in.nextInt();
		int cID =0;
		int pID =0;
		int sID =0;
		
		ArrayList<Port> Ports = new ArrayList<Port>();
		ArrayList<Ship> Ships = new ArrayList<Ship>();
		ArrayList<Container> Containers = new ArrayList<Container>();


		
		for (int i=0;i<N && in.hasNextLine();i++) {

			int currentOperation = in.nextInt();

			switch (currentOperation) {

			// creating container
			case 1: {
				int portID = in.nextInt();
				int weight = in.nextInt();

				if (in.hasNext("R") || in.hasNext("L")) {
					String specialType = in.next();
					if (specialType.equals("R")) {
						Containers.add(new RefrigeratedContainer(cID, weight));
						Ports.get(portID).addContainer(Containers.get(cID));
					} else {
						Containers.add(new LiquidContainer(cID, weight));
						Ports.get(portID).addContainer(Containers.get(cID));
					}

				} else {
					if (weight <= 3000) {
						Containers.add(new BasicContainer(cID, weight));
						Ports.get(portID).addContainer(Containers.get(cID));
					} else {
						Containers.add(new HeavyContainer(cID, weight));
						Ports.get(portID).addContainer(Containers.get(cID));
					}
				}
				cID++;
				break;
			}

			// creating ship
			case 2: {
				int portID = in.nextInt();
				int totalWeightCapacity = in.nextInt();
				int maxNumberOfAllContainers = in.nextInt();
				int maxNumberOfHeavyContainers = in.nextInt();
				int maxNumberOfRefrigeratedContainers = in.nextInt();
				int maxNumberOfLiquidContainers = in.nextInt();
				double fuelConsumptionPerKM = in.nextDouble();

				Ships.add(new Ship(sID, Ports.get(portID), totalWeightCapacity, maxNumberOfAllContainers,
						maxNumberOfHeavyContainers, maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers,
						fuelConsumptionPerKM));

				sID++;
				break;
			}

			// creating port
			case 3: {
				double X = in.nextDouble();
				double Y = in.nextDouble();

				Ports.add(new Port(pID, X, Y));

				pID++;
				break;
			}

			// loading container to ship
			case 4: {
				int loadSID = in.nextInt();
				int loadCID = in.nextInt();

				Ships.get(loadSID).load(Containers.get(loadCID));
				break;
			}

			// unleoading container from ship
			case 5: {
				int unloadSID = in.nextInt();
				int unloadCID = in.nextInt();

				Ships.get(unloadSID).unLoad(Containers.get(unloadCID));
				break;
			}

			// sail ship
			case 6: {
				int sailSID = in.nextInt();
				int sailPID = in.nextInt();

				Ships.get(sailSID).sailTo(Ports.get(sailPID));
				break;
			}

			// refuel ship
			case 7: {
				int fuelSID = in.nextInt();
				double fuelAdded = in.nextDouble();

				Ships.get(fuelSID).reFuel(fuelAdded);
				break;
			}

			}

		}
		
		//For loop for ports.
		for (int i = 0; i < pID; i++) {
	
			out.printf("Port %d: (%.2f, %.2f)", i, Ports.get(i).getX(), Ports.get(i).getY());

			for (Container c : Ports.get(i).getContainers()) {
				if (c instanceof BasicContainer) {
					out.printf("\n  BasicContainer:");
					break;
				}

			}
			for (Container c : Ports.get(i).getContainers()) {
				if (c instanceof BasicContainer)
					out.printf(" %d", c.getID());

			}

			for (Container c : Ports.get(i).getContainers()) {
				if (c.getClass().equals(HeavyContainer.class)) {
					out.printf("\n  HeavyContainer:");
					break;
				}
			}
			for (Container c : Ports.get(i).getContainers()) {
				if (c.getClass().equals(HeavyContainer.class))
					out.printf(" %d", c.getID());

			}
			for (Container c : Ports.get(i).getContainers()) {
				if (c.getClass().equals(RefrigeratedContainer.class)) {
					out.printf("\n  RefrigeratedContainer:");
					break;
				}
			}
			for (Container c : Ports.get(i).getContainers()) {
				if (c.getClass().equals(RefrigeratedContainer.class))
					out.printf(" %d", c.getID());

			}
			for (Container c : Ports.get(i).getContainers()) {
				if (c.getClass().equals(LiquidContainer.class)) {
					out.printf("\n  LiquidContainer:");
					break;
				}
			}
			for (Container c : Ports.get(i).getContainers()) {
				if (c.getClass().equals(LiquidContainer.class))
					out.printf(" %d", c.getID());

			}

			int sCount = Ports.get(i).getCurrentShipCount();
			
			//For loop for ships.
			for (int j = 0; j < sCount; j++) {
				int shipID = Ports.get(i).getCurrentShips().get(j).getID();
				
				out.printf("\n  Ship %d: %.2f", shipID, Ships.get(shipID).getFuel());
				
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c instanceof BasicContainer) {
						out.printf("\n    BasicContainer:");
						break;
					}

				}
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c instanceof BasicContainer)
						out.printf(" %d", c.getID());
				}

				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c.getClass().equals(HeavyContainer.class)) {
						out.printf("\n    HeavyContainer:");
						break;
					}
				}
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c.getClass().equals(HeavyContainer.class))
						out.printf(" %d", c.getID());
				}
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c.getClass().equals(RefrigeratedContainer.class)) {
						out.printf("\n    RefrigeratedContainer:");
						break;
					}
				}
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c.getClass().equals(RefrigeratedContainer.class))
						out.printf(" %d", c.getID());
				}
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c.getClass().equals(LiquidContainer.class)) {
						out.printf("\n    LiquidContainer:");
						break;
					}
				}
				for (Container c : Ships.get(shipID).getCurrentContainers()) {
					if (c.getClass().equals(LiquidContainer.class))
						out.printf(" %d", c.getID());
				}

			}

			out.printf("\n");
		}
		
		
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

