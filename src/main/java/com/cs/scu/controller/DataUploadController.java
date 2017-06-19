package com.cs.scu.controller;

import com.cs.scu.kafka.consumer.KafkaConsumers;
import com.cs.scu.kafka.producer.KafkaProducers;
import com.cs.scu.service.DataUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lch on 2017/5/3.
 */
@Controller
public class DataUploadController {
    @Autowired
    private DataUploadService dataUploadService;

    @Resource(name = "kafkaProducers")
    KafkaProducers producers;

    @Resource(name = "kafkaConsumers")
    KafkaConsumers consumers;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestBody String json) {
        System.out.println("-----------------<>");
        String ujson = "";
        String res2 = "";
        try {
            ujson = new String(json.getBytes("ISO-8859-1"), "utf-8");
            res2 = URLDecoder.decode(ujson, "UTF-8");
            System.err.println("res2"+res2);
            producers.sendMessage(res2);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return "";
    }

    @RequestMapping(value = "/onsend", method = RequestMethod.POST)
    public ModelAndView onsend(@RequestParam("message") String msg) {
        System.out.println("--------onsend--------");
        producers.sendMessage(msg);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }
    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    public ModelAndView sendMessage() {
        System.out.println("--------sendmessage--------");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(date);

        ModelAndView mv = new ModelAndView();
        mv.addObject("time", now);
        mv.setViewName("kafka_send");
        return mv;
    }


    @RequestMapping(value = "/receive",method = RequestMethod.GET)
    public ModelAndView receive() throws  Exception {
        System.err.println("--------------> receive <----------------");
        String msg = consumers.receive();
        System.err.println("--------------> receive ----------------" + msg);
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("kafka_receive");
        return  mv;
    }

}
