package com.sx.createxml.util;

import com.sx.createxml.pojo.CreateXMLResult;
import com.sx.createxml.pojo.XMLDataStruct.FileBasic;
import com.sx.createxml.pojo.XMLDataStruct.Print4XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author sunxu93@163.com
 * @date 19/7/8/008 16:42
 */
public class CLass2XML {
    //TODO  把模板修改成拿到printLIst,写出标准的xml文件,以print.getName命名xml,并把xml的路径返回
    public ArrayList<CreateXMLResult> createXMLByDOM(File dest, List<Print4XML> prints) {
        // 创建DocumentBuilderFactory
        Print4XML print4XML = prints.get(0);
        FileBasic fileBasic = print4XML.getFileBasic();

        String[] listFileBasic = fileBasic.getListFileBasic();

        HashMap<String, Object> hashMap = fileBasic.getHashMap();

        String s = listFileBasic[0];
        hashMap.get(s);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {

            // 创建DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 创建Document
            Document document = builder.newDocument();

            // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
            // document.setXmlStandalone(true);

            // 创建根节点
            Element bookstore = document.createElement("bookstore");

            // 创建子节点，并设置属性
            Element book = document.createElement("book");
            book.setAttribute("id", "1");

            // 为book添加子节点
            Element name = document.createElement("name");
            name.setTextContent("安徒生童话");
            book.appendChild(name);

            Element author = document.createElement("author");
            author.setTextContent("安徒生");
            book.appendChild(author);

            Element price = document.createElement("price");
            price.setTextContent("49");
            book.appendChild(price);

            // 为根节点添加子节点
            bookstore.appendChild(book);

            // 将根节点添加到Document下
            document.appendChild(bookstore);

            /*
             * 下面开始实现： 生成XML文件
             */

            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();

            // 创建Transformer对象
            Transformer tf = tff.newTransformer();

            // 设置输出数据时换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            // 使用Transformer的transform()方法将DOM树转换成XML
            tf.transform(new DOMSource(document), new StreamResult(dest));


            return null;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
//        public void createXMLByDOM(File dest) {
//            // 创建DocumentBuilderFactory
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            try {
//
//                // 创建DocumentBuilder
//                DocumentBuilder builder = factory.newDocumentBuilder();
//
//                // 创建Document
//                Document document = builder.newDocument();
//
//                // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
//                // document.setXmlStandalone(true);
//
//                // 创建根节点
//                Element bookstore = document.createElement("bookstore");
//
//                // 创建子节点，并设置属性
//                Element book = document.createElement("book");
//                book.setAttribute("id", "1");
//
//                // 为book添加子节点
//                Element name = document.createElement("name");
//                name.setTextContent("安徒生童话");
//                book.appendChild(name);
//                Element author = document.createElement("author");
//                author.setTextContent("安徒生");
//                book.appendChild(author);
//                Element price = document.createElement("price");
//                price.setTextContent("49");
//                book.appendChild(price);
//
//                // 为根节点添加子节点
//                bookstore.appendChild(book);
//
//                // 将根节点添加到Document下
//                document.appendChild(bookstore);
//
//                /*
//                 * 下面开始实现： 生成XML文件
//                 */
//
//                // 创建TransformerFactory对象
//                TransformerFactory tff = TransformerFactory.newInstance();
//
//                // 创建Transformer对象
//                Transformer tf = tff.newTransformer();
//
//                // 设置输出数据时换行
//                tf.setOutputProperty(OutputKeys.INDENT, "yes");
//
//                // 使用Transformer的transform()方法将DOM树转换成XML
//                tf.transform(new DOMSource(document), new StreamResult(dest));
//
//            } catch (ParserConfigurationException e) {
//                e.printStackTrace();
//            } catch (TransformerConfigurationException e) {
//                e.printStackTrace();
//            } catch (TransformerException e) {
//                e.printStackTrace();
//            }
//

