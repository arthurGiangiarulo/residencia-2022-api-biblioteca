package br.org.serratec.biblioteca.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;

import br.org.serratec.biblioteca.dto.ConsultaCNPJDTO;
import br.org.serratec.biblioteca.dto.EditoraDTO;
import br.org.serratec.biblioteca.entity.Editora;
import br.org.serratec.biblioteca.service.EditoraService;
import io.micrometer.core.ipc.http.HttpSender;
import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/editoras")
public class EditoraController {
    @Autowired
    EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<Editora>> getAllEditoras(){
        return new ResponseEntity<>(editoraService.getAllEditoras(), HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<EditoraDTO>> getAllEditorasDTO(){
        return new ResponseEntity<>(editoraService.getAllEditorasDTO(), HttpStatus.OK);
    }

    @GetMapping("/livrosporeditoras/dto")
    public ResponseEntity<List<EditoraDTO>> getLivrosPorEditoraDTO(){
        return new ResponseEntity<>(editoraService.getLivrosPorEditoraDTO(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> getEditoraById(@PathVariable int id){
        Editora editora = editoraService.getEditoraById(id);
        if(editora != null){
            return new ResponseEntity<>(editora, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(editora, HttpStatus.NOT_FOUND);
        }
    }

    // @GetMapping("dto/{id}")
    // public ResponseEntity<EditoraDTO> getEditoraDTOById(@PathVariable int id){
    //     EditoraDTO editoraDTO = editora.converterParaEntidadeFromDTO(editoraService.getEditoraDTOById(id)) ;
    //     if(editoraDTO != null){
    //         return new ResponseEntity<>(editoraDTO, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(editoraDTO, HttpStatus.NOT_FOUND);
    //     }
    // }

    @PostMapping
    public ResponseEntity<Editora> saveEditora(@RequestBody Editora editora) {
        return new ResponseEntity<>(editoraService.saveEditora(editora), HttpStatus.CREATED);
    }

    @PostMapping("/dto")
    public ResponseEntity<EditoraDTO> saveEditoraDTO(@RequestBody EditoraDTO editoraDTO) {
        if(editoraDTO != null){
            return new ResponseEntity<>(editoraService.saveEditoraDTO(editoraDTO), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(editoraDTO, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora, @PathVariable int id) {
        return new ResponseEntity<>(editoraService.updateEditora(editora, id), HttpStatus.OK);
    }

    @PutMapping("dto/{id}")
    public ResponseEntity<EditoraDTO> updateEditoraDTO(@RequestBody EditoraDTO editoraDTO, @PathVariable int id) {
        return new ResponseEntity<>(editoraService.updateEditoraDTO(editoraDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<Editora> deleteEditora(@PathVariable int id){
            Editora editora = editoraService.getEditoraById(id);
            if(editora == null){
                return new ResponseEntity<>(editora, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(editoraService.deleteEditora(id), HttpStatus.OK);
            }
        }    

    // @GetMapping("/apiexterna/cnpj/{cnpj}")
    // public ConsultaCNPJDTO consultaCnpjApiExterna(@PathVariable String cnpj){
    //     return editoraService.consultaCnpjApiExterna(cnpj);
    // }

    @GetMapping("/apiexterna/cnpj/{cnpj}")
    public ResponseEntity<ConsultaCNPJDTO> consultaCnpjApiExterna(@PathVariable String cnpj){
        ConsultaCNPJDTO consultaCnpjDTO = editoraService.consultaCnpjApiExterna(cnpj);
        if(consultaCnpjDTO != null){
            return new ResponseEntity<>(consultaCnpjDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(consultaCnpjDTO, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/apiexterna/cnpj/save/{cnpj}")
    public ResponseEntity<EditoraDTO> salvaNomeApiExterna(@PathVariable String cnpj){
        ConsultaCNPJDTO consultaCnpjDTO = editoraService.consultaCnpjApiExterna(cnpj);
        EditoraDTO editoraDTO = new EditoraDTO();
        if(consultaCnpjDTO != null){
            editoraDTO.setNome(consultaCnpjDTO.getNome());
            return new ResponseEntity<>(editoraService.saveEditoraDTO(editoraDTO), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(editoraDTO, HttpStatus.NOT_FOUND);
        }
    }

    //Endpoints about images

    //save image
    @PostMapping(value = "/editora-com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE })
public ResponseEntity<EditoraDTO> saveEditoraComFoto(@RequestPart("editora") String editora,
       @RequestPart("filename") MultipartFile file) throws IOException {
   
   EditoraDTO editoraDTO = editoraService.saveFotoImgBB(editora, file);
   
   if (null == editoraDTO)
       return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
   else
       return new ResponseEntity<>(editoraDTO, HttpStatus.CREATED);
}

    //get image
    //update image
    //delete image
}

