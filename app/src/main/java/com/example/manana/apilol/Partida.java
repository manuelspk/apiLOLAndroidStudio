package com.example.manana.apilol;

import android.graphics.Bitmap;

/**
 * Created by manana on 23/6/16.
 */
public class Partida {



    private String gameMode;            //Modos de juego
    private String gameType;
    private String subType;

    private int championId;             //id del personaje que ha elegido
    private int spell1;                 //1er hechizo
    private int spell2;                 //2ndo hechizo
    private boolean win;                //¿Partida ganada?
    private int championsKilled;        //Asesinatos
    private int numDeaths;              //muertes
    private int assist;                 //asistencias

    private long createDate;            //Fecha

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

    public boolean isWin() {
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
    }
}
