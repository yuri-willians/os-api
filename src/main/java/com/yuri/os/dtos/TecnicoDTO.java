package com.yuri.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.yuri.os.domain.Tecnico;

import org.hibernate.validator.constraints.br.CPF;

public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "O campo NOME é requerido.")
    private String nome;

    @CPF
    @NotEmpty(message = "O campo CPF é requerido.")
    private String cpf;

    @NotEmpty(message = "O campo TELEFONE é requerido.")
    private String telefone;

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
