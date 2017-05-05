package com.cs.scu.controller;

import com.cs.scu.service.DataUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by lch on 2017/5/3.
 */
@Controller
public class DataUploadController {
    @Autowired
    private DataUploadService dataUploadService;
    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public Object upload(@RequestBody String json) {
        String ujson = "";
        String res = "";
        try {
            ujson = new String(json.getBytes("ISO-8859-1"),"utf-8");
            res = URLDecoder.decode(ujson,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            System.out.print(res);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
