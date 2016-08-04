package org.hanna.woodbadge.gameshow.model;

/**
 * Created by timhanna on 6/20/16.
 */
public class QuestionKey {

    private int round;
    private Category category;
    private int points;

    public QuestionKey(int round, Category category, int points) {
        this.round = round;
        this.category = category;
        this.points = points;
    }

    public int getRound() {
        return round;
    }

    public Category getCategory() {
        return category;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionKey that = (QuestionKey) o;

        if (round != that.round) return false;
        if (points != that.points) return false;
        return category == that.category;

    }

    @Override
    public int hashCode() {
        int result = round;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + points;
        return result;
    }
}
