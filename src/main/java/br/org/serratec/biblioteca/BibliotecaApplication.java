package br.org.serratec.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	//1. Percorrer a lista de entidades Editora (chamada listaEditora)
		//2. Na lista de entidade, pegar cada entidade existente nela
		//3. Transformar cada entidade existente na lista em um DTO
		//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
		//5. Retornar/devolver a lista de DTO preenchida
		
		//OBS: para converter a entidade no DTO, usar o metodo toDTO
		
}
