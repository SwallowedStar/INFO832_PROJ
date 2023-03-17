package polytech.annecy.groupe_valentin.timer;

public class PeriodicTimer extends Exception implements Timer {

	private int period;
	private int next;
	private RandomTimer moreOrLess = null;
	private static final String ARGUMENT_IS_NEGATIVE = "argument is negative";
	
	public PeriodicTimer(int at) {
		if (at<0) throw new IllegalArgumentException(ARGUMENT_IS_NEGATIVE);
		this.period = at;
   		this.next = at;
	}

	public PeriodicTimer(int period, int at) {
		if (at<0 || period<0) throw new IllegalArgumentException(ARGUMENT_IS_NEGATIVE);
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
