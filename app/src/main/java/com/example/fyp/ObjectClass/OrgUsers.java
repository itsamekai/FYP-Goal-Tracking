package com.example.fyp.ObjectClass;

public class OrgUsers {

    private int org_id;
    private String email_address;
    private int approved;
    private int contactNo;
    private String contact_name;
    private String address;
    private String org_name;
    private String password;

    public OrgUsers(String email_address, int contactNo, String contact_name, String address, String org_name, String password){
        this.email_address = email_address;
        this.approved = 0 ;
        this.contact_name = contact_name;
        this.contactNo = contactNo;
        this.address = address;
        this.org_name = org_name;
        this.password = password;
    }

    public OrgUsers(){
    }

    //org_id
    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    //email_address
    public String getEmail_address(){
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    //approved_id
    public int getApproved(){
        return approved;
    }

    public void setApproved(int approved){
        this.approved = approved;
    }

    //contact_name
    public String getContact_name(){
        return contact_name;
    }

    public void setContact_name(String contact_name){
        this.contact_name = contact_name;
    }

    //contactNo
    public int getContactNo(){
        return contactNo;
    }

    public void setContactNo(int contactNo){
        this.contactNo = contactNo;
    }

    //address
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    //org_name
    public String getOrg_name(){
        return org_name;
    }

    public void setOrg_name(String org_name){
        this.org_name = org_name;
    }

    //password
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return "OrgUsers{" +
                "org_id=" + org_id + '\'' +
                "email_address=" + email_address + '\'' +
                "contactNo=" + contactNo + '\'' +
                "contact_name=" + contact_name + '\'' +
                "address=" + address + '\'' +
                "org_name=" + org_name + '\'' +
                "password=" + password + '\'' +
                '}';
    }

}