package com.yuri.os.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tecnico extends Pessoa {

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<OS> list = new ArrayList<>();

    public Tecnico() {
        super();
    }
    
    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<OS> getList() {
        return this.list;
    }

    public void setList(List<OS> list) {
        this.list = list;
    }

}
