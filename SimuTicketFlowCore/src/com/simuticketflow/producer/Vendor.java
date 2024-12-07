package com.simuticketflow.producer;

import java.util.concurrent.SynchronousQueue;

import com.simuticketflow.core.SimuTicketFlowMain;
import com.simuticketflow.core.Ticket;
import com.simuticketflow.core.TicketPool;

public class Vendor implements Runnable {
	
	public int vendorId;
	public TicketPool ticketPool;
    private int ticketsPerRelease;
    private long releaseInterval;
	
	
	public Vendor(int vendorId, TicketPool ticketPool,int ticketsPerRelease,long releaseInterval) {
		this.vendorId = vendorId;
		this.ticketPool = ticketPool;
		this.releaseInterval = releaseInterval;
		this.ticketsPerRelease = ticketsPerRelease;
	}

	@Override
	public void run() {
		
		boolean succes = false;

		while (true) {
			if (SimuTicketFlowMain.getTotalTickets() <= ticketPool.getIssuedTicketCount()) {
				break;
			}
			try {
				succes = ticketPool.addTickets(ticketsPerRelease,vendorId);
				if(!succes) {
					continue;
				}
				Thread.sleep(releaseInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
