package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author Flavien Vernier
 *
 */
public class LogFormatter  extends Formatter {

	public String format(LogRecord rec) {
		return calcDate(rec.getMillis()) +
				": " +
				rec.getLevel() +
				System.getProperty("line.separator") +
				formatMessage(rec) +
				System.getProperty("line.separator");
	}

	private String calcDate(long millisecs) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SS");
		Date resultdate = new Date(millisecs);
		return dateFormat.format(resultdate);
	}

	// this method is called just after the handler using this
	// formatter is created
	@Override
	public String getHead(Handler h) {
		// TODO: Finish this function
		return "";
	}


	// this method is called just after the handler using this
	// formatter is closed
	@Override
	public String getTail(Handler h) {
		// TODO: Finish this function
		return "";
	}
}
