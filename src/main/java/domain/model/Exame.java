package domain.model;

import java.io.Serializable;
import java.util.Date;

public class Exame  implements Serializable {

    private Long id;
    private Double ao;
    private Double ae;
    private Double vid;
    private Double ved;
    private Double ves;
    private Double siv;
    private Double pp;
    private Double fracaoEjecao;
    private Double e;
    private Double eLinha;
    private String medidasCavitarias;
    private String medidasVE;
    private String funcaoSistolica;
    private String contratilidadeSegmentar;
    private String funcaoDiastolica;
    private String cavidadesDireita;
    private String aorta;
    private String valvulaAortica;
    private String valvulaMitral;
    private String tricuspide;
    private String valvulaPulmonar;
    private String pericardio;
    private String cava;
    private String comentario;
    private Date data = new Date();


    public Long getId() {
        return this.id;
    }
}
