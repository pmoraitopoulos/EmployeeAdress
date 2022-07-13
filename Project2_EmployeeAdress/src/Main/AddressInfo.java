package Main;
import java.util.*;

/**
 * The AdressInfo class holds full details about an Address and phone number.
 * 
 * @author Moraitopoulos, Panagiotis
 * @version 2022.05.07
 * @class MIS2022
 */


public class AddressInfo {
	private Scanner in = new Scanner(System.in);
	private String address;
	private String phone;
	
	
	public AddressInfo(String address, String phone) {
		this.address = checkAddress(address);
		this.phone = checkPhone(phone);
	}
	
	private String checkAddress(String address) {
		while(address.isBlank()){
			System.out.println("Δεν επιτρέπεται κενή διεύθυνση. Εισάγετε τη σωστή:");
			address = in.nextLine();
		}
		return address;
	}
	
	private String checkPhone(String phone) {	
		while(phone.length() != 10 || !phone.matches("[0-9]+")) {
			System.out.println("Ο αριθμός τηλεφώνου πρέπει να περιέχει 10 ψηφία. Εισάγετε το σωστό: ");
			phone = in.nextLine();
		}
		return phone;
		
	}
	
	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getFullAddress() {
		return "Full Address: " + address + ", phone number: " + phone;
	}

	public void setAddress(String address) {
		this.address = checkAddress(address);
	}

	public void setPhone(String phone) {
		this.phone = checkPhone(phone);
	}
	
	public void updateAddressInfo(String address, String phone) {
		this.address = checkAddress(address);
		this.phone = checkPhone(phone);
		
	}

}
