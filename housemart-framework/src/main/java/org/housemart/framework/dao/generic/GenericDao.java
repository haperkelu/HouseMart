package org.housemart.framework.dao.generic;

import java.util.List;
import java.util.Map;
import org.housemart.framework.dao.generic.PaginateObject;

public interface GenericDao<T> {	
	
	List<T> select(String statementid, Object para);	
	
	int add(String statementid, Object para);
	
	T load(String statementid, int id);

	@SuppressWarnings("rawtypes")
	T load(String statementid, Map para);
	
	int delete(String statementid, int id);
	
	int delete(String statementid, Object obj);
	
	int deletePhysical(String statementid, int id);

	int deletePhysical(String statementid, Object args);
	
	int update(String statementid, Object para);
	
	int count(String statementid, Object para);
	
	<S> List<S> selectByType(String statementid, Object args);
	
	PaginateObject paginate(String statementid, int pageNo, int pageSize,
			Object para);
}


