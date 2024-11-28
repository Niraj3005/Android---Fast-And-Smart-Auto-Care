package com.example.onroadremotevehicleassistantapp.Model;

public class FeedbackModel {
    private int feedbackId,feedbackUserId,feedbackMechanicId,feedbackRequestId;
    private float feedbackMechanicRating;
    private String feedbackSatisfaction, feedbackSuggestion,feedbackDate;

    public FeedbackModel(int feedbackId, int feedbackUserId, int feedbackMechanicId, int feedbackRequestId, float feedbackMechanicRating, String feedbackSatisfaction, String feedbackSuggestion, String feedbackDate) {
        this.feedbackId = feedbackId;
        this.feedbackUserId = feedbackUserId;
        this.feedbackMechanicId = feedbackMechanicId;
        this.feedbackRequestId = feedbackRequestId;
        this.feedbackMechanicRating = feedbackMechanicRating;
        this.feedbackSatisfaction = feedbackSatisfaction;
        this.feedbackSuggestion = feedbackSuggestion;
        this.feedbackDate = feedbackDate;
    }

    @Override
    public String toString() {
        return "FeedbackModel{" +
                "feedbackId=" + feedbackId +
                ", feedbackUserId=" + feedbackUserId +
                ", feedbackMechanicId=" + feedbackMechanicId +
                ", feedbackRequestId=" + feedbackRequestId +
                ", feedbackMechanicRating=" + feedbackMechanicRating +
                ", feedbackSatisfaction='" + feedbackSatisfaction + '\'' +
                ", feedbackSuggestion='" + feedbackSuggestion + '\'' +
                ", feedbackDate='" + feedbackDate + '\'' +
                '}';
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getFeedbackUserId() {
        return feedbackUserId;
    }

    public void setFeedbackUserId(int feedbackUserId) {
        this.feedbackUserId = feedbackUserId;
    }

    public int getFeedbackMechanicId() {
        return feedbackMechanicId;
    }

    public void setFeedbackMechanicId(int feedbackMechanicId) {
        this.feedbackMechanicId = feedbackMechanicId;
    }

    public int getFeedbackRequestId() {
        return feedbackRequestId;
    }

    public void setFeedbackRequestId(int feedbackRequestId) {
        this.feedbackRequestId = feedbackRequestId;
    }

    public float getFeedbackMechanicRating() {
        return feedbackMechanicRating;
    }

    public void setFeedbackMechanicRating(float feedbackMechanicRating) {
        this.feedbackMechanicRating = feedbackMechanicRating;
    }

    public String getFeedbackSatisfaction() {
        return feedbackSatisfaction;
    }

    public void setFeedbackSatisfaction(String feedbackSatisfaction) {
        this.feedbackSatisfaction = feedbackSatisfaction;
    }

    public String getFeedbackSuggestion() {
        return feedbackSuggestion;
    }

    public void setFeedbackSuggestion(String feedbackSuggestion) {
        this.feedbackSuggestion = feedbackSuggestion;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
