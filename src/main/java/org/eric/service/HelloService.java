package org.eric.service;

import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloService {
    public String getMsg() {
        return "hello world!!";
    }
}
