package builders;


public class Schedule {
	/**
	 * A job descriptor.
	 */

	private final int MAXPRIO = 3;

	//private int allocProcNum;

	private int numProcesses;

	private Job curProc;

	/**
	 * 0th element unused
	 */
	private List[] prioQueue = new List[MAXPRIO+1];

	private List blockQueue;

	/**
	 * Constructor assuming priority 3
	 */
	public Schedule (int numProc3, int numProc2, int numProc1) {
		initialize();
		initPrioQueue(3,numProc3);
		initPrioQueue(2,numProc2);
		initPrioQueue(1,numProc1);
	}
	
	private List appendEle(List aList, Job aEle) {
		if(aList == null) {
			aList = new List();/* make list without compare function */
		}
		
		aEle.setPrev(aList.getLast()); /* insert at the tail */
		if(aList.getLast() != null) {
			aList.getLast().setNext(aEle);
		} else {
			aList.setFirst(aEle);
		}
		aList.setLast(aEle);
		aEle.setNext(null);
		aList.setMemCount(aList.getMemCount() + 1);
		return aList;
	}

	private Job findNth(List fList, int n) {
		Job fEle;

		if(fList == null) {
			return null;
		}
		fEle = fList.getFirst();
		for(int i = 1; fEle != null && i < n; i++ ) {
			fEle = fEle.getNext();
		}
		return fEle;
	}

	private List delEle(List dList, Job dEle) {
		if(dList == null || dEle == null) {
			return null;
		}

		if(dEle.getNext() != null) {
			dEle.getNext().setPrev(dEle.getPrev());
		} else {
			dList.setLast(dEle.getPrev());
		}
		if(dEle.getPrev() != null) {
			dEle.getPrev().setNext(dEle.getNext());
		} else {
			dList.setFirst(dEle.getNext());
		}
		/* KEEP d_ele's data & pointers intact!! */
		dList.setMemCount(dList.getMemCount() - 1);
		return dList;
	}

	public void finishProcess() {
		schedule();
		if(curProc != null) {
			curProc = null;
			numProcesses--;
		}
	}

	public void finishAllProcesses() {
		int total;
		total = numProcesses;
		for(int i = 0; i < total; i++) {
			finishProcess();
		}
	}

	private void schedule() {
		curProc = null;
		for(int i = MAXPRIO; i > 0; i--) {
			if(prioQueue[i].getMemCount() > 0) {
				curProc = prioQueue[i].getFirst();
				prioQueue[i] = delEle(prioQueue[i], curProc);
				break;
				
			}
		}
	}

	public void upgradeProcessPrio(int prio, float ratio) {
		if (prio<1 || prio>MAXPRIO)
			throw new IllegalArgumentException();
		int count;
		int n;
		Job proc;
		List srcQueue, destQueue;

		if(prio >= MAXPRIO) {
			return;
		}
		srcQueue = prioQueue[prio];
		destQueue = prioQueue[prio + 1];
		count = srcQueue.getMemCount();

		if(count > 0) {
			n = (int)(count * ratio + 1);
			proc = findNth(srcQueue, n);
			if(proc != null) {
				srcQueue = delEle(srcQueue, proc);
				/* append to appropriate prio queue */
				proc.setPriority((short)prio + 1);
				destQueue = appendEle(destQueue, proc);
			}
		}
	}

	public void unblockProcess(float ratio) {
		int count;
		int n;
		Job proc;
		int prio;
		if(blockQueue != null) {
			count = blockQueue.getMemCount();
			n = (int)(count * ratio + 1);
			proc = findNth(blockQueue, n);
			if(proc != null) {
				blockQueue = delEle(blockQueue, proc);
				/* append to appropriate prio queue */
				prio = proc.getPriority();
				prioQueue[prio] = appendEle(prioQueue[prio], proc);
			}
		}
	}

	public void quantumExpire() {
		int prio;
		schedule();
		if(curProc != null) {
			prio = curProc.getPriority();
			prioQueue[prio] = appendEle(prioQueue[prio], curProc);
		}
	}

	public void blockProcess() {
		schedule();
		if(curProc != null) {
			blockQueue = appendEle(blockQueue, curProc);
		}
	}

	private Job newProcess(int prio) {
		if (prio<1 || prio>MAXPRIO)
			throw new IllegalArgumentException();
		//Job proc = new Job(allocProcNum++);
		Job proc = new Job(0);

		proc.setPriority((short)prio);
		numProcesses++;
		return proc;
	}

	public void addProcess(int prio) {
		if (prio<1 || prio>MAXPRIO)
			throw new IllegalArgumentException();
		Job proc;
		proc = newProcess(prio);
		prioQueue[prio] = appendEle(prioQueue[prio], proc);
	}

	private void initPrioQueue(int prio, int numProc) {
		if (prio<1 || prio>MAXPRIO)
			throw new IllegalArgumentException();
		if ( prioQueue[prio] != null)
			throw new IllegalArgumentException();
		List queue;
		Job proc;

		queue = new List();
		for(int i = 0; i < numProc; i++) {
			proc = newProcess(prio);
			queue = appendEle(queue, proc);
		}
		prioQueue[prio] = queue;
	}

	private void initialize() {
		//allocProcNum = 0;
		numProcesses = 0;
		blockQueue = new List();
	}
	
	
	
	public String toString() {
		 if (numProcesses == 0) {
			 return "Schedule={}";
	     } else {
	    	 StringBuffer buf = new StringBuffer(512);
	         buf.append("Schedule = {");
	         buf.append(" prioQueue = { ");
	 		for (int i = 1; i <= MAXPRIO; i++) {
	 			 List proc = prioQueue[i];
	 			 buf.append(proc.toString());
	 		 }
	         buf.append(" }");
	         buf.append(" , ");
	         buf.append(" blockQueue = { ");
	         buf.append(blockQueue.toString());
	         buf.append(" }");
	         buf.append(" currProc = { ");
	         buf.append(curProc == null ? "null" : curProc.toString());
	         buf.append(" }");
	         buf.append(" }");
	         return buf.toString();
	     }
	}
	
}
