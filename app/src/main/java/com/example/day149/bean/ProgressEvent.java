package com.example.day149.bean;

import java.io.File;

public class ProgressEvent {
    private int type;
    private int progress;
    private int max;
    private int txt;

    public ProgressEvent(int type, int progress, int max, int txt) {
        this.type = type;
        this.progress = progress;
        this.max = max;
        this.txt = txt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTxt() {
        return txt;
    }

    public void setTxt(int txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "ProgressEvent{" +
                "type=" + type +
                ", progress=" + progress +
                ", max=" + max +
                ", txt=" + txt +
                '}';
    }
}
