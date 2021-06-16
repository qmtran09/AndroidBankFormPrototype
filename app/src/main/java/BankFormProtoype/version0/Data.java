package BankFormProtoype.version0;


import java.time.LocalTime;
import java.util.List;

import static java.lang.System.currentTimeMillis;

public class Data {
    public List<String> event;
    public List<String> time;
    public List<String> metadata;
    public List<String> userId;

    public Data(){

    }

    public Data(List<String> event,List<String> metadata, List <String> time, List<String> userId){
        this.event = event;
        this.metadata = metadata;
        this.time = time;
        this.userId = userId;


    }

}
