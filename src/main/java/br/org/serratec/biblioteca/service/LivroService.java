package br.org.serratec.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.biblioteca.repository.LivroRepository;
import br.org.serratec.biblioteca.dto.LivroDTO;
import br.org.serratec.biblioteca.entity.Livro;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    public List<Livro> getAllLivros(){
        return livroRepository.findAll();
    }

    public Livro getLivroById(int id){
        return livroRepository.findById(id).orElse(null);
    }

    public Livro saveLivro(Livro livro){
        return livroRepository.save(livro); 
    }

    public LivroDTO saveLivroDTO(LivroDTO livroDTO){
        return converteLivroEntityToDTO(livroRepository.save(converteLivroDTOtoEntity(livroDTO)));
    }

    public Livro updateLivro(Livro livro, int id){
        Livro livroExistenteNoBanco = getLivroById(id);
        livroExistenteNoBanco.setAllAtributos(livro);
        return livroRepository.save(livroExistenteNoBanco);
    }

    public LivroDTO updateLivroDTO(LivroDTO livroDTO, int id){
        if(getLivroById(id) != null){
            return converteLivroEntityToDTO(livroRepository.save(converteLivroDTOtoEntity(livroDTO)));
        } return null;
    //Corrigir a brecha que há quando o usuário preenche o id do JSON diferente do id do http. 
    }

    public Livro deleteLivro(int id){
        livroRepository.deleteById(id);
        return getLivroById(id);
    }

    private LivroDTO converteLivroEntityToDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setAllAtributosFromEntidade(livro);
        return livroDTO;
    }

    private Livro converteLivroDTOtoEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setAllAtributosFromDTO(livroDTO);
        return livro;
    }
}
