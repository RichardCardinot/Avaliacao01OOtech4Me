package classes;

public class Aeronave {
    private String cdRegistro;
    private String modelo;
    private Piloto piloto;

    public Aeronave (String cdRegistro, String modelo, Piloto piloto) {
        setCdRegistro(cdRegistro);
        setModelo(modelo);
        setPiloto(piloto);
    }

    public Aeronave (String cdRegistro, String modelo) {
        setModelo(modelo);
        setCdRegistro(cdRegistro);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCdRegistro() {
        return cdRegistro;
    }
    public void setCdRegistro(String cdRegistro) {
        this.cdRegistro = cdRegistro;
    }
    public Piloto getPiloto() {
        return piloto;
    }
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    
}
