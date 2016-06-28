package com.example.manana.apilol;

/**
 * Created by Trabajo on 28/06/2016.
 */
public class Estadisticas {
    private String playerStatSummaryType;
    private long modifyDate;
    private int wins;
    private AggregatedStats aggregatedStats;

    public String getPlayerStatSummaryType() {
        return playerStatSummaryType;
    }

    public void setPlayerStatSummaryType(String playerStatSummaryType) {
        this.playerStatSummaryType = playerStatSummaryType;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public AggregatedStats getAggregatedStats() {
        return aggregatedStats;
    }

    public void setAggregatedStats(AggregatedStats aggregatedStats) {
        this.aggregatedStats = aggregatedStats;
    }
}
