package org.housemart.rpc.stubs.log;

public interface UserAccessLoggerServiceStub {

	void access(String bizTag, int userId, String URL, String text);
	
}
