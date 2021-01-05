package by.tc.task03.service.impl;

import by.tc.task03.dao.XMLParser;
import by.tc.task03.dao.XMLReader;
import by.tc.task03.dao.impl.XMLParserImpl;
import by.tc.task03.service.exception.ServiceException;
import by.tc.task03.entity.Node;
import by.tc.task03.service.NodeBuilder;

import java.io.IOException;


public class NodeBuilderImpl implements NodeBuilder {

    public Node parseXML(String path) throws ServiceException {

        XMLParser parser;
        try {
            parser = new XMLParserImpl(XMLReader.readXML(path));
        } catch (IOException e) {
            throw  new ServiceException(e.fillInStackTrace());
        }
        return  parser.parseXML();
    }
}
