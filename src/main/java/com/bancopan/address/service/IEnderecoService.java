package com.bancopan.address.service;

import com.bancopan.address.rest.client.ibge.dto.EstadoDTO;
import com.bancopan.address.rest.client.ibge.dto.MunicipioDTO;
import com.bancopan.address.rest.client.viacep.dto.EnderecoViaCepDTO;

public interface IEnderecoService {

    EnderecoViaCepDTO consultarCep(String cep);
    EstadoDTO[] consultaEstados();
    MunicipioDTO[] consultaMunicipios(int uf);

}
