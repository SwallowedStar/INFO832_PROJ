package polytech.annecy.groupe_valentin.timer;

public class PeriodicTimer implements Timer {

	private int period;
	private int next;
	private RandomTimer moreOrLess = null;
	
	public PeriodicTimer(int at) {
		this.period = at;
		this.next = at;
	}
	
	public PeriodicTimer(int period, int at) {
		this.period = period;
		this.next = at;
	}
	
	public int getPeriod() {
		return this.period;
	}
	
	
	@Override
	public Integer next() {
		
		int res =  this.next;
		
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
