package by.tc.task03.dao.impl;

import by.tc.task03.dao.XMLParser;
import by.tc.task03.entity.Node;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParserImpl implements XMLParser {


    private LinkedList<Node> stackNodes;
    private String xmlData;
    private static final String XML_DECLARATION = "(<\\?.*\\?>)"; //<?xml version="1.0" encoding="UTF-8"?>
    private static final String COMMENT_TAG = "(<!--.*-->)"; //  <!-- Comments -->
    private static final String END_TAG = "</.*>"; // </tag>
    private static final String CLOSED_TAG = "<[\\s\\w]+[ \\w\\S]+/[\\s]*>"; // <tagName attribute="value"/>
    private static final String SIMPLE_TAG = "<[^/][\\s\\w\\-_=\"']+>"; //<tagName attribute="value">
    private static final String TO_SPLIT_ATTRIBUTE ="[=\"\\/]+";

    public XMLParserImpl(String xmlData){
        this.xmlData=xmlData;
        stackNodes = new LinkedList<>();
    }

    @Override
    public Node parseXML()  {


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

        return stackNodes.pop();
    }

    private void parseLine(String lineToParse){

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
            Node last = stackNodes.removeLast();
            Node current = stackNodes.peekLast();
            current.addChildNodes(last);
        }
         else if(matcher(CLOSED_TAG,lineToParse))
        {
            createNode(lineToParse);
            Node last = stackNodes.removeLast();
            Node current = stackNodes.peekLast();
            current.addChildNodes(last);
        }
    }

    private void createNode(String lineToParse) {
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

    private void addAttributes(Node node) {

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

    private boolean matcher(String patternString,String lineToMatch){

        return Pattern.compile(patternString).matcher(lineToMatch).find();

    }
}
