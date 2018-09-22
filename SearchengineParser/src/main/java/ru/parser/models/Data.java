package ru.parser.models;



/**
 * Created by Sulaymon on 12.03.2018.
 */

public class Data {

    private Long id;


    private String name;
    private String uri;
    private String email;
    private String website;
    private String phone;
    private String address;
    private Long categoryId;
    private Long cityId;

    private Data(){

    }

    public static Builder builder(){
        return new Data().new Builder();
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uri='" + uri + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", categoryId=" + categoryId +
                ", cityId=" + cityId +
                '}';
    }

    public class Builder{

        private Builder(){

        }

        public Builder id(Long id){
            Data.this.id = id;
            return this;
        }



        public Builder name(String name){
            Data.this.name = name;
            return this;
        }

        public Builder uri(String uri){
            Data.this.uri = uri;
            return this;
        }

        public Builder email(String email){
            Data.this.email = email;
            return this;
        }

        public Builder website(String website){
            Data.this.website = website;
            return this;
        }

        public Builder phone(String phone){
            Data.this.phone = phone;
            return this;
        }

        public Builder address(String address){
            Data.this.address = address;
            return this;
        }

        public Builder categoryId(Long id){
            Data.this.categoryId = id;
            return this;
        }

        public Builder cityId(Long id){
            Data.this.cityId = id;
            return this;
        }

        public Data build(){
            return Data.this;
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
