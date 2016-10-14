package com.therishka.fragmania.cats;

/**
 * @author Rishad Mustafaev
 */

public class Cat {

    private String name;
    private int type;
    private String parent;

    public Cat() {
    }

    public Cat(String name, int type, String parent) {
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
