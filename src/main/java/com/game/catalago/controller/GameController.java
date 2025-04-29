package com.game.catalago.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.game.catalago.dto.GameDTO;
import com.game.catalago.entity.GameEntity;
import com.game.catalago.repository.GameRepository;
import com.game.catalago.service.GameService;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository repo;

    @GetMapping("/listar")
    public List<GameEntity> listar() {
        return gameService.listarTodos();
    }

    @PostMapping("/criar")
    public ResponseEntity<GameEntity> criar(@RequestBody GameDTO dto) {
        // converte DTO em entidade
        GameEntity entidade = new GameEntity();
        entidade.setName(dto.name());
        entidade.setImage(dto.image());
        entidade.setDescription(dto.description());
        GameEntity salvo = gameService.salvar(entidade);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<GameEntity> buscarPorId(@PathVariable Long id) {
        GameEntity entidade = gameService.buscarPorId(id);
        return entidade != null
            ? ResponseEntity.ok(entidade)
            : ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<GameEntity> atualizar(
        @PathVariable Long id,
        @RequestBody GameDTO dto
    ) {
        GameEntity game = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jogo não encontrado"));

        game.updatedDTO(dto);
        GameEntity atualizado = repo.save(game);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        gameService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
