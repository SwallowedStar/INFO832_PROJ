package polytech.annecy.groupe_valentin.timer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.List;

public class DateTimer  implements Timer {
	
	List<Integer> lapsTimes;
	Iterator<Integer> it;
	
	public DateTimer(SortedSet<Integer> dates) {
		this.lapsTimes = new ArrayList<>();
		Integer last;
		Integer current=0;
		
		Iterator<Integer> itr = dates.iterator();
		while (itr.hasNext()) {
			last = current;
			current = itr.next();
			this.lapsTimes.add(current-last);
		}
		this.it = this.lapsTimes.iterator();
	}
	
	public DateTimer(List<Integer> lapsTimes) {
		this.lapsTimes = new ArrayList<>(lapsTimes);
		this.it = this.lapsTimes.iterator();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Integer next() {
		return it.next();
	}

}
