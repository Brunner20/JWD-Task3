package by.tc.task03.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private String name;
    private List<Attribute> attributes;
    private List<Node> childNodes;
    private String content;

    public Node(){
        attributes =new ArrayList<>();
        childNodes =new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void setAttributes(String key,String value) {
      attributes.add(new Attribute(key,value));
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public void addChildNodes(Node childNode){
        childNodes.add(childNode);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name) &&
                attributes.equals(node.attributes) &&
                childNodes.equals(node.childNodes) &&
                content.equals(node.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes, childNodes, content);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", childNodes=" + childNodes +
                ", content='" + content + '\'' +
                '}';
    }
}
