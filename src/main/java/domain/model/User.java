package domain.model;

import java.util.Date;


public class User {

    private Long id;

    private String name;
    private String lastName;
    private String genero;
    private String telefone;
    private String email;
    private String password;
    private String crm;
    private String cpf;
    private Date data = new Date();

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}

