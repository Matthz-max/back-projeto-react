
package com.game.catalago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.game.catalago.dto.GameDTO;
import com.game.catalago.entity.GameEntity;
import com.game.catalago.repository.GameRepository;
import com.game.catalago.service.GameService;

@CrossOrigin(origins = "*")  
@RestController
@RequestMapping("Game")
public class GameController {

	@Autowired
    private GameService  GameService;
	
	@Autowired
	GameRepository repo;

    @GetMapping("/listar")
    public List<GameEntity> listar() {
        return GameService.listarTodos();
    }

    @PostMapping("/criar")
    public ResponseEntity<GameEntity> criar(@RequestBody GameEntity GameImagem) {
        System.out.println("URL da imagem recebida: " + GameImagem.getImage());
        return new ResponseEntity<>(GameService.salvar(GameImagem), HttpStatus.CREATED);
    }


    @GetMapping("/listar/{id}")
    public ResponseEntity<GameEntity> buscarPorId(@PathVariable Long id) {
    	GameEntity GameImagem = GameService.buscarPorId(id);
        return GameImagem != null ? ResponseEntity.ok(GameImagem) : ResponseEntity.notFound().build();
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCarro(@PathVariable Long id, @RequestBody GameDTO dto) {
    	GameEntity game = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));

        // Atualizar as propriedades com os dados do DTO
        game.updatedDTO(dto);
 
        repo.save(game);

        return ResponseEntity.ok(game);   
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
    	GameService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}