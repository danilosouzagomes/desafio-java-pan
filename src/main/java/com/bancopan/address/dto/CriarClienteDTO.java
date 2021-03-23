package com.bancopan.address.dto;

import com.bancopan.address.model.Cliente;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CriarClienteDTO {

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;
    private String nome;

    public Cliente getCliente() {
        return new Cliente(cpf, nome);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
