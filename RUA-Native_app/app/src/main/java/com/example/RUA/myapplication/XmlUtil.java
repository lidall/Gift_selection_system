/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.RUA.myapplication;

import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
 
public class XmlUtil {
 
    public static String xmlChangeString(String fileName){
        try {
            SAXReader saxReader = new SAXReader();//新建一个解析类
            Document tempDocument = saxReader.read(XmlUtil.class.getClassLoader().getResourceAsStream(fileName));//读入一个文件
            return tempDocument.asXML();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
    //将字符串string类型转换成xml文件
    public static void strChangeXML(String str) throws IOException {
           SAXReader saxReader = new SAXReader();
           Document document;
            File extDir = Environment.getExternalStorageDirectory();
            String filename = "api.xml";
            File fullFilename = new File(extDir, filename);
           try {
               if(fullFilename.exists()){
                   fullFilename.delete();
               }
               fullFilename.createNewFile();
               fullFilename.setWritable(Boolean.TRUE);
            document = saxReader.read(new ByteArrayInputStream(str.getBytes("UTF-8")));
            OutputFormat format = OutputFormat.createPrettyPrint();
            /** 将document中的内容写入文件中 */
            XMLWriter writer = new XMLWriter(new FileWriter(fullFilename),format);
            writer.write(document);
            writer.close();
           } catch (DocumentException e) {
            e.printStackTrace();
           }
 
           
   }
}
