package br.org.serratec.biblioteca.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.org.serratec.biblioteca.repository.EditoraRepository;
import br.org.serratec.biblioteca.repository.LivroRepository;
import br.org.serratec.biblioteca.dto.ConsultaCNPJDTO;
import br.org.serratec.biblioteca.dto.EditoraDTO;
import br.org.serratec.biblioteca.dto.LivroDTO;
import br.org.serratec.biblioteca.dto.imgbb.IMGBBDTO;
import br.org.serratec.biblioteca.entity.Editora;
import br.org.serratec.biblioteca.entity.Livro;

@Service
public class EditoraService {
    @Autowired
    EditoraRepository editoraRepository;
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    LivroService livroService;
    @Autowired
    EmailService emailService;
    
    @Value("${imgbb.host.url}")
	private String imgBBHostUrl;

	@Value("${imgbb.host.key}")
    private String imgBBHostKey;

    public List<Editora> getAllEditoras(){
        return editoraRepository.findAll();
    }

    // public List<EditoraDTO> getAllEditorasDTO(){
    //     List<EditoraDTO> listaDTO = new ArrayList<>();
    //     BeanUtils.copyProperties((editoraRepository.findAll()), listaDTO);
    //     return listaDTO;
    // }

    public List<EditoraDTO> getAllEditorasDTO() {
        List<Editora> editora = new ArrayList<>();
        List<EditoraDTO> editoraDTO = new ArrayList<>();
        editora = getAllEditoras();
        editora.forEach(edt -> {
            editoraDTO.add(converteEditoraEntityToDTO(edt));
        });
        return editoraDTO;
    }

    public List<EditoraDTO> getLivrosPorEditoraDTO(){
        List<Editora> listaEditoras = getAllEditoras();
        List<EditoraDTO> listaEditorasDTO = new ArrayList<>();
        
        for(Editora editora: listaEditoras){
            List<Livro> listaLivros = livroRepository.findByEditora(editora);
            EditoraDTO editoraDTO = converteEditoraEntityToDTO(editora);
            List<LivroDTO> listaLivrosDTO = new ArrayList<>();
            
            for(Livro livro: listaLivros){
                LivroDTO livroDTO = livroService.converteLivroEntityToDTO(livro);
                listaLivrosDTO.add(livroDTO);
            }
            editoraDTO.setListaLivrosDTO(listaLivrosDTO);
            listaEditorasDTO.add(editoraDTO);
        }
        return listaEditorasDTO;
    }

    public Editora getEditoraById(int id){
        // return editoraRepository.findById(id).orElse(null);
        return editoraRepository.findById(id).get();
    }

    public EditoraDTO getEditoraDTOById(int id) {
        Editora editora = editoraRepository.findById(id).orElse(null);
        if(editora != null) {
            return converteEditoraEntityToDTO(editora);
        } else {
            return null;
        }
    }

    public Editora saveEditora(Editora editora){
        return editoraRepository.save(editora); 
    }

    public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO){
        emailService.sendEmail("emaildogiango@gmail.com", "Teste API", editoraDTO.toString());
        return converteEditoraEntityToDTO(editoraRepository.save(converteEditoraDTOtoEntity(editoraDTO))); 
    }

    public Editora updateEditora(Editora editora, int id){
        Editora editoraExistenteNoBanco = getEditoraById(id);
        return editoraRepository.save(editoraExistenteNoBanco.setAllAtributos(editora));
    }

    public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, int id){
        if(getEditoraById(id) != null){
            return converteEditoraEntityToDTO(editoraRepository.save(converteEditoraDTOtoEntity(editoraDTO)));
        } return null;
    }

    public Editora deleteEditora(int id){
        editoraRepository.deleteById(id);
        return getEditoraById(id);
    }
    
    private EditoraDTO converteEditoraEntityToDTO(Editora editora) {
        EditoraDTO editoraDTO = new EditoraDTO();
        editoraDTO.setAllAtributosFromEntidade(editora);
        return editoraDTO;
    }

    public Editora converteEditoraDTOtoEntity(EditoraDTO editoraDTO) {
        Editora editora = new Editora();
        editora.setAllAtributosFromDTO(editoraDTO);
        return editora;
    }

    public ConsultaCNPJDTO consultaCnpjApiExterna(String cnpj){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://receitaws.com.br/v1/cnpj/{cnpj}";
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("cnpj", cnpj);

        ConsultaCNPJDTO consultaCnpjDTO = restTemplate.getForObject(uri, ConsultaCNPJDTO.class, params);

        return consultaCnpjDTO;
    }

    private EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		editoraDTO.setImagemFileName(editora.getImagemFileName());
		editoraDTO.setImagemNome(editora.getImagemNome());
		editoraDTO.setImagemUrl(editora.getImagemUrl());

		return editoraDTO;
	}
    
    public EditoraDTO saveFotoImgBB(String editora,
			MultipartFile file) throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
		String serverUrl = imgBBHostUrl + imgBBHostKey;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
		
		ContentDisposition contentDisposition = ContentDisposition
				.builder("form-data")
				.name("image")
				.filename(file.getOriginalFilename())
				.build();
		
		fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
		
		HttpEntity<byte[]> fileEntity = new HttpEntity<>(file.getBytes(), fileMap);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("image", fileEntity);
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity =
				new HttpEntity<>(body, headers);
		
		ResponseEntity<IMGBBDTO> response = null;
		IMGBBDTO imgDTO = new IMGBBDTO();
		Editora novaEditora = new Editora(); 
		try {
			response = restTemplate.exchange(
					serverUrl,
					HttpMethod.POST,
					requestEntity,
					IMGBBDTO.class);
			
			imgDTO = response.getBody();
			System.out.println("ImgBBDTO: " + imgDTO.getData().toString());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}
		
		//Converte os dados da editora recebidos no formato String em Entidade
		//  Coleta os dados da imagem, após upload via API, e armazena na Entidade Editora
		if(null != imgDTO) {
			Editora editoraFromJson = convertEditoraFromStringJson(editora);
			editoraFromJson.setImagemFileName(imgDTO.getData().getImage().getFilename());
			editoraFromJson.setImagemNome(imgDTO.getData().getTitle());
			editoraFromJson.setImagemUrl(imgDTO.getData().getUrl());
			novaEditora = editoraRepository.save(editoraFromJson);
		}
		
		return toDTO(novaEditora);
	}
	
	private Editora convertEditoraFromStringJson(String editoraJson) {
		Editora editora = new Editora();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			editora = objectMapper.readValue(editoraJson, Editora.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância da entidade Editora", err.toString());
		}
		
		return editora;
	}
}
 
