package com.checkout.events;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public final class RetrieveEventsRequest {

    private String paymentId;

    private String chargeId;

    private String trackId;

    private String reference;

    private Integer skip;

    private Integer limit;

    private Instant from;

    private Instant to;

    final String getPath() {
        final List<String> parameters = new ArrayList<>();
        if (paymentId != null) {
            parameters.add("payment_id=" + paymentId);
        }
        if (chargeId != null) {
            parameters.add("charge_id=" + chargeId);
        }
        if (trackId != null) {
            parameters.add("track_id=" + trackId);
        }
        if (reference != null) {
            parameters.add("reference=" + reference);
        }
        if (from != null) {
            parameters.add("from=" + from);
        }
        if (to != null) {
            parameters.add("to=" + to);
        }
        if (limit != null) {
            parameters.add("limit=" + limit);
        }
        if (skip != null) {
            parameters.add("skip=" + skip);
        }
        if (parameters.isEmpty()) {
            return "";
        }
        return "?".concat(String.join("&", parameters));
    }

}
