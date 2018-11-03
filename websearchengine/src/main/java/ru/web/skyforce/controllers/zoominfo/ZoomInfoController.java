package ru.web.skyforce.controllers.zoominfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Date 03.11.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Controller
public class ZoomInfoController {

    private Map<String, Integer> urls = new HashMap<>();
    private int count=0;

    @GetMapping("/zoominfo")
    public String zoominfo( ModelMap modelMap){
        modelMap.put("hash",urls);
        urls.forEach((url, status)->{
            modelMap.addAttribute("url", url);
            modelMap.addAttribute("status", status);
            if (status>=100)
            modelMap.addAttribute("available", true);
        });
        return "houzzZoomScrapper";
    }

    @PostMapping("/zoominfo")
    public String zoominfo(@RequestParam("url")String url) throws IOException, InterruptedException {
        if (url!= null){
            urls.put(url, 0);
            Process process = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c"," jps -lV | grep zoominfo.jar"});
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (stdInput.readLine() == null){
                Process exec = Runtime.getRuntime().exec(new String[] {"/bin/bash","-c"," nohup java -jar /home/dev/projects/parser/ihouzzscrapper/target/zoominfo.jar > /home/dev/projects/parser/zoominfo.out &"});
                    exec.waitFor();
                    stdInput = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                    String s;
                    while ((s=stdInput.readLine())!=null){
                        System.out.println(s);
                    }
            }

        }
        return "redirect:/zoominfo";
    }

    @GetMapping("/nextUrl")
    @ResponseBody
    public String getUrl(){
        if (urls.size()>count){
            return new ArrayList<>(urls.keySet()).get(count++);
        }
        return "empty";
    }

    @GetMapping("/reset")
    public String reset() throws IOException {
        urls.clear();
        count=0;
        Process process = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c"," kill `jps -lV | grep zoominfo.jar | awk '{printf \"%s\",$1}'`"});
        return "redirect:/zoominfo";
    }

    @GetMapping("/setUrlStatus")
    @ResponseBody
    public boolean setStatus(@RequestParam String url, @RequestParam Integer status){
        if (urls.containsKey(url)){
            urls.put(url,status);
            return true;
        }
        return false;
    }
}
