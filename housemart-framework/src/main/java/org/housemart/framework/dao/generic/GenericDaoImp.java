package org.housemart.framework.dao.generic;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.housemart.framework.dao.generic.PaginateObject;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class GenericDaoImp<T> extends SqlMapClientDaoSupport implements GenericDao<T>  {

	private Class<T> entity;	
	public void setEntity(Class<T> entity) {
		this.entity = entity;
	}

	public GenericDaoImp(Class<T> entity){
		this.setEntity(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> select(String statementid, Object args) {		
		List<T> result = null;
		result = getSqlMapClientTemplate().queryForList(getNamespaceStatement(statementid), args);
		return result;
	}

	@SuppressWarnings("unchecked")
	public T load(String statementid, int id) {
		UniqueIdObject obj = new UniqueIdObject(id);
		T result = (T) getSqlMapClientTemplate().queryForObject(getNamespaceStatement(statementid), obj);		
		return result;
	}
	
	public int add(String statementid, Object para) {		
		Object o = getSqlMapClientTemplate().insert(getNamespaceStatement(statementid), para);
		return Integer.parseInt(o.toString());
	}
	

	public int delete(String statementid, int id) {
		UniqueIdObject obj = new UniqueIdObject(id);
		return getSqlMapClientTemplate().update(getNamespaceStatement(statementid), obj);
	}
	
	/**
	 * 
	 * @param statementId
	 * @return
	 */
	private String getNamespaceStatement(String statementId){
		return entity.getSimpleName() + "." + statementId;
	}


	public int deletePhysical(String statementid, int id) {
		UniqueIdObject obj = new UniqueIdObject(id);
		return getSqlMapClientTemplate().delete(getNamespaceStatement(statementid), obj);
	}


	public int update(String statementid, Object para) {
		return getSqlMapClientTemplate().update(getNamespaceStatement(statementid), para);
	}


	public int count(String statementid, Object args) {
		return (Integer)getSqlMapClientTemplate().queryForObject(getNamespaceStatement(statementid), args);
	}

	@SuppressWarnings("unchecked")
	public <S> List<S> selectByType(String statementid, Object args) {
		List<S> result = null;
		result = getSqlMapClientTemplate().queryForList(getNamespaceStatement(statementid), args);
		return result;
	}

	public int delete(String statementid, Object obj) {
		return getSqlMapClientTemplate().delete(getNamespaceStatement(statementid), obj);
	}

	@Override
	public T load(String statementid, Map para) {
		T result = (T) getSqlMapClientTemplate().queryForObject(getNamespaceStatement(statementid), para);		
		return result;
	}

	@Override
	public int deletePhysical(String statementid, Object args) {
		return getSqlMapClientTemplate().delete(getNamespaceStatement(statementid), args);
	}

	@Override
	public PaginateObject paginate(String statementid, int pageNo,
			int pageSize, Object para) {
		PaginateObject obj = new PaginateObject();
		List<Object> result = null;
		
		if(para != null && para instanceof Map){
			((Map)para).put("skip", 0);
			((Map)para).put("count", Integer.MAX_VALUE);
		}
		result = getSqlMapClientTemplate().queryForList(getNamespaceStatement(statementid), para);
		int count = CollectionUtils.isEmpty(result) ? 0: result.size();
		
		if(para != null && para instanceof Map){
			((Map)para).put("skip", pageNo == 0 ? 0: pageNo * pageSize);
			((Map)para).put("count", pageSize);
		}
		result = getSqlMapClientTemplate().queryForList(getNamespaceStatement(statementid), para);
		obj.setResult(result);
		obj.setPageNo(pageNo);
		obj.setPageSize(pageSize);
		obj.setCount(count);
		
		return obj;
	}


}
