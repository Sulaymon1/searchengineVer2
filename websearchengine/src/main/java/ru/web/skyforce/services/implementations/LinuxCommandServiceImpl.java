package ru.web.skyforce.services.implementations;

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
public class LinuxCommandServiceImpl implements LinuxCommandService {
    @Override
    public boolean isRunningParser() {
        try {
            Process process = Runtime.getRuntime().exec("jps -lV | grep parser.jar");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return stdInput.readLine() != null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void startParser() {
        try {
            Runtime.getRuntime().exec("nohup java -jar /home/dev/projects/parser/parser.jar &");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
