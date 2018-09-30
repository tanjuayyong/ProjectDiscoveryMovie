package org.ourkidslearningjourney.discoverymovie;

import java.io.Serializable;

public class MovieReview implements Serializable {
    private String reviewAuthor;
    private String reviewContent;
    private String reviewId;

    public MovieReview() {

    }

    public MovieReview(String reviewauthor, String reviewcontent, String reviewid) {
        this.reviewAuthor = reviewauthor;
        this.reviewContent = reviewcontent;
        this.reviewId = reviewid;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
