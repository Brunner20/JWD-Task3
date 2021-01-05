package by.tc.task03.dao;

import by.tc.task03.service.exception.ServiceException;
import by.tc.task03.entity.Node;

public interface XMLParser {

     Node parseXML() throws ServiceException;
}
