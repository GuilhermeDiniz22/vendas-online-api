package br.com.vendas_api.service;

import br.com.vendas_api.dto.EnderecoDto;
import br.com.vendas_api.exception.EnderecoNaoEncontradoException;
import br.com.vendas_api.mapper.Mapper;
import br.com.vendas_api.model.Endereco;
import br.com.vendas_api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private Mapper mapper;

    public String postEndereco(EnderecoDto enderecoDto){

        Endereco endereco = enderecoRepository.save(mapper.convertToEndereco(enderecoDto));

        return "Endereço adicionado com sucesso!";
    }

    public List<EnderecoDto> getEnderecos(){
        List<Endereco> enderecos = enderecoRepository.findAll();

        if(enderecos.isEmpty()){
            throw new EnderecoNaoEncontradoException("Nenhum endereço cadastrado!");
        }

        return enderecos.stream().map(mapper::convertToEnderecoDto)
                .collect(Collectors.toList());
    }

    public EnderecoDto getEnderecoById(Long id){
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(
                () -> new EnderecoNaoEncontradoException("Endereco com id: '%d' não encontrado!", id));

        return mapper.convertToEnderecoDto(endereco);
    }

}
