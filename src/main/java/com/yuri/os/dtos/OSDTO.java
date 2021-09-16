package com.yuri.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yuri.os.domain.OS;

public class OSDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    
    @NotEmpty(message = "O campo OBSERVAÇÕES é requirido.")
    private String observacoes;

    private Integer prioridade;
    private Integer status;
    private Integer idTecnico;
    private Integer idCliente;


    public OSDTO() {
        super();
    }

    public OSDTO(OS os) {
        super();
        this.id = os.getId();
        this.dataAbertura = os.getDataAbertura();
        this.dataFechamento = os.getDataFechamento();
        this.prioridade = os.getPrioridade().getCod();
        this.observacoes = os.getObservacoes();
        this.status = os.getStatus().getCod();
        this.idTecnico = os.getTecnico().getId();
        this.idCliente = os.getCliente().getId();
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAbertura() {
        return this.dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return this.dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getObservacoes() {
        return this.observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdTecnico() {
        return this.idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    


}
