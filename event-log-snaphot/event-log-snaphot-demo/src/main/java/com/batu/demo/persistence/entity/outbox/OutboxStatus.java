package com.batu.demo.persistence.entity.outbox;

public enum OutboxStatus {
    STARTED("Outbox Message Saved In To Persistence Store !"),
    COMPLETED("Outbox Message Send To Relevant Topics Successfully !"),
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
        return valueOf(OutboxStatus.class, "Completed");
    }

    public static OutboxStatus startOutbox() {
        return valueOf(OutboxStatus.class, "Started");
    }
}

