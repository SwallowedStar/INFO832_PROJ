package polytech.annecy.groupe_valentin.timer;

public class PeriodicTimer extends Exception implements Timer {

	private int period;
	private int next;
	private RandomTimer moreOrLess = null;
	
	public PeriodicTimer(int at) {
		if (at<0) throw new IllegalArgumentException("argument is negative");
		this.period = at;
   		this.next = at;
	}
	
	/**
	 * @param at
	 * @param moreOrLess
	 * 
	 * use MergedTimer instead
	 */
	@Deprecated
	public PeriodicTimer(int at, RandomTimer moreOrLess) {
		if (at<0) throw new IllegalArgumentException("argument is negative");
		this.period = at;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	public PeriodicTimer(int period, int at) {
		if (at<0 || period<0) throw new IllegalArgumentException("argument is negative");
		this.period = period;
		this.next = at;
	}
	
	/**
	 * @param period
	 * @param at
	 * @param moreOrLess
	 * 
	 * use MergedTimer instead
	 */
	@Deprecated
	public PeriodicTimer(int period, int at, RandomTimer moreOrLess) {
		if (at<0 || period<0) throw new IllegalArgumentException("argument is negative");
		this.period = period;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	public int getPeriod() {
		return this.period;
	}
	
	
	@Override
	public Integer next() {
		
		int next =  this.next;
		
		if(this.moreOrLess != null) {
			this.next = this.period + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
		}else {
			this.next = this.period;
		}
		
		return next;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
