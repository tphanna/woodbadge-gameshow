package org.hanna.woodbadge.gameshow.model;

/**
 * Created by timhanna on 5/16/16.
 */
public class Question implements Comparable<Question>{
    private int round;
    private Category category;
    private int points;
    private String question;
    private boolean answered = false;

    public Question(int round, Category category, int points, String question) {
        this.round = round;
        this.category = category;
        this.points = points;
        this.question = question;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    @Override
    public int compareTo(Question o) {
       return new Integer(this.points).compareTo(new Integer(o.points));
    }
}
