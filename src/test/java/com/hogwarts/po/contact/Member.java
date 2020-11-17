package com.hogwarts.po.contact;

public class Member {

    private String name;

    private String account;

    private String phone;

    public Member() {
    }

    public Member(String name, String account, String phone) {
        this.name = name;
        this.account = account;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
