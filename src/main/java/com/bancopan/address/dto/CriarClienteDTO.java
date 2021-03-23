package com.bancopan.address.dto;

import com.bancopan.address.model.Cliente;

public class CriarClienteDTO {

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
