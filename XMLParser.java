package by.tc.task03.dao;

import by.tc.task03.dao.exception.DaoException;
import by.tc.task03.entity.Node;

import java.io.*;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {


    private static Stack<Node> stackNodes;

    private static final String XML_DECLARATION = "(<\\?.*\\?>)"; //<?xml version="1.0" encoding="UTF-8"?>
    private static final  String COMMENT_TAG = "(<!--.*-->)"; //  <!-- Comments -->
    private static final  String END_TAG = "</.*>"; // </tag>
    private static final  String CLOSED_TAG = "<[\\s\\w]+[ \\w\\S]+/[\\s]*>"; // <tagName attribute="value"/>
    private static final  String SIMPLE_TAG = "<[^/][\\s\\w\\-_=\"']+>"; //<tagName attribute="value">
    private static final String TO_SPLIT_ATTRIBUTE ="[=\"\\/]+";

    public static Node parseXML(String path) throws DaoException {

        stackNodes = new Stack<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String xmlData = null;
            while (reader.ready()) {
                xmlData += (char) reader.read();
            }

             while(!xmlData.isEmpty())
             {
                String xmlTagLine = null;
                int bracket = xmlData.indexOf("<");
                int nextBracket = xmlData.indexOf("<", bracket + 1);
                if (bracket != -1 && nextBracket != -1) {

                    xmlTagLine = xmlData.substring(bracket, nextBracket).trim();
                    xmlData = xmlData.substring(nextBracket);
                    parseLine(xmlTagLine);
                }
                else break;
            }

        } catch (IOException e) {
            throw new DaoException(e.fillInStackTrace());
        }
        return stackNodes.pop();
    }

    private static void parseLine(String lineToParse){

        if(matcher(XML_DECLARATION,lineToParse))
            return;
        if(matcher(COMMENT_TAG,lineToParse))
            return;
        if(matcher(SIMPLE_TAG,lineToParse))
        {
            createNode(lineToParse);
        }

        else if(matcher(END_TAG,lineToParse))
        {
            Node last = stackNodes.pop();
            Node current = stackNodes.peek();
            current.addChildNodes(last);
        }
         else if(matcher(CLOSED_TAG,lineToParse))
        {
            createNode(lineToParse);
            Node last = stackNodes.pop();
            Node current = stackNodes.peek();
            current.addChildNodes(last);
        }
    }

    private static void createNode(String lineToParse) {
        Node node = new Node();
        int indexBeg = lineToParse.indexOf("<");
        int indexEnd= lineToParse.indexOf(">");
        String nodeName = lineToParse.substring(indexBeg+1,indexEnd);
        String content = lineToParse.substring(indexEnd+1);
        node.setName(nodeName);
        node.setContent(content);
        addAttributes(node);
        stackNodes.add(node);

    }

    private static void addAttributes(Node node) {

        String name = node.getName();
        if(name.contains("=")){
            String attributesLine = name.substring(name.indexOf(" ")).trim();
            String newName = name.substring(0,name.indexOf(" "));
            node.setName(newName);
            String[] attributes = attributesLine.split(TO_SPLIT_ATTRIBUTE);
            for(int i =0;i<attributes.length;i+=2)
                node.setAttributes(attributes[i],attributes[i+1]);
        }

    }

    private static boolean matcher(String patternString,String lineToMatch){

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(lineToMatch);
        return matcher.find();

    }
}
