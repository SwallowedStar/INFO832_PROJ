package polytech.annecy.groupe_valentin.timer;

public class PeriodicTimer extends Exception implements Timer {

	private int period;
	private int next;
	private RandomTimer moreOrLess = null;
	private final String argumentIsNegative = "argument is negative";
	
	public PeriodicTimer(int at) {
		if (at<0) throw new IllegalArgumentException(this.argumentIsNegative);
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
		if (at<0) throw new IllegalArgumentException(this.argumentIsNegative);
		this.period = at;
		this.moreOrLess = moreOrLess;
		this.next = at + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
	}
	
	public PeriodicTimer(int period, int at) {
		if (at<0 || period<0) throw new IllegalArgumentException(this.argumentIsNegative);
		this.period = period;
		this.next = at;
	}

	@Override
	public Integer next() {
		
		int res = this.next;
		
		if(this.moreOrLess != null) {
			this.next = this.period + (int)(this.moreOrLess.next() - this.moreOrLess.getMean());
		}else {
			this.next = this.period;
		}
		
		return res;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
