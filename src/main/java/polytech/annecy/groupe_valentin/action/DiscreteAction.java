package polytech.annecy.groupe_valentin.action;

import polytech.annecy.groupe_valentin.timer.Timer;

import java.lang.reflect.Method;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author Tiphaine Bulou (2016)
 * @author Flavien Vernier
 *
 */

// TODO must inherit from Action
public class DiscreteAction implements DiscreteActionInterface {
	private Object object;
	private Method method;
	private Timer timmer;				// timer provides new lapsTime
	private Integer lapsTime; 			// waiting time (null if never used)
	
	private Logger logger;

	// Constructor
	private DiscreteAction() {
		// Start logger
			this.logger = Logger.getLogger("DAS");
			this.logger.setLevel(Level.ALL);
			this.logger.setUseParentHandlers(true);
	}
	
	public DiscreteAction(Object o, String m, Timer timmer){
		this();
		this.object = o;
		try{	
			this.method = o.getClass().getDeclaredMethod(m);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.timmer = timmer;
	}
	
	// ATTRIBUTION

	public void spendTime(int t) {
		Integer old = this.lapsTime;
		if(this.lapsTime != null) {
			this.lapsTime -= t;
		}
		this.logger.log(Level.FINE, String.format("[DA] operate spendTime on  %s:%d: old time %d new time %d", this.getObject().getClass().getName(), this.getObject().hashCode(), old ,this.getCurrentLapsTime()));
	}

	// RECUPERATION

	public Method getMethod(){
		return method;
	}
	public Integer getCurrentLapsTime(){
		return lapsTime;
	}
	public Object getObject(){
		return object;
	}



	// COMPARAISON
	public int compareTo(DiscreteActionInterface c) {
		if (this.lapsTime == null) { // no lapstime is equivalent to infinity 
			return 1;
		}
		if (c.getCurrentLapsTime() == null) {// no lapstime is equivalent to infinity 
			return -1;
		}
    	if(this.lapsTime > c.getCurrentLapsTime()){
    		return 1;
    	}
    	if(this.lapsTime < c.getCurrentLapsTime()){
    		return -1;
    	}
		if(this.lapsTime.equals(c.getCurrentLapsTime())){
			return 0;
		}
		return 0;
	}

	public String toString(){
		return "Object : " + this.object.getClass().getName() + "\n Method : " + this.method.getName()+"\n Stat. : "+ this.timmer + "\n delay: " + this.lapsTime;

	}

	public DiscreteActionInterface next() {
		Integer old = this.lapsTime;
		this.lapsTime = this.timmer.next();
		this.logger.log(Level.FINE, String.format("[DA] operate next on  %s:%d: old time %d new time %d", this.getObject().getClass().getName(),  this.getObject().hashCode(), old, this.getCurrentLapsTime()));
		return this;
	}

	public boolean hasNext() {
		return this.timmer != null && this.timmer.hasNext();
	}
}
