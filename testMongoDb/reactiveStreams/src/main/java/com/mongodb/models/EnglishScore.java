package com.mongodb.models;

import java.util.Objects;

public class EnglishScore extends Score {

    Double grammerScore;

    public EnglishScore(String type, Double score, Double grammerScore) {
        super(type, score);
        this.grammerScore = grammerScore;
    }

    public Double getGrammerScore() {
        return grammerScore;
    }

    public void setGrammerScore(Double grammerScore) {
        this.grammerScore = grammerScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishScore that = (EnglishScore) o;
        return Objects.equals(grammerScore, that.grammerScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grammerScore);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("English Score{");
        sb.append("type='").append(type).append('\'');
        sb.append("grammerScore='").append(grammerScore).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
