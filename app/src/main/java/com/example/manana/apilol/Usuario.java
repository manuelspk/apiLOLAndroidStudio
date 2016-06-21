package com.example.manana.apilol;


import java.io.Serializable;

/**
 * Created by manana on 7/6/16.
 */
public class Usuario implements Serializable{




    private long id;                            //Número de identificación del usuario.
    private String summonerName;                //Nombre de nuestro jugador.
    private String name;                        //Nombre de nuestro jugador (El que devuelve la API)
    private int profileIconId;                  //El número de avatar del jugador. Lo usaremos para obtener la imagen a través de los datos estáticos de la API.
    private long revisionDate;                  //Long que indica si ha habido alguna modificación en el perfil.
    private long summonerLevel;                 //Nivel del jugador


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(long summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

}
