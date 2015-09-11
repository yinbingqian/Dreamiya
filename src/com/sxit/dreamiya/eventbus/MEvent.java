package com.sxit.dreamiya.eventbus;

public class MEvent {
    private int event_code;
    private Object obj;

    public int getEvent_code() {
        return event_code;
    }

    public void setEvent_code(int event_code) {
        this.event_code = event_code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
