package com.cs.scu.kafka.consumer;

import com.cs.scu.hive.HiveService;
import com.cs.scu.tools.Util;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import scala.util.parsing.combinator.testing.Str;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by lch on 2017/6/24.
 */
public class KafkaConsumerForHive {
    Properties properties;
    Connection con;
    Statement stmt;
    FileWriter fileWriter;
    PrintWriter printWriter;

    public KafkaConsumerForHive(Properties properties) {
        super();
        this.properties = properties;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String receive() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(properties.getProperty("topic")));
        final int minBatchSize = 20;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<ConsumerRecord<String, String>>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);

            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
               System.err.println(buffer.size() + "----->" + record);

            }
            if (buffer.size() >= minBatchSize) {
                boolean b = writeFile(buffer);//先把buffer写入文件中
                //if(b)
                   // insertIntoHive(buffer);//存入hive中
                    consumer.commitSync();
                    buffer.clear();
            }
        }
    }

    private void insertIntoHive(List<ConsumerRecord<String, String>> buffer) {
        String tableName = "test";
        try {
            con = HiveService.getConn();
            stmt = HiveService.getStmt(con);

            for (int i = 0; i < buffer.size(); i++) {
                String data = "\"" + StringEscapeUtils.escapeJava(buffer.get(i).value()) + "\"";
                stmt.execute("insert into test(id, data) values(" + buffer.get(i).offset()+ "," + data + ")");
            }
            //stmt.execute("load data inpath '/Users/lch/Desktop/file.txt' into table test");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public synchronized boolean writeFile(List<ConsumerRecord<String, String>> buffer) {

        long begin = System.currentTimeMillis();
        try {
            File file = new File("/usr/local/file.txt");

            fileWriter = new FileWriter(file,false);
            printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < buffer.size(); i++) {
                printWriter.println(buffer.get(i).value()   + "\t" + buffer.get(i).value());
            }

            printWriter.flush();

        } catch (IOException e) {
            return false;
        }finally {
            try {
                fileWriter.close();
                long end = System.currentTimeMillis();
                System.err.println("写文件一共花了 -------》》》》》》" + (end - begin) + "毫秒");
                return true;
            } catch (IOException e) {
                return false;
            }
        }

    }

}
