/*
 * this class is to create ReentrantLock for threads. 
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.*;

public class Moves {
	
	static public void lock(){
		mylock.lock();
	}
	
	
	static public void unlock(){
		mylock.unlock();
	}
	
	private static Lock mylock= new ReentrantLock();
}

