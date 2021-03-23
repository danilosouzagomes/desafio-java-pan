package com.bancopan.address.rest.client.viacep;

import com.bancopan.address.rest.client.exception.ErroConsultaClientException;
import com.bancopan.address.rest.client.viacep.dto.EnderecoViaCepDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ViaCepRestClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URI = "http://viacep.com.br/ws/{cep}/json/";

    public EnderecoViaCepDTO obterCep(String cep){

        Map<String, String> parametros = new HashMap<>();
        parametros.put("cep", cep);

        EnderecoViaCepDTO enderecoDto = new EnderecoViaCepDTO();

        try {
            enderecoDto = restTemplate.getForObject(BASE_URI, EnderecoViaCepDTO.class, parametros);
        } catch (RestClientException e) {
            throw new ErroConsultaClientException(this, e);
        }

        return enderecoDto;
    }
}
