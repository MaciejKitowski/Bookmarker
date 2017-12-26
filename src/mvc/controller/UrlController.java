package mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.dao.model.IUrlDAO;

public final class UrlController {
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	
	private IUrlDAO urlDao = null;
	
	public UrlController() {
		log.info("Initialize url controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		urlDao = DAOFactory.get(DAOFactory.SQLITE).getUrl();
	}
}
