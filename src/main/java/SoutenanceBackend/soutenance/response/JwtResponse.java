package SoutenanceBackend.soutenance.response;

import SoutenanceBackend.soutenance.Models.SerieLycee;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String numero;
    private String nomcomplet;
    private String email;
    private List<String> roles;

   /* public void setNomseries(SerieLycee nomseries) {
        this.nomseries = nomseries;
    }*/


    public SerieLycee getSerie() {
        return Serie;
    }

    public void setSerie(SerieLycee serie) {
        Serie = serie;
    }

    private SerieLycee Serie;

    public JwtResponse(String accessToken, Long id, String nomcomplet, String numero, String email,SerieLycee nomseries, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.nomcomplet= nomcomplet;
        this.numero = numero;
        this.email = email;
        this.roles = roles;
        this.Serie = nomseries;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<String> getRoles() {
        return roles;
    }
}
