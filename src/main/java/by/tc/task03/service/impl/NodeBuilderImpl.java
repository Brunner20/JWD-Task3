package by.tc.task03.service.impl;

import by.tc.task03.dao.XMLParser;
import by.tc.task03.dao.exception.DaoException;
import by.tc.task03.entity.Node;
import by.tc.task03.service.NodeBuilder;


public class NodeBuilderImpl implements NodeBuilder {

    public Node parseXML(String path) throws DaoException {
        return  XMLParser.parseXML(path);
    }
}
