package org.housemart.framework.search;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HouseMartWriter {
  
  private static final Logger logger = LoggerFactory.getLogger(HouseMartWriter.class);
  private static final String FILE_WRITE_DIRECTORY_ROOT = "/data/store/housemart/index/";
  private static final Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
  private static final Map<String,IndexWriter> map = new HashMap<String,IndexWriter>();
  
  public HouseMartWriter() {}
  
  /**
   * 
   * @param bizCode
   * @return
   * @throws Exception
   */
  public static IndexWriter getBizIndexWrieter(String bizCode) throws Exception {
    
    if (StringUtils.isEmpty(bizCode)) {
      return null;
    }
    IndexWriter result = null;
    if ((result = map.get(bizCode)) != null) {
      return result;
    }
    Directory directory = FSDirectory.open(new File(FILE_WRITE_DIRECTORY_ROOT + bizCode));
    IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_36, analyzer));
    map.put(bizCode, writer);
    
    return writer;
    
  }
  
  public static void unlockBizIndexWriter(String bizCode) throws IOException {
    if (StringUtils.isEmpty(bizCode)) {
      return;
    }
    
    Directory directory = FSDirectory.open(new File(FILE_WRITE_DIRECTORY_ROOT + bizCode));
    if (IndexWriter.isLocked(directory)) {
      IndexWriter.unlock(directory);
    }
  }
  
  /**
   * 
   * @param writer
   * @param documents
   * @param keyFieldName
   */
  public static void addOrUpdateDocuments(IndexWriter writer, Document[] documents, String keyFieldName) {
    if (writer == null || documents == null || documents.length == 0) {
      return;
    }
    for (Document doc : documents) {
      if (doc != null) {
        Field keyField = (Field) doc.getFieldable(keyFieldName);
        String keyValue = null;
        if (keyField != null) {
          keyValue = keyField.stringValue();
        }
        if (keyValue != null) {
          try {
            writer.updateDocument(new Term(keyFieldName, keyValue), doc);
          } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("HouseMartWriter::addOrUpdateDocuments", e);
          }
        }
      }
    }
    try {
      writer.commit();
    } catch (Exception e) {
      logger.error("HouseMartWriter::addOrUpdateDocuments", e);
    }
  }
  
  /**
   * 
   * @param writer
   * @param document
   * @param keyFieldName
   */
  public static void addOrUpdateDocument(IndexWriter writer, Document document, String keyFieldName) {
    Document[] documents = {document};
    addOrUpdateDocuments(writer, documents, keyFieldName);
  }
  
  /**
   * 
   * @param writer
   * @param query
   * @throws Exception
   */
  public static void deleteDocuments(IndexWriter writer, Query query) throws Exception {
    writer.deleteDocuments(query);
    try {
      writer.commit();
    } catch (Exception e) {
      logger.error("HouseMartWriter::deleteDocuments", e);
    }
  }
  
  /**
   * Delete all documents
   * 
   * @param writer
   */
  public static void deleteAllDocuments(IndexWriter writer) {
    try {
      writer.deleteAll();
    } catch (IOException e) {
      logger.error("HouseMartWriter::deleteAllDocuments", e);
    }
  }
  
  @SuppressWarnings("deprecation")
  public static void optimize(String bizCode) throws Exception {
    IndexWriter writer = map.get(bizCode);
    if (writer != null) {
      writer.optimize();
    }
  }
  
  public static void closeWriter(String bizCode) throws Exception {
    IndexWriter writer = map.get(bizCode);
    if (writer != null) {
      writer.close();
      map.remove(bizCode);
    }
  }
  
  public static String getIndexDirOf(String bizCode) {
    return FILE_WRITE_DIRECTORY_ROOT + bizCode;
  }
}
