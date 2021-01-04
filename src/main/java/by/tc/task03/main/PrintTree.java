package by.tc.task03.main;

import by.tc.task03.entity.Attribute;
import by.tc.task03.entity.Node;

import java.util.List;

public class PrintTree {

    private static StringBuilder indent = new StringBuilder();

    public PrintTree(){}

    public void print(Node node ){

        if(!node.getAttributes().isEmpty())
        {
            String attributes = getAttributeLine(node);
            System.out.println(indent+node.getName()+"("+attributes+")");
            printChild(node);
            printContent(node);
        }
        else {
            System.out.println(indent+node.getName());
            printChild(node);
            printContent(node);
        }
    }

    private String getAttributeLine(Node node) {
        List<Attribute> attrList = node.getAttributes();
        String attributesLine="";
        for(Attribute attr : attrList)
        {
            attributesLine+=" ";
            attributesLine+=attr.getAttributeString();

        }
        return attributesLine;
    }
    private  void printContent(Node node) {
        if (!node.getContent().isEmpty()) {
            System.out.println(String.format("%s- %s",indent, node.getContent()));
        }
    }

    private void printChild(Node node){
        if(!node.getChildNodes().isEmpty()){
            for(Node child: node.getChildNodes()){
                indent.append("   ");
                print(child);
                indent.delete(indent.length() - 3, indent.length());
            }
        }
    }

}
