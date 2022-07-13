package Main;
import java.util.*;

/**
 * The Employee class represents a list of Employees. 
 * These Employees have a full name, two addresses and a debt.
 * Employees can pay their debt or get a reminder about their debt.
 * 
 * 
 * @author Moraitopoulos, Panagiotis
 * @version 2022.05.07
 * @class MIS2022
 */

public class Employee {
	private Scanner in = new Scanner(System.in);
	
	private String name;
	private AddressInfo homeAddress;
	private AddressInfo workAddress;
	private int debt;
	private int reminder=0;
	private int change = 0;
	
	public Employee (String name, String homeAddress, String homePhone, String workAddress, String workPhone, int debt) {
		this.name = checkName(name);
		this.homeAddress = new AddressInfo(homeAddress, homePhone);
		this.workAddress = new AddressInfo(workAddress, workPhone);
		this.debt = checkDebt(debt);
	}
	
	public Employee (String name, AddressInfo homeAddress, AddressInfo workAddress, int debt) {
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.name = checkName(name);
		this.debt = checkDebt(debt);
	}
	
	private String checkName(String name) {
		while(!name.matches("[a-zA-Z]+( +[a-zA-Z]+)*") || name.isBlank()) {
			System.out.println();
			name = in.nextLine();
		}
		 return name;
	}
	
	private int checkDebt(int debt) {
		while(debt<0) {
			System.out.println("Μη αποδεκτό ποσό. Εισάγετε εκ νέου");
			debt = in.nextInt();
		}
		
		return debt;
	}

	public String getName() {
		return name;
	}

	public int getDebt() {
		return debt;
	}

	public int getReminder() {
		return reminder;
	}

	public void setName(String name) {
		this.name = checkName(name);
	}

	public void setHomeAdress(AddressInfo homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setWorkAdress(AddressInfo workAddress) {
		this.workAddress = workAddress;
	}
	
	public void setDebt(int debt) {
		this.debt = checkDebt(debt);
	}
	
	private void checkPayment(int ammount) {
		if(debt>=ammount) {
			change = 0;
			debt-=ammount;
		}
		else {
			change = ammount-debt;
			debt = 0;
		}
	}
	
	
	private void pay(int ammount) {
		
		System.out.println("---------------------- ΕΝΑΡΞΗ ΔΙΑΔΙΚΑΣΙΑΣ ΠΛΗΡΩΜΗΣ---------------------- ");
		System.out.println("Εισάγατε " + ammount + " ευρώ. Θέλετε να γίνει η κατάθεση όλου του ποσού ή θέλετε συγκεκριμένο ποσό.");
		System.out.println("0=> Ακύρωση Διαδικασίας, 1=> Όλο το ποσό, 2=> Διαφορετικό ποσό");
		int i = in.nextInt();
		while (i!=0 && i!=1 && i!=2) {
			System.out.println("Λάθος Επιλογή: ");
			System.out.println("0=> Ακύρωση Διαδικασίας, 1=> Όλο το ποσό, 2=> Διαφορετικό ποσό");
			i = in.nextInt();
		}
		
		switch(i) {
			case 0:
				System.out.println("Ακυρώσατε τη διαδικασία κατάθεσης.");
				System.out.println("Παραλάβετε το ποσό που καταθέσατε ("+ammount+" euro)");
				ammount = 0;
				System.out.println("---------------------- ΤΕΡΜΑΤΙΣΜΟΣ ΔΙΑΔΙΚΑΣΙΑΣ ΠΛΗΡΩΜΗΣ---------------------- ");
				break;
			case 1:
				System.out.println("Καταθέσατε όλο το ποσό.");
				this.checkPayment(ammount);
				System.out.println("---------------------- ΤΕΡΜΑΤΙΣΜΟΣ ΔΙΑΔΙΚΑΣΙΑΣ ΠΛΗΡΩΜΗΣ---------------------- ");
				break;
			case 2:
				System.out.println("Πληκτρολογήστε το νέο ποσό");
				int newAmmount = in.nextInt();
				
				while (newAmmount<=0||newAmmount>ammount) {
					System.out.println("Πληκτρολογήσατε ποσό εκτός ορισμού. Το αρχικό σας ποσό είνα: " +ammount+ " euro. Εισάγετε εκ νέου");
					newAmmount = in.nextInt();
				}
				
				this.checkPayment(newAmmount);
				System.out.println("---------------------- ΤΕΡΜΑΤΙΣΜΟΣ ΔΙΑΔΙΚΑΣΙΑΣ ΠΛΗΡΩΜΗΣ---------------------- ");
				break;
				}
		}
	
	
	private void receipt(int ammount) {
		System.out.println();
		System.out.println("---------- Receipt ------------------");
		System.out.println(name + " paid " + ammount + " Euro");
		System.out.println(homeAddress.getFullAddress());
		System.out.println("The rest is " + debt + " Euro");
		System.out.println("Your change is: " + change);
		System.out.println("-------------------------------------");
	}
	
	public void payment(int ammount) {
		this.pay(ammount);
		this.receipt(ammount);
	}
	
	private void increaseReminder() {
		if(debt>0) {
			++reminder;
		}
		else
			reminder = 0;
	}
	
	private void message (AddressInfo address) {
		System.out.println();
		System.out.println("---------- Reminder "+reminder+" ----------");
		System.out.println(name);
		System.out.println(address.getFullAddress());
		System.out.println("Please pay "+debt+" Euro by the end of the month");
		System.out.println("-------------------------------------");
	}
	
	private void recordedMessage(AddressInfo address) {
		
		System.out.println();
		System.out.println("---------- Recorded message ---------");
		System.out.println("---------- Reminder "+reminder+" ----------");
		System.out.println("I am calling "+address.getPhone()+" ...");
		System.out.println(name);
		System.out.println("Please pay "+debt+" Euro by the end of the month");
		System.out.println("-------------------------------------");	
	}
	
	public void Reminder() {
		
		this.increaseReminder();
		
		switch(reminder) {
		case 0:
			System.out.println();
			System.out.println("-------------------------------------");
			System.out.println("Λανθασμένη Ειδοποίηση."+"\n" + "Ο Υπάλληλος έχει " + debt + " υπόλοιπο.");
			System.out.println("-------------------------------------");
			break;
		case 1: 
			this.message(workAddress);
			break;
		case 2: 
			this.message(homeAddress);
			break;
		case 3:
			this.recordedMessage(workAddress);
			break;
		case 4:
			this.recordedMessage(homeAddress);
			break;
		default:
			this.recordedMessage(homeAddress);
			System.out.println("---------- IMPORTANT NOTICE!!! ----------");
			System.out.println("Your debt will be subtracted from your salary.");
			System.out.println("-------------------------------------");
			break;
		}
	}
	
	public void printEmployee()
	{
		System.out.println(name);
		System.out.println("Home address: " + homeAddress.getAddress());
		System.out.println("work address: " + workAddress.getAddress());
		System.out.println("Debt: " + debt + " Euro");
	}
}