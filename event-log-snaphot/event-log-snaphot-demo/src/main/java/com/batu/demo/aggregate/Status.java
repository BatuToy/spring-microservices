package com.batu.demo.aggregate;

public enum Status {
    CREATED {
        @Override
        public Status next() {
            return PAID;
        }
    },
    PAID {
        @Override
        public Status next() {
            return DELIVERED;
        }
    },
    DELIVERED {
        @Override
        public Status next() {
            return DELIVERED;
        }
    };

    public static Status startProcess() {
        return CREATED;
    }

    public abstract Status next();
}
