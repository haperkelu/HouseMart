package org.housemart.framework.search;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class HouseMartIndexer {

	private static final String FILE_WRITE_DIRECTORY_ROOT = "/data/store/housemart/index/";
	private static final Map<String, IndexSearcher> map = new HashMap<String, IndexSearcher>();
	private static final Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);

	public static IndexSearcher getBizIndexSearcher(String bizCode) throws Exception {

		if (StringUtils.isEmpty(bizCode)) {
			return null;
		}
		IndexSearcher result = null;
		if ((result = map.get(bizCode)) != null) {
			return result;
		}

		Directory directory = FSDirectory.open(new File(FILE_WRITE_DIRECTORY_ROOT + bizCode));
		IndexReader reader = IndexReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		map.put(bizCode, searcher);
		return searcher;

	}

	/**
	 * 
	 * @param bizCode
	 * @param fieldName
	 * @param filedValue
	 * @param maxSize
	 * @return
	 * @throws Exception
	 */
	public static ScoreDoc[] search(String bizCode, String fieldName, String filedValue, int maxSize) throws Exception {

		IndexSearcher searcher = getBizIndexSearcher(bizCode);
		QueryParser parser = new QueryParser(Version.LUCENE_36, fieldName, analyzer);
		Query query = parser.parse(filedValue);
		TopDocs results = searcher.search(query, null, maxSize);
		return results.scoreDocs;
	}

	/**
	 * 
	 * @param bizCode
	 * @param query
	 * @param maxSize
	 * @param sort
	 *            (Not-None)
	 * @return
	 * @throws Exception
	 */
	public static ScoreDoc[] search(String bizCode, Query query, int maxSize, Sort sort) throws Exception {
		IndexSearcher searcher = getBizIndexSearcher(bizCode);
		TopDocs results = searcher.search(query, null, maxSize, sort);
		return results.scoreDocs;
	}

	/**
	 * 
	 * @param bizCode
	 * @param query
	 * @param maxSize
	 * @return
	 * @throws Exception
	 */
	public static ScoreDoc[] search(String bizCode, Query query, int maxSize) throws Exception {
		IndexSearcher searcher = getBizIndexSearcher(bizCode);
		TopDocs results = searcher.search(query, maxSize);
		return results.scoreDocs;
	}

	/**
	 * 
	 */
	public static void resetIndexerMap(String bizCode) {
		if (StringUtils.isNotBlank(bizCode))
			map.remove(bizCode);
		else
			map.clear();
	}
}
