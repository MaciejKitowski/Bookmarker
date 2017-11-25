package mvc.dao;

import java.util.logging.Logger;

public abstract class DAOFactory {
	private Logger log = Logger.getLogger(DAOFactory.class.getName());

	public static DAOFactory get(int database) {
		return null;
	}
}
