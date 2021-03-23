package com.bancopan.address.dto;

import com.bancopan.address.model.Cliente;

public class ClienteDTO {

    private String cpf;
    private String nome;
    private EnderecoDTO enderecoDTO;

    public ClienteDTO(String cpf, String nome, EnderecoDTO enderecoDTO) {
        this.cpf = cpf;
        this.nome = nome;
        this.enderecoDTO = enderecoDTO;
    }

    public ClienteDTO() {
    }

    public ClienteDTO(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.enderecoDTO = new EnderecoDTO();
    }

    public static ClienteDTO toDto(Cliente cliente){
        if (cliente.getEndereco() == null){
            return new ClienteDTO(cliente.getCpf(), cliente.getNome());
        } else {
            return new ClienteDTO(cliente.getCpf(), cliente.getNome(), EnderecoDTO.toDto(cliente.getEndereco()));
        }
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

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
