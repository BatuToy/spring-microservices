package com.batu.demo.persistence.entity;

public enum OutboxStatus {
    STARTED("Outbox Message Saved In To Persistence Store !"),
    FINISHED("Outbox Message Send To Relevant Topics Successfully !"),
    FAILED("An Error Occurred While Sending The Outbox Message To Relevant Topics !");

    private final String description;

    OutboxStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OutboxStatus finishOutbox() {
        return valueOf(OutboxStatus.class, "Finished");
    }

    public static OutboxStatus failOutbox() {
        return valueOf(OutboxStatus.class, "Failed");
    }

    public static OutboxStatus startOutbox() {
        return valueOf(OutboxStatus.class, "Started");
    }
}

