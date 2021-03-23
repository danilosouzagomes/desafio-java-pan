package com.bancopan.address.service.impl;

import com.bancopan.address.rest.client.ibge.IbgeRestClient;
import com.bancopan.address.rest.client.ibge.dto.EstadoDTO;
import com.bancopan.address.rest.client.ibge.dto.MunicipioDTO;
import com.bancopan.address.rest.client.viacep.ViaCepRestClient;
import com.bancopan.address.rest.client.viacep.dto.EnderecoViaCepDTO;
import com.bancopan.address.service.IEnderecoService;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService implements IEnderecoService {

    private final ViaCepRestClient viaCepRestClient;
    private final IbgeRestClient ibgeRestClient;

    public EnderecoService(ViaCepRestClient viaCepRestClient, IbgeRestClient ibgeRestClient) {
        this.viaCepRestClient = viaCepRestClient;
        this.ibgeRestClient = ibgeRestClient;
    }

    @Override
    public EnderecoViaCepDTO consultarCep(String cep) {
        return this.viaCepRestClient.obterCep(cep);
    }

    @Override
    public EstadoDTO[] consultaEstados() {
        return this.ibgeRestClient.obterEstados();
    }

    @Override
    public MunicipioDTO[] consultaMunicipios(int uf) {
        return this.ibgeRestClient.obterMunicipios(uf);
    }

}
