package com.mongodb.models;

import java.util.Objects;

public class MathsScore extends Score{

    Double practicalScore;

    public MathsScore(String type, Double score, Double practicalScore) {
        super(type, score);
        this.practicalScore = practicalScore;
    }


    public Double getPracticalScore() {
        return practicalScore;
    }

    public void setPracticalScore(Double practicalScore) {
        this.practicalScore = practicalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathsScore that = (MathsScore) o;
        return Objects.equals(practicalScore, that.practicalScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(practicalScore);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("English Score{");
        sb.append("type='").append(type).append('\'');
        sb.append("practicalScore='").append(practicalScore).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
