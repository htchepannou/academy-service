package io.tchepannou.academy.dto.course;

public class SearchCourseRequest {
    enum SortStrategy {
        TITLE,
        PUBLISHED_DATE
    }

    private String status;
    private String level;
    private String language;
    private SortStrategy sortStrategy = SortStrategy.PUBLISHED_DATE;
    private int limit = 100;
    private int offset = 0;

    public String getLevel() {
        return level;
    }

    public void setLevel(final String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public SortStrategy getSortStrategy() {
        return sortStrategy;
    }

    public void setSortStrategy(final SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(final int offset) {
        this.offset = offset;
    }
}
