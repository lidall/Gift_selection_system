/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.RUA.myapplication;

import android.os.Environment;

import java.io.File;
import java.util.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author Lida
 */
public class ProcessXML {
    public static List<String> ITEM_IMAGE = new ArrayList<String>();
    public static List<String> ITEM_TITLE = new ArrayList<String>();
    public static List<String> ITEM_URL = new ArrayList<String>();
    public static List<String> ITEM_BRAND = new ArrayList<String>();
    public static List<String> ITEM_PRICE = new ArrayList<String>();

    
    public static void usefulXMLProcess() throws DocumentException
    {
        SAXReader reader = new SAXReader();
        File extDir = Environment.getExternalStorageDirectory();
        String filename = "api.xml";
        File fullFilename = new File(extDir, filename);
        Document document = reader.read(fullFilename);
        
        /**
         * 节点对象的操作方法
         */
        
        //获取文档根节点
        Element root = document.getRootElement();
        //输出根标签的名字

        
        //获得指定节点下面的子节点
        Element contactElem = root.element("Items");//首先要知道自己要操作的节点。 
        List<Element> contactList = contactElem.elements();
        for (Element e:contactList){
            if(e.getName().equals("Item")){


                String Image = "Image:NULL";
                String Title = "Title:NULL";
                String URL = "URL:NULL";
                String Brand = "Brand:NULL";

                List<Element> sub = e.elements();

                for(Element s:sub) {

                    if(s.getName().equals("DetailPageURL")){
                        URL =s.getStringValue();
                    }
                    if(s.getName().equals("MediumImage")){
                        Image = s.element("URL").getStringValue();
                    }else if(s.getName().equals("LargeImage")){
                        Image = s.element("URL").getStringValue();
                    }

                }

                List<Element> ssub = e.element("ItemAttributes").elements();

                for(Element ss:ssub) {

                    if(ss.getName().equals("Title")){
                        Title = ss.getStringValue();
                    }
                    if(ss.getName().equals("Brand")){
                        Brand = ss.getStringValue();
                    }


                }

                ITEM_IMAGE.add(Image);
                ITEM_TITLE.add(Title);
                ITEM_BRAND.add(Brand);
                ITEM_URL.add(URL);
            /*    Element urlElem = e.element("MediumImage").element("URL");
                System.out.println(urlElem.getName()+" : "+urlElem.getStringValue());
                ITEM_IMAGE.add(urlElem.getStringValue());
                Element titleElem = e.element("ItemAttributes").element("Title");
                System.out.println(titleElem.getName()+" : "+titleElem.getStringValue());
                ITEM_TITLE.add(titleElem.getStringValue());
                Element detailElem = e.element("DetailPageURL");
                System.out.println(detailElem.getName()+" : "+detailElem.getStringValue());
                ITEM_URL.add(detailElem.getStringValue());
                Element brandElem = e.element("ItemAttributes").element("Brand");
                System.out.println(brandElem.getName()+" : "+brandElem.getStringValue());
                ITEM_BRAND.add(brandElem.getStringValue());
              */
                Element offerElem = e.element("OfferSummary");
                        
                    List<Element> checkList = offerElem.elements();
                        
                    for(Element el:checkList){ 
                        
                        if(el.getName().equals("LowestNewPrice")){
                            Element priceElem = el.element("FormattedPrice");
                            //System.out.println(priceElem.getName()+" : "+priceElem.getStringValue());
                            ITEM_PRICE.add(priceElem.getStringValue());
                            break;
                        }else if(el.getName().equals("LowestUsedPrice")){
                            Element priceElem = el.element("FormattedPrice");
                            //System.out.println(priceElem.getName()+" : "+priceElem.getStringValue());
                            ITEM_PRICE.add(priceElem.getStringValue());
                            break;
                        }
                        else{
                            ITEM_PRICE.add("Price:NULL");
                            break;
                        }
                    }
            }
        }  /*
        System.out.println(ITEM_IMAGE);
        System.out.println(ITEM_TITLE);
        System.out.println(ITEM_URL);
        System.out.println(ITEM_BRAND);
        System.out.println(ITEM_PRICE);
        */
    }
    

    
}
