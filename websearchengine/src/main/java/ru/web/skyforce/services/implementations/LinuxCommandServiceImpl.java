package ru.web.skyforce.services.implementations;

import org.springframework.stereotype.Service;
import ru.web.skyforce.services.interfaces.LinuxCommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Date 23.09.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Service
public class LinuxCommandServiceImpl implements LinuxCommandService {

    @Override
    public boolean isRunningParser() {
        try {
            Process process = Runtime.getRuntime().exec(new String[] { "/bin/bash", "-c"," jps -lV | grep parser.jar"});
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));


            return !(stdInput.readLine() == null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void startParser() {
        try {
            Process exec = Runtime.getRuntime().exec(new String[] {"/bin/bash","-c"," nohup java -jar /home/dev/projects/parser/searchengineVer2/SearchengineParser/target/parser.jar > /home/dev/projects/parser/parser.out"});
            try {
                exec.waitFor();
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                BufferedReader stdError = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
                String s;
                while ((s=stdInput.readLine())!=null){
                    System.out.println(s);
                }
                System.out.println("error");
                while ((s=stdError.readLine())!=null){
                    System.out.println(s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
