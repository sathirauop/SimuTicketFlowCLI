package com.simuticketflow.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.simuticketflow.consumer.Customer;
import com.simuticketflow.producer.Vendor;

public class SimuTicketFlowMain {
	
	private static int ticketID = 1;
	
	private static int totalTickets = 20;

	private static int maxTicketCapacity = 100;
	private static int ticketReleaseRate = 3000;
	private static int customerRetrievalRate = 1;
	
	
	
	
	


	public static void main(String[] args) {
		getInputParameters();
		TicketPool ticketPool = new TicketPool(maxTicketCapacity);
		
		List<Thread> VendorThreads = new ArrayList<>();
		List<Thread> CustomerThreads = new ArrayList<>();
		
		int numofVendors = 5;
		int numofCustomers = 5;
		
		for(int i = 0;i<numofVendors;i++) {
			Vendor vendor = new Vendor(i+1,ticketPool,1,ticketReleaseRate);
			Thread thrd = new Thread(vendor);
			thrd.start();
			VendorThreads.add(thrd);
		}
		
		for(int i = 0;i<numofCustomers;i++) {
			Customer customer = new Customer(i+1,ticketPool,customerRetrievalRate);
			Thread thrd = new Thread(customer);
			thrd.start();
			CustomerThreads.add(thrd);
		}
		
	}
	
    public static void getInputParameters() {
        Scanner scanner = new Scanner(System.in);

        // Get Total Number of Tickets
        System.out.print("Enter Total Number of Tickets: ");
        totalTickets = Integer.parseInt(scanner.nextLine());

        // Get Ticket Release Rate
        System.out.print("Enter Ticket Release Rate: ");
        ticketReleaseRate = Integer.parseInt(scanner.nextLine());

        // Get Customer Retrieval Rate
        System.out.print("Enter Customer Retrieval Rate: ");
        customerRetrievalRate = Integer.parseInt(scanner.nextLine());

        // Get Maximum Ticket Capacity
        System.out.print("Enter Maximum Ticket Capacity: ");
        maxTicketCapacity = Integer.parseInt(scanner.nextLine());

        scanner.close();
    }

	
	public static int generateTicketId() {
		return ticketID++;
	}
	
	public static int getTotalTickets() {
		return totalTickets;
	}

}
