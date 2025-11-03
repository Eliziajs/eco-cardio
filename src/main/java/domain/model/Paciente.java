package domain.model;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Paciente implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String CPF;
    private Integer idade;
    private String sexo;
    private double peso;
    private double altura;
    private LocalDateTime dataPaciente;

    public Long getId() {
        return id;
    }
}
