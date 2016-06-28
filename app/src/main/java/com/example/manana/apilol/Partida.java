package com.example.manana.apilol;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by manana on 23/6/16.
 */
public class Partida implements Serializable{







    private int championId;             //id del personaje que ha elegido
    private long createDate;            //Fecha
    private long gameId;
    private String gameMode;            //Modos de juego
    private String gameType;
    private boolean invalid;
    private int ipEarned;
    private int level;
    private int mapId;
    private int spell1;                 //1er hechizo
    private int spell2;                 //2ndo hechizo
    private RawStatsDto stats;
    private String subType;
    private int teamId;

 //   private boolean win;                //¿Partida ganada?
 //   private int championsKilled;        //Asesinatos
 //   private int numDeaths;              //muertes
 //   private int assist;                 //asistencias


    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public int getIpEarned() {
        return ipEarned;
    }

    public void setIpEarned(int ipEarned) {
        this.ipEarned = ipEarned;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public RawStatsDto getStats() {
        return stats;
    }

    public void setStats(RawStatsDto stats) {
        this.stats = stats;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }




    public Bitmap getImagenPersonaje() {
        return imagenPersonaje;
    }

    public void setImagenPersonaje(Bitmap imagenPersonaje) {
        this.imagenPersonaje = imagenPersonaje;
    }

    private Bitmap imagenPersonaje;


    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public int getSpell1() {
        return spell1;
    }

    public void setSpell1(int spell1) {
        this.spell1 = spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public void setSpell2(int spell2) {
        this.spell2 = spell2;
    }

 /*   public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getChampionsKilled() {
        return championsKilled;
    }

    public void setChampionsKilled(int championsKilled) {
        this.championsKilled = championsKilled;
    }

    public int getNumDeaths() {
        return numDeaths;
    }

    public void setNumDeaths(int numDeaths) {
        this.numDeaths = numDeaths;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }*/
}
