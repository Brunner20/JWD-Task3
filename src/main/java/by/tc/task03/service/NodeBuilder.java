package by.tc.task03.service;

import by.tc.task03.service.exception.ServiceException;
import by.tc.task03.entity.Node;


public interface NodeBuilder {

    Node parseXML(String path) throws ServiceException;
}
