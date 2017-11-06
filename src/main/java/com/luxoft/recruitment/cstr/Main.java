package com.luxoft.recruitment.cstr;

import java.util.Scanner;
import java.util.Set;

import com.luxoft.recruitment.cstr.http.Request;
import com.luxoft.recruitment.cstr.http.Response;
import com.luxoft.recruitment.cstr.model.IPAdressBlackList;
import com.luxoft.recruitment.cstr.repository.IPAdressBlackListRepository;

public class Main {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println(">> This is a very simple utility class to use the system <<");

			while (true) {
				try {
					printBasicOptions();
					int option = scan.nextInt();

					switch (option) {
						case 1:
							request(scan);
							break;
						case 2:
							registerIPAdresses(scan);
							break;
						case 3:
							removeIPAdresses(scan);
							break;
						case 4:
							removeAllIPAdresses();
						case 5:
							listAllIPAdresses();
							break;
						case 6:
							System.out.print("The application is being finalized...");
							return;
						default:
							System.out.println("Wrong option! Please inform a correct one.");

					}
				} catch (Exception e) {
					System.out.println("Exception trown, please check the StackTrace");
					e.printStackTrace();
					scan.nextLine();
				}
			}
		}

	}

	private static void request(Scanner scan) {
		System.out.print("Please type your IP Adress: ");
		String ipAdress = scan.next();

		SuperApiController superApiController = new SuperApiController();
		Request request = new Request(ipAdress);
		Response response = superApiController.healthCheck(request);

		switch (response.getCode()) {
			case OK:
				System.out.println("Your request was successfull");
				break;
			case BAD_REQUEST:
				System.out.println("Your request failed. Problably your IP Adress is blocked!!!");
				break;
		}
	}

	private static void registerIPAdresses(Scanner scan) {
		System.out.print("Please type how many IP Adresses do you want to register: ");
		String[] ipAdresses = readIPAdresses(scan);

		IPAdressBlackListRepository repository = new IPAdressBlackListRepository();

		for (String ipAdress : ipAdresses) {
			repository.insert(new IPAdressBlackList(ipAdress));
		}
	}

	private static void removeIPAdresses(Scanner scan) {
		System.out.println("Please type how many IP Adresses do you want to remove?");
		String[] ipAdresses = readIPAdresses(scan);

		IPAdressBlackListRepository repository = new IPAdressBlackListRepository();

		for (String ipAdress : ipAdresses) {
			repository.remove(new IPAdressBlackList(ipAdress));
		}
	}

	private static String[] readIPAdresses(Scanner scan) {
		int amount = scan.nextInt();
		String[] ipAdresses = new String[amount];

		for (int i = 0;i < amount;i++) {
			ipAdresses[i] = scan.next();
		}

		return ipAdresses;
	}

	private static void removeAllIPAdresses() {
		IPAdressBlackListRepository repository = new IPAdressBlackListRepository();
		repository.removeAll();
	}

	private static void listAllIPAdresses() {
		IPAdressBlackListRepository repository = new IPAdressBlackListRepository();
		Set<IPAdressBlackList> registers = repository.selectAll();

		if (registers.size() > 0) {
			for (IPAdressBlackList blackList : repository.selectAll()) {
				System.out.println(blackList);
			}
		} else {
			System.out.println("There's no adresses registered");
		}
	}

	private static void printBasicOptions() {
		System.out.println("\n\nPlease select one option: ");
		System.out.println("\t1 - Submit a request;");
		System.out.println("\t2 - Register a new IP Adress to Blacklist to be blocked;");
		System.out.println("\t3 - Remove an IP Adress from the Blacklist;");
		System.out.println("\t4 - Remove all IP Adressess from the Blacklist;");
		System.out.println("\t5 - List all IP Adressess registered;");
		System.out.println("\t6 - Exit the aplication.");
	}

}
