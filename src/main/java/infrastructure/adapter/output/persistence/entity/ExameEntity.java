package infrastructure.adapter.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name="exames")
public class ExameEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private Date data = new Date();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    public Long getId() {
        return this.id;
    }
}
