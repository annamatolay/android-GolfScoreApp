package com.zionroad.app.golfscoreapp;

public class Hole {
    private String mLabel;
    private Integer mStrokeCount;

    public Hole(String label, Integer strokeCount) {
        mLabel = label;
        mStrokeCount = strokeCount;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public Integer getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(Integer strokeCount) {
        mStrokeCount = strokeCount;
    }
}
