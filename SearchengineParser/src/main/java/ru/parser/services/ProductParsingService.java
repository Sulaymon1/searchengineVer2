package ru.parser.services;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.parser.models.Data;

import java.util.ArrayList;
import java.util.List;

public class ProductParsingService {

    private List<Data> productList = new ArrayList();

    public List<Data> getProductList(){
        return productList;
    }


    public synchronized void getProduct(Document document, Long cityId, Long categoryId) {
        Data data;
        String name = null;
        String addressStr = null;
        String phoneStr = null;
        String website = null;
        String mail = null;
        try {
            Element first = document.getElementsByClass("business-card-footer").first();
            Elements href = first.getElementsByAttributeValueContaining("href", "mailto:");
            Elements contact = document.getElementsByClass("contact");
            if (contact.size()>0){
                Elements address = contact.first().getElementsByClass("address");
                Elements phone = contact.first().getElementsByClass("phone");
                if (address !=null)
                    addressStr = address.text();
                if (phone != null)
                    phoneStr = phone.text();
            }
            Elements visit_website = first.getElementsContainingOwnText("Visit Website");
            if (visit_website.size()>0){
                website = visit_website.first().attr("href" );
                if (website.length()>253)
                    website=null;
            }
            Elements elementsByClass = document.getElementsByClass("sales-info");
            if (elementsByClass.size()>0){
                name = elementsByClass.first().text();
            }
            if (href.size()>0){
                mail = href.first().attr("href").substring(7);
            }
            data = Data.builder()
                    .categoryId(categoryId)
                    .cityId(cityId)
                    .name(name)
                    .uri(document.baseUri())
                    .email(mail)
                    .website(website)
                    .address(addressStr)
                    .phone(phoneStr)
                    .build();
            if (data != null) {
                if (data.getEmail() != null  || data.getWebsite() != null) {
                    productList.add(data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
