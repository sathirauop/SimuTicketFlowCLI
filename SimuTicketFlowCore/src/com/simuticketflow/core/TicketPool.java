package com.simuticketflow.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
	
	private final List<Ticket> tickets;
	private int capacity;
	private int issuedTicketCount;
	
	public TicketPool(int capacity) {
		tickets =  Collections.synchronizedList(new ArrayList<>());
		this.capacity = capacity;
		issuedTicketCount = 0;
	}
	
    public boolean addTickets(int count, int vendorId) {
        synchronized (tickets) {
        	if(issuedTicketCount>=SimuTicketFlowMain.getTotalTickets()) {
        		return false;
        	}
            for (int i = 0; i < count; i++) {
            	if(tickets.size() >= capacity) {
            		System.out.println("Ticket Pool is full");
            		return false;
            	}
                Ticket ticket = new Ticket(SimuTicketFlowMain.generateTicketId(), vendorId);
                tickets.add(ticket);
                
                issuedTicketCount++;
            }
            if(count == 1) {
            	System.out.println("Vendor "+vendorId+ " added a ticket");
            }else {
            	System.out.println("Vendor "+vendorId+ " added "+count+ " tickets");
            }
            
            return true;
        }
    }
	

    public int getIssuedTicketCount() {
		return issuedTicketCount;
	}

	public Ticket removeTicket() {
        synchronized (tickets) { 
            if (!tickets.isEmpty()) {
                return tickets.remove(0); 
            }
            return null; 
        }
    }

}
	