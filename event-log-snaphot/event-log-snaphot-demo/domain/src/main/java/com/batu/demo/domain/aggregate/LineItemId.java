package com.batu.demo.domain.aggregate;

import java.util.Objects;

public class LineItemId {

    private final Long val;

    public LineItemId(Long val) {
        this.val = val;
    }

    public Long getVal() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LineItemId that = (LineItemId) o;
        return Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(val);
    }
}
