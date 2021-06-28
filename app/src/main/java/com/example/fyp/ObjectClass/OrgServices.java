package com.example.fyp.ObjectClass;

public class OrgServices {

    private int org_has_service_id;
    private int org_id;
    private int service_id;

    public OrgServices(int org_id,int service_id ) {
        this.org_id = org_id;
        this.service_id = service_id;
    }

    //org_has_service_id
    public int getOrg_has_service_id() {
        return org_has_service_id;
    }

    public void setOrg_has_service_id(int org_has_service_id) {
        this.org_has_service_id = org_has_service_id;
    }

    //org_id
    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    //service_id
    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    @Override
    public String toString() {
        return "OrgServices{" +
                "org_has_service_id=" + org_has_service_id +
                ", org_id='" + org_id + '\'' +
                ", service_id='" + service_id + '\'' +
                '}';
    }

}
