import java.util.Scanner;

public class CPMS {
	public static void main (String[] args) throws InterruptedException {
		int getTicket, stop=0, menu=0, pn = 0;
		String name, num, type, plate, model, ansC, ansB, ans;
		
		parkingDetails vehicle = new parkingDetails();
		Scanner input = new Scanner(System.in);
		Scanner input_ = new Scanner(System.in).useDelimiter("\\s*name\\s*");
		
		System.out.println(" Hi! Welcome to AEON Carpark.\n");
		System.out.println(" A. Admin || B. AEON Customer");
		System.out.print(" Choose: ");
			ansC = input_.nextLine();
		
		//THIS IS FOR THE CARPARK ADMIN
		switch(ansC) {
			case "A": {
				menu:
					do {
						System.out.println("\n Here's the menu if you want to:");
						System.out.println(" A. Add Vehicles || B. Display parked vehicles\n C. Departure || D. Admin Logout");
						System.out.print(" Choose: ");
							ansB = input_.nextLine();
						
						if (ansB.equals("A")) {
							int slot;
							
							slot = vehicle.parkingSlot();
							
							if (slot == 1 || slot == -1) {
								System.out.println("\n Slot is available!");
								Thread.sleep(1000);
								
								again:
								do {
									System.out.println("\n What is the customers' vehicle type: .");
									System.out.println(" A. Motor || B. Car ");
									System.out.print(" Choose: ");
										ans = input_.nextLine();
									
									Thread.sleep(1000);
									System.out.println("\n Noted.");
									Thread.sleep(1000);
									
									if (ans.equals("A")) {
										System.out.println("\n Please fill in the details.");
										System.out.print(" Owner Name: ");
											name = input_.nextLine();
										System.out.print(" Owner Number: ");
											num = input_.nextLine();
										System.out.print(" Plate Number: ");
											plate = input_.nextLine();
										
										Motor mDetails = new Motor(name, num, "Motor", plate);
										Thread.sleep(1000);
										System.out.println("\n Motor successfully parked!\n Parking No.: " + pn++);
										try {
											vehicle.addVehicle(mDetails);
										}
										catch (vException e) {
											System.out.println(e.getMessage());
										}
										continue menu;
										//stop = 1;
									}
									else if (ans.equals("B")) {
										System.out.println(" Please fill in the details.");
										System.out.print(" Owner Name: ");
											name = input.nextLine();
										System.out.print(" Owner Number: ");
											num = input.nextLine();
										System.out.print(" Plate Number: ");
											plate = input.nextLine();
										
										Car cDetails = new Car(name, num, "Car", plate);
										Thread.sleep(1000);
										System.out.println("\n Car successfully parked!\n Parking No.: " + pn++);
										try {
											vehicle.addVehicle(cDetails);
										}
										catch (vException e) {
											System.out.println(e.getMessage());
										}
										continue menu;
									}
									else {
										System.out.println("\n Incorrect input. Please try again.");
										continue again;
									}
									//stop = 1;
								} while (stop!=1);
							}
							else {
								System.out.println("\n Slot is full! Please depart some of the vehicles in the parking lot.");
								Thread.sleep(1000);
								continue menu;
							}
							
						}		
						else if (ansB.equals("B")) {
							System.out.println("\n ~PARKED VEHICLES~ ");
							vehicle.display();
							Thread.sleep(1500);
							continue menu;
						}
						else if (ansB.equals("C")) {
							vehicle.departure();
							menu = 1;
						}
						else if (ansB.equals("D")) {
							System.out.println("\n Have a great day, Admin!");
						}
						else {
							System.out.println("\n Incorrect input. Please try again.");
							continue menu;
						}
					} while (!ansB.equals("D"));
				break;
			}
			
			//THIS IS FOR THE AEON CUSTOMERS WHEN THEY ARRIVE AT THE PARKING
			case "B": {
				System.out.println(" Hi! Welcome to AEON Carpark.\n");
				System.out.println(" Please press 1 to get your ticket.");
				System.out.print(" Press: ");
					getTicket = input.nextInt();
				
				switch(getTicket) {
					case 1: {
						int slot, dep=0;
						
						slot = vehicle.parkingSlot();
						
						if (slot == 1) {
							System.out.println("\n Slot is available!");
							Thread.sleep(1000);
							
							again:
							do {
								System.out.println("\n What is your vehicle type: .");
								System.out.println(" A. Motor || B. Car ");
								System.out.print(" Choose: ");
									ans = input_.nextLine();
								
								Thread.sleep(1000);
								System.out.println("\n Noted.");
								Thread.sleep(1000);
								
								if (ans.equals("A")) {
									System.out.println("\n Please fill in your details.");
									System.out.print(" Owner Name: ");
										name = input_.nextLine();
									System.out.print(" Owner Number: ");
										num = input_.nextLine();
									System.out.print(" Plate Number: ");
										plate = input_.nextLine();
									
									Motor mDetails = new Motor(name, num, "Motor", plate);
									Thread.sleep(1000);
									System.out.println("\n Motor successfully parked!\n Parking No.: " + pn++);
									try {
										vehicle.addVehicle(mDetails);
									}
									catch (vException e) {
										System.out.println(e.getMessage());
									}
									Thread.sleep(1500);
									menu:
										do {
											System.out.println("\n Here's the option if you want to:");
											System.out.println(" A. Departure");
											System.out.print(" Choose: ");
												ansB = input_.nextLine();
												
											if (ansB.equals("A")) {									
												char stats, stat='0';
											depart:
												do {
													vehicle.departure();
													stats = vehicle.removeVehicle(stat);
													if (stats == '1') {
														System.out.println(" Please pay in a correct amount. Car cannot depart successfully.");
														continue depart;
													}
													else {
														System.out.println(" Thank you for coming! Drive home safely.");
														dep = 1;
														menu = 1;
														stop = 1;
													}
												} while (dep!=1);
											}
											else {
												System.out.println("\n Incorrect input. Please try again.");
												continue menu;
											}
										} while (menu!=1);
									//stop = 1;
								}
								else if (ans.equals("B")) {
									System.out.println(" Please fill in your details.");
									System.out.print(" Owner Name: ");
										name = input_.nextLine();
									System.out.print(" Owner Number: ");
										num = input_.nextLine();
									System.out.print(" Plate Number: ");
										plate = input_.nextLine();
									
									Car cDetails = new Car(name, num, "Car", plate);
									Thread.sleep(1000);
									System.out.println("\n Car successfully parked!\n Parking No.: " + pn++);
									try {
										vehicle.addVehicle(cDetails);
									}
									catch (vException e) {
										System.out.println(e.getMessage());
									}
									
									menu:
										do {
											System.out.println("\n Here's the option if you want to:");
											System.out.println(" A. Departure");
											System.out.print(" Choose: ");
												ansC = input_.nextLine();
												
											if (ansC.equals("A")) {
												char stats, stat='0';
											depart:
												do {
													vehicle.departure();
													stats = vehicle.removeVehicle(stat);
													if (stats == '1') {
														System.out.println(" Please pay in a correct amount. Car cannot depart successfully.");
														continue depart;
													}
													else {
														System.out.println(" Thank you for coming! Drive home safely.");
														dep = 1;
														menu = 1;
														stop = 1;
														
													}
												} while (dep!=1);
											}
											else {
												System.out.println("\n Incorrect input. Please try again.");
												continue menu;
											}
										} while (menu!=1);
									//stop = 1;
								} 
							} while (stop!=1);
						}
						else
						break;
					}
				}
				break;
			}
		}
	}
}