package com.hogwarts.po.contact;

import java.util.List;

public class Department {

    private String name;

    private List<Member> members;

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
