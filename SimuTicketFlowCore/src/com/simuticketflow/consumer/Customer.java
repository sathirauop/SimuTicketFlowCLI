package com.simuticketflow.consumer;

import com.simuticketflow.core.SimuTicketFlowMain;
import com.simuticketflow.core.Ticket;
import com.simuticketflow.core.TicketPool;
import java.util.Random;	

public class Customer implements Runnable {
	
	public int customerId;
	public TicketPool ticketPool;
	public int customerRetrievalRate;
	
	public Customer(int customerId, TicketPool ticketPool,int customerRetrievalRate) {
		this.customerId = customerId;
		this.ticketPool = ticketPool;
		this.customerRetrievalRate = customerRetrievalRate;
	}

	@Override
	public void run() {
		
		while(true) {
	        Random random = new Random();
	        int randomrRetrievalRate = random.nextInt(customerRetrievalRate) + 1;
			try {
				Ticket ticket = ticketPool.removeTicket();
				if(ticket == null) {
					if (SimuTicketFlowMain.getTotalTickets() <= ticketPool.getIssuedTicketCount()) 
						break;
					continue;
				}
				System.out.println("ticket number :" +ticket.tickectId + " is tacken by customer: " + customerId + " from vendor :"+ticket.vendorId);
				Thread.sleep(randomrRetrievalRate*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
