import java.util.Scanner;
import java.util.ArrayList;

public class parkingDetails implements Vehicle {
	private String vType;
	private String vPlate;
	private String ownerName;
	private String ownerNumber;
	private java.util.Date entry, depart;
	private int count;
	
	//String[][] vArray = new String[5][6];
	private ArrayList<parkingDetails> vList = new ArrayList<parkingDetails>(7);
	
	public parkingDetails(String name, String num, String type, String plate) {
		super();
		this.ownerName = name;
		this.ownerNumber = num;
		this.vType = type;
		this.vPlate = plate;
		entry = new java.util.Date();
		depart = new java.util.Date();
	}
	
	public parkingDetails() {}
	
	public String getvType() {
		return vType;
	}
	
	public void setvType(String type) {
		this.vType = type;
	}
	
	public String getvPlate() {
		return vPlate;
	}
	
	public void setvPlate(String plate) {
		this.vPlate = plate;
	}
	
	public String getownerName() {
		return ownerName;
	}
	
	public void setownerName(String name) {
		this.ownerName = name;
	}
	
	public java.util.Date getentry() {
		return entry;
	}
	public java.util.Date getdepart() {
		return depart;
	}

	public void addVehicle(parkingDetails parkingDetails) throws vException {
			vList.add(parkingDetails);
			count++;
	}
	
	@Override
	public void display() {
		for (parkingDetails v : vList) {
			System.out.println(v);
		}
	}
	
	@Override
	public void departure() {
		int pn;
		String type;
		int price = 0;
		Scanner input = new Scanner(System.in);
		
		System.out.print(count);
		
		System.out.println("\n To depart, please fill in the details.");
		System.out.print(" Vehicle Type: ");
			type = input.nextLine();
		System.out.print(" Parking Number: ");
			pn = input.nextInt();
		
		for(parkingDetails v : vList) {
			if (type.equals("Motor")) {
				if((type).equals(v.vType))
				price += 15;
			}
			else if (type.equals("Car")) {
				if((type).equals(v.vType))
					price += 30;
			}
		}
		
		if (price == 15 || price == 30) {
			System.out.println(" Amount to be paid: RM" + price);
			try {
				payment(price, pn);
			} 
			catch(InterruptedException e) {
			}
		}
		else {
			System.out.println(" Incorrect vehicle type.");
		}
	}
	
	public void payment(int price, int pn) throws InterruptedException {
		int change, pay;
		char status;
		int parkingNo = pn;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("\n My Payment: RM ");
			pay = input.nextInt();
		
		System.out.println("\n Calculating...");
		Thread.sleep(2000);
		
		change = pay - price;
		System.out.print("\n Change: " + change + "\n");
		
		if (change < 0) {
			status = 'F';
			removeVehicle(status);
		}
		else {
			status = 'P';
			removeVehicle(status);
			removeVehicle(status, pn);
		}
	}
	
	public char removeVehicle(char stat) {
		if (stat == 'F') {
			return stat = '1';
		}
		else {
			return stat = '0';
		}
	}
	public void removeVehicle(char stat, int pos) {
		//String plateNum = plate;
		if (stat == 'F') {
			
		}
		else {
			vList.remove(pos);
			count--;
		}
	}
	public int Pos(){
		count++;
		return count;
	}
	@Override
	public String toString() {
		return entry + "\n Parking No.: " + count++ + "\n Owner: " + ownerName + "\n Car Type: " + vType + "\n Plate No.: " + vPlate + "\n Owner No.: " + ownerNumber + "\n";
	}
	public int parkingSlot() {
		
		if ((vList==null)||(vList.size()==0)) {
			return 1;
		}
		else if (vList.size()==5)
			return 0;
		else 
			return -1;
	}
	
}