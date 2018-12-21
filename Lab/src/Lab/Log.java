package Lab;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

import org.apache.log4j.Logger;

public class Log {
	private final static Logger logger = Logger.getLogger(Log.class.getName());


	public static Logger getlogger() {
		
		return logger;
	}
}
