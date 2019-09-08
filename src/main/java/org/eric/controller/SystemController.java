package org.eric.controller;

import org.eric.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SystemController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(path = {"/system/info"}, method = RequestMethod.GET)
    public String index() {
        List<String> versionList = Arrays.asList("1.0.0");

        return versionList.get(0);
    }

    @RequestMapping(path = {"/"}, method = RequestMethod.GET)
    public String getMsg() {
        return helloService.getMsg();
    }
}
