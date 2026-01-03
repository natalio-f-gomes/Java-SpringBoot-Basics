package com.nataliogomes.chronicblog.Controlllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "hello world";
    }


    @GetMapping("/info")
    public String aboutMachine() {
        String os = System.getProperty("os.name");
        String version = System.getProperty("os.version");
        String architecture = System.getProperty("os.arch");
        String user = System.getProperty("user.name");
        String javaVersion = System.getProperty("java.version");

        return "OS: " + os + " " + version + " (" + architecture + ")\n" +
                "User: " + user + "\n" +
                "Java Version: " + javaVersion;
    }
}

