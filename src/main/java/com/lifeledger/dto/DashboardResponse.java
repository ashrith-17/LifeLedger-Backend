package com.lifeledger.dto;

public class DashboardResponse {

    private long totalItems;
    private long upcomingItems;
    private long overdueItems;
    private long dueSoonItems;
    private double totalCost;

    public DashboardResponse(
            long totalItems,
            long upcomingItems,
            long overdueItems,
            long dueSoonItems,
            double totalCost) {

        this.totalItems = totalItems;
        this.upcomingItems = upcomingItems;
        this.overdueItems = overdueItems;
        this.dueSoonItems = dueSoonItems;
        this.totalCost = totalCost;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public long getUpcomingItems() {
        return upcomingItems;
    }

    public long getOverdueItems() {
        return overdueItems;
    }

    public long getDueSoonItems() {
        return dueSoonItems;
    }

    public double getTotalCost() {
        return totalCost;
    }
}