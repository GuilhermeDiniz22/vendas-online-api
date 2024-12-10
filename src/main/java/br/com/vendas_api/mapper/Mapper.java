package br.com.vendas_api.mapper;

import br.com.vendas_api.dto.LivroDto;
import br.com.vendas_api.model.Livro;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Mapper {

    public LivroDto convertToLivroDto(Livro livro){
        LivroDto livroDto = new LivroDto();

        livroDto.setId((livro.getId()));
        livroDto.setNome(livro.getNome());
        livroDto.setPreco(livro.getPreco());
        livroDto.setImagem(livro.getImagem());
        livroDto.setEditora(livro.getEditora());
        livroDto.setDescricao(livro.getDescricao());
        livroDto.setAutor(livro.getAutor());
        livroDto.setCopiasDisponiveis(livro.getCopiasDisponiveis());
        livroDto.setCategoria(livro.getCategoria());

        return livroDto;

    }

    public Livro convertToLivro(LivroDto livro){
        Livro livroNovo = new Livro();

        livroNovo.setId((livro.getId()));
        livroNovo.setNome(livro.getNome());
        livroNovo.setPreco(livro.getPreco());
        livroNovo.setImagem(livro.getImagem());
        livroNovo.setEditora(livro.getEditora());
        livroNovo.setDescricao(livro.getDescricao());
        livroNovo.setAutor(livro.getAutor());
        livroNovo.setCopiasDisponiveis(livro.getCopiasDisponiveis());
        livroNovo.setCategoria(livro.getCategoria());
        livroNovo.setAtivo(Boolean.TRUE);

        return livroNovo;

    }
}