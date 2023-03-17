package polytech.annecy.groupe_valentin.action;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.TreeSet;

import polytech.annecy.groupe_valentin.timer.Timer;

/**
 * @author flver
 *
 */
public class DiscreteActionDependent implements DiscreteActionInterface {
	
	protected DiscreteActionInterface baseAction;
	protected TreeSet<DiscreteActionInterface> depedentActions;
	private Iterator<DiscreteActionInterface> it;
	protected DiscreteActionInterface currentAction;

	private Boolean started = false;
	
	/**
	 * Construct a series of dependent actions, first action (method) called is baseMethodName, then method nextMethod() is called to select the next action. 
	 * 
	 * @param o
	 * @param baseMethodName
	 * @param timerBase
	 */	
	public DiscreteActionDependent(Object o, String baseMethodName, Timer timerBase){
		this.baseAction = new DiscreteAction(o, baseMethodName, timerBase);
		this.depedentActions = new TreeSet<>();
		this.it = this.depedentActions.iterator();
		this.currentAction = this.baseAction;
	}
	
	public void addDependence(Object o, String dependentMethodName, Timer timerDependence) {
		this.depedentActions.add(new DiscreteAction(o, dependentMethodName, timerDependence));
	}

	private void reInit() {
		for (DiscreteActionInterface depedentAction : this.depedentActions) {
			//TODO finish this method
		}
	}
	
	private void nextMethod(){
		if (this.currentAction == this.baseAction && Boolean.TRUE.equals(this.started)){
			this.it = this.depedentActions.iterator();
			this.currentAction = this.it.next();
		}else if(this.currentAction == this.depedentActions.last() && this.currentAction.hasNext()){
			this.currentAction = this.baseAction;
			this.reInit();
		}else {
			this.it = this.depedentActions.iterator();
			this.currentAction.next();
		}
	}
	
	public void spendTime(int t) {
		this.currentAction.spendTime(t);
		for (DiscreteActionInterface element : this.depedentActions) {
			element.spendTime(t);
		}
	}

	public void updateTimeLaps() {
		// time laps is updated at the re-initialization
		this.nextMethod();	
	}

	public Method getMethod() {
		return this.currentAction.getMethod();
	}

	public Integer getCurrentLapsTime() {
		return this.currentAction.getCurrentLapsTime();
	}

	public Object getObject() {
		return this.currentAction.getObject();
	}

	public int compareTo(DiscreteActionInterface c) {
		return this.currentAction.compareTo(c);
	}

	public Boolean isEmpty() {
		return !this.hasNext();
	}

	public DiscreteActionInterface next() {
		this.nextMethod();
		if(Boolean.FALSE.equals(this.started)){
			for (DiscreteActionInterface element : this.depedentActions) {
				element.next();
			}
		}
		this.started = true;
		return this;
	}

	public boolean hasNext() {
		boolean result = this.baseAction.hasNext();
		for (DiscreteActionInterface element : this.depedentActions) {
			result = result || element.hasNext();
		}
		return result;
	}
}
