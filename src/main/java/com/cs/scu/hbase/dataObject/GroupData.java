package com.cs.scu.hbase.dataObject;

import com.cs.scu.hbase.HbaseColumn;
import com.cs.scu.hbase.HbaseTable;

@HbaseTable(name = "GroupData", rowKey = "time_id")
public class GroupData implements java.io.Serializable{
    private String time_id;
    @HbaseColumn(family = "probeInfo", qualifier = "id")
    private String id;

    @HbaseColumn(family = "probeInfo", qualifier = "mmac")
    private String mmac;

    @HbaseColumn(family = "probeInfo", qualifier = "rate")
    private String rate;

    @HbaseColumn(family = "probeInfo", qualifier = "wssid")
    private String wssid;
    @HbaseColumn(family = "probeInfo", qualifier = "wmac")
    private String wmac;
    @HbaseColumn(family = "probeInfo", qualifier = "time")
    private String time;

    //下面三个字段如果没有需要补全
    @HbaseColumn(family = "Address", qualifier = "lat")
    private String lat;
    @HbaseColumn(family = "Address", qualifier = "lon")
    private String lon;
    @HbaseColumn(family = "Address", qualifier = "addr")
    private String addr;

    @HbaseColumn(family = "data", qualifier = "data")
    private String data;

    public GroupData(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMmac() {
        return mmac;
    }

    public void setMmac(String mmac) {
        this.mmac = mmac;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getWssid() {
        return wssid;
    }

    public void setWssid(String wssid) {
        this.wssid = wssid;
    }

    public String getWmac() {
        return wmac;
    }

    public void setWmac(String wmac) {
        this.wmac = wmac;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }
}
