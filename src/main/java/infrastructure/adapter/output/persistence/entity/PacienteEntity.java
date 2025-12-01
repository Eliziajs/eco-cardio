package infrastructure.adapter.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pacientes")
public class PacienteEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column
    private String email;
    @Column(nullable = true,unique = true)
    private String CPF;
    private Integer idade;
    //@Column(nullable = false)
    private String sexo;
    //private Date dataNascimento;
    private double peso;
    private double altura;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataPaciente;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   /** @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_paciente",
    joinColumns = {@JoinColumn (name = "id_user")},
    inverseJoinColumns = {@JoinColumn (name = "id_paciente")})*/ 
   //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonManagedReference
    


    @ManyToOne
    @JoinColumn(name = "medico_id") // Coluna de chave estrangeira na tabela Paciente
    private UserEntity medico;

  
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    
    private List<ExameEntity> exames;

    public Long getId() {
        return id;
    }
}
