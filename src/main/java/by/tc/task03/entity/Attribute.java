package by.tc.task03.entity;


import java.util.Objects;

public class Attribute {

    private String key;
    private String value;


    public Attribute(){
    }

    public Attribute(String key,String value){
        this.key =key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttributeString(){
        return key+"=\""+value+"\"";
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return key.equals(attribute.key) &&
                value.equals(attribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
