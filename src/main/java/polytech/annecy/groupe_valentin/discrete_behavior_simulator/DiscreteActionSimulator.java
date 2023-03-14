
package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import polytech.annecy.groupe_valentin.action.DiscreteActionInterface;


/**
 * @author Tiphaine Bulou (2016)
 * @author Flavien Vernier
 *
 */
public class DiscreteActionSimulator implements Runnable {


	private Thread t;
	private boolean running = false;
	
	private Clock globalTime;
	
	private List<DiscreteActionInterface> actionsList = new ArrayList<>();
	
	private int nbLoop;
	private int step;
	
	private Logger logger;
	private FileHandler logFile; 
	private ConsoleHandler logConsole; 

	public DiscreteActionSimulator(){
		
		// Start logger
		this.logger = Logger.getLogger("DAS");
		this.logger.setLevel(Level.ALL);
		this.logger.setUseParentHandlers(true);
		try{
			this.logFile = new FileHandler(this.getClass().getName() + ".log");
			this.logFile.setFormatter(new LogFormatter());
			this.logConsole = new ConsoleHandler();
		}catch(Exception e){
			e.printStackTrace();
		}
		this.logger.addHandler(logFile);
		this.logger.addHandler(logConsole);
		

		this.globalTime = Clock.getInstance();
		
		this.t = new Thread(this);
		this.t.setName("Discrete Action Simulator");
	}
	
	/**
	 * @param nbLoop defines the number of loop for the simulation, the simulation is infinite if nbLoop is negative or 0.
	 */
	public void setNbLoop(int nbLoop){
		if(nbLoop>0){
			this.nbLoop = nbLoop;
			this.step = 1;
		}else{ // running infinitely
			this.nbLoop = 0;
			this.step = -1;
		}
	}
	
	public void addAction(DiscreteActionInterface c){

		if(c.hasNext()) {
			// add to list of actions, next is call to the action exist at the first time
			this.actionsList.add(c.next());

			// sort the list for ordered execution 
			Collections.sort(this.actionsList);
		}
	}
	
	/**
	 * @return the laps time before the next action
	 */
	private int nextLapsTime() {
		DiscreteActionInterface currentAction = this.actionsList.get(0);
		return currentAction.getCurrentLapsTime();
	}
	/**
	 * @return laps time of the running action
	 */
	private int runAction(){
		// Run the first action
		int sleepTime = 0;


		// TODO Manage parallel execution !  
		DiscreteActionInterface currentAction = this.actionsList.get(0);
		Object o = currentAction.getObject();
		Method m = currentAction.getMethod();
		sleepTime = currentAction.getCurrentLapsTime();
		
		try {
			Thread.yield();
			if(this.globalTime!=null) {
				this.globalTime.increase(sleepTime);
			}
			m.invoke(o);
			if(this.globalTime!=null) {
				this.logger.log(Level.FINE, String.format("[DAS] run action {} on {}:{} at {} after {} time units%n", m.getName(), o.getClass().getName(), o.hashCode(), this.globalTime.getTime(), sleepTime));
			}else {
				this.logger.log(Level.FINE, String.format("[DAS] run action {} on {}:{} after {} time units%n", m.getName(), o.getClass().getName(), o.hashCode(), sleepTime));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		return sleepTime;
	}

	private void updateTimes(int runningTimeOf1stCapsul){
		
		// update time laps off all actions
		for(int i=1 ; i < this.actionsList.size(); i++){
			this.actionsList.get(i).spendTime(runningTimeOf1stCapsul);
		}

		// get new time lapse of first action
		DiscreteActionInterface a = this.actionsList.remove(0);
		if(a.hasNext()) {
			a = a.next();
			this.actionsList.add(a);
			if(this.globalTime!=null) {
				this.logger.log(Level.FINE, String.format("[DAS] reset action {} on {}:{} at {} to {} time units%n", a.getMethod().getName(), a.getObject().getClass().getName(), a.getObject().hashCode(), this.globalTime.getTime(), a.getCurrentLapsTime()));
			}else {
				this.logger.log(Level.FINE, String.format("[DAS] reset action {} on {}:{} to {} time units%n", a.getMethod().getName(), a.getObject().getClass().getName(), a.getObject().hashCode(), a.getCurrentLapsTime()));
			}
			Collections.sort(this.actionsList);
		}
	}


	public void run() {
		int count = this.nbLoop;
		boolean finished = false;

		this.logger.log(Level.FINE, String.format("LANCEMENT DU THREAD {} %n", t.getName()));

		while(running && !finished){

			if(!this.actionsList.isEmpty()){
				this.logger.log(Level.FINE, this.toString());
				this.globalTime.setNextJump(this.nextLapsTime());
				
				this.globalTime.lockWriteAccess();
				int runningTime = this.runAction();
				this.updateTimes(runningTime);
				this.globalTime.unlockWriteAccess();
				try {
					Thread.sleep(100);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//TODO add global time synchronizer for action with list of date and avoid drift 
			}else{
				this.logger.log(Level.FINE, "NOTHING TO DO%n");
				this.stop();
			}

			count -= this.step;
			if(count<=0) {
				finished = true;
			}
		}
		this.running = false;
		if(this.step>0) {
			this.logger.log(Level.FINE, String.format("DAS: {} actions done for {} turns asked.", (this.nbLoop - count), this.nbLoop));
		}else {
			this.logger.log(Level.FINE, String.format("DAS: {} actions done!", count));			
		}
	}

	public void start(){
		this.running = true;
		this.t.start();
	}

	public void stop(){
		this.logger.log(Level.FINE, String.format("STOP THREAD {} obj {}", t.getName(), this));
		this.running = false;
	}
	
	public String toString(){
		StringBuffer toS = new StringBuffer("------------------%nTestAuto :" + this.actionsList.size());
		for(DiscreteActionInterface c : this.actionsList){
			toS.append(c.toString() + "%n");
		}
		toS.append("---------------------%n");
		return toS.toString();
	}

	public boolean getRunning() {
		return this.running;
	}
}
