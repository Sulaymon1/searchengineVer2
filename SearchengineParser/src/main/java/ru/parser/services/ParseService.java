package ru.parser.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.parser.dao.DataDAO;
import ru.parser.models.Category;
import ru.parser.models.City;
import ru.parser.models.Data;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ParseService {


    public ParseService(DataDAO dataDAO) {
        this.dataDAO = dataDAO;
        this.productParsingService = new ProductParsingService();
    }

    private DataDAO dataDAO;
    private ProductParsingService productParsingService;

    public void parseByCategoryAndCity(City city, Category category)  {
        String cityName = city.getName();
        if (cityName.contains(","))
            cityName = cityName.replace(",", "%2C");
        if (cityName.contains(" "))
            cityName = cityName.replace(" ", "+");
        // example or template
        // https://www.yellowpages.com/search?search_terms=architects&geo_location_terms=New+York%2C+NY
        String categoryName = category.getName().replace(" ", "+");
        StringBuilder url = new StringBuilder();
        url.append("https://www.yellowpages.com/search?search_terms=")
                .append(categoryName)
                .append("&geo_location_terms=")
                .append(cityName);
        try {
            int pageNum = getPageNum(url.toString());
            ExecutorService executorService = Executors.newFixedThreadPool(15);
            for (int page = 1; page <= pageNum; page++) {
                try {
                    url.append("&page=").append(page);
                    Thread.sleep(2000);
                    Document document = Jsoup.connect(url.toString()).userAgent("Mozilla").get();
                    Elements n = document.getElementsByClass("n");
                    n.forEach(item -> {
                        if (item.childNodes().size() >= 2) {
                            String uri = item.getElementsByTag("a").first().attr("href");
                            executorService.submit(parse(category, city, uri));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Thread.sleep(10000);
                }
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            List<Data> productList = productParsingService.getProductList();
            if (productList.size() > 0)
                dataDAO.save(productList);
            productParsingService.getProductList().clear();

        } catch (Throwable e) {
           e.printStackTrace();
        }
    }


    private int getPageNum(String url) {
        int pageNum = 1;
        int productNum;
        try {
            Document document = Jsoup.connect(url).get();
            Elements pagination = document.getElementsByClass("pagination");
            if (!pagination.isEmpty()){
                productNum = Integer.valueOf(pagination.first().child(0).ownText());
                int itemPerPage = 30;
                if (productNum > itemPerPage) {
                    pageNum = (productNum / itemPerPage) + (productNum % itemPerPage == 1 ? 1 : 0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pageNum;
    }

    private Runnable parse(Category category, City city, String uri){
       return  () -> {
           String url = "https://www.yellowpages.com" + uri;
           try {
                Document document1 = Jsoup.connect(url).get();
                productParsingService.getProduct(document1, city.getId(), category.getId());
            } catch (IOException e) {
               e.printStackTrace();
           }
        };
    }

}
