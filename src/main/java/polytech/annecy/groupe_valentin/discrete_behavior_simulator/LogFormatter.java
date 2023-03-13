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
		StringBuffer buf = new StringBuffer();
		
		buf.append(calcDate(rec.getMillis()));
		buf.append(": ");
		buf.append(rec.getLevel());
		buf.append(System.getProperty("line.separator"));
		buf.append(formatMessage(rec));
		buf.append(System.getProperty("line.separator"));
		
		return buf.toString();
	}
	
	private String calcDate(long millisecs) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SS");
	    Date resultdate = new Date(millisecs);
	    return dateFormat.format(resultdate);
	  }

	  // this method is called just after the handler using this
	  // formatter is created
	  public String getHead(Handler h) {
		  return "";
	  }
	  
	// this method is called just after the handler using this
	  // formatter is closed
	  public String getTail(Handler h) {
	    return "";
	  }

}
