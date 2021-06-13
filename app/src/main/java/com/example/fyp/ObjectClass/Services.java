package com.example.fyp.ObjectClass;

public class Services {

    private int service_id;
    private String service_name;
    private String service_desc;

    public Services(String service_name,String service_desc ) {
        this.service_name = service_name;
        this.service_desc = service_desc;
    }

    //service_id
    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    //service_name
    public String getService_name(){
        return service_name;
    }

    public void setService_name(String service_name){
        this.service_name = service_name;
    }

    //service_desc
    public String getService_desc(){
        return service_desc;
    }

    public void setService_desc(String service_desc){
        this.service_desc = service_desc;
    }

    @Override
    public String toString() {
        return "Services{" +
                "service_id=" + service_id +
                ", service_name='" + service_name + '\'' +
                ", service_desc='" + service_desc + '\'' +
                '}';
    }
}
