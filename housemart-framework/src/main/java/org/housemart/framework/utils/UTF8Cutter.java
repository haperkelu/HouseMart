/**
 * Created on 2013-7-30
 * 
 */
package org.housemart.framework.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UTF8Cutter {
  
  public static String cut(String text, int maxBytes) {
    StringBuilder ret = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      if ((maxBytes -= text.substring(i, i + 1).getBytes().length) < 0) {
        break;
      }
      ret.append(text.charAt(i));
    }
    return ret.toString();
  }
  
  
  public static void main(String[] args) throws IOException {
    String sample = "2006年3月27日 - 调用String的substring函数截取中文字符时，它把一个汉字当作一个字符来截取了，如下例： .... java中的String是unicode类型的的 汉字占用一个字节 和c中的char[]是不一样的可以考虑byte[]类型进行处理 不过代码操作就不如String ...How to substring by bytes | Oracle Forumhttps://forums.oracle.com/thread/2064131‎2008年9月7日 - I need to save strings in different langauges in the DB, up to 24 bytes. How can i substring by bytes in java? for example: Èçðàèëü - is 14 bytes, ...";
    
    String cutString = cut(sample, 100);
    System.out.println(cutString);
    
    File file = new File("c:/test.txt");
    FileWriter fw = new FileWriter(file);
    fw.write(cutString);
    fw.close();
    
    System.out.println("file size : " + file.length());
  }
}
