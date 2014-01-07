package org.housemart.rpc.stubs.log;

import java.util.Date;
import java.util.List;

public interface UserAccessLoggerServiceStub {

	void access(String bizTag, int userId, String URL, String text);
	List<UserAccessDTO> retrieveList(String tag, Date laterThan, String urlQuery);		
	
}
