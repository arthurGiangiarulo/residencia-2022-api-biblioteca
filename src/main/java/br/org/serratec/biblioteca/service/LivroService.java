package br.org.serratec.biblioteca.service;

import java.util.ArrayList;
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

    public List<LivroDTO> getAllLivrosDTO(){
        List<Livro> listaDeLivros = new ArrayList<Livro>();
        List<LivroDTO> listaDeLivrosDTO = new ArrayList<LivroDTO>();
        listaDeLivros = getAllLivros();
        listaDeLivros.forEach(edt -> {
            listaDeLivrosDTO.add(converteLivroEntityToDTO(edt));
        });
        return listaDeLivrosDTO;
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
    }

    public Livro deleteLivro(int id){
        livroRepository.deleteById(id);
        return getLivroById(id);
    }

    public LivroDTO converteLivroEntityToDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setAllAtributosFromEntidade(livro);
        return livroDTO;
    }

    public Livro converteLivroDTOtoEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setAllAtributosFromDTO(livroDTO);
        return livro;
    }
}
