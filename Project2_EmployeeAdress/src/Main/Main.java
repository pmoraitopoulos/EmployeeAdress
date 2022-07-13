package Main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Employee Nikos = new Employee("Nikos Papadopoulos",
									new AddressInfo("Egnatias 150, Thessaloniki, 54634","2310234567"),
									new AddressInfo("Delfwn 40,Thessaloniki, 44656", "2310897678"), 
									5000);
		Employee Me = new Employee("Panagiotis Moraitopoulos",
									new AddressInfo("Agiou Dimitriou 117, Thessaloniki, 54634", "2314033058"),
									new AddressInfo("Xrisostomou Smirnis 11, Sindos, 57400","2310799411"),
									4000);
	for(int i =1; i<5; ++i) {
		Nikos.Reminder();
	}
	
	Me.payment(4000);;
	Me.Reminder();
		
	Nikos.payment(2000);
	Nikos.Reminder();
	
	Nikos.payment(3000);
	Nikos.Reminder();
	
	}

}
