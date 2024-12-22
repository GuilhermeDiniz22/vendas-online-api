package br.com.vendas_api.controller;

import br.com.vendas_api.dto.LoginRequestDto;
import br.com.vendas_api.dto.RegistroDtoRequest;
import br.com.vendas_api.dto.ResponseDto;
import br.com.vendas_api.model.User;
import br.com.vendas_api.repository.UserRepository;
import br.com.vendas_api.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  TokenService tokenService;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
        User user = userRepository.findByEmail(loginRequestDto.getEmail());

        if(user == null) throw  new RuntimeException("Usuário com email " + loginRequestDto.getEmail() + " não encontrado!");

        if(passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            var token = tokenService.generateToken(user);

            return ResponseEntity.ok(new ResponseDto(user.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegistroDtoRequest registroDtoRequest){
        User user = userRepository.findByEmail(registroDtoRequest.getEmail());

        if(user == null){
            User novoUser = new User();
            novoUser.setNome(registroDtoRequest.getNome());
            novoUser.setEmail(registroDtoRequest.getEmail());
            novoUser.setPassword(passwordEncoder.encode(registroDtoRequest.getPassword()));

            User retorno = userRepository.save(novoUser);

            var token = tokenService.generateToken(novoUser);


            return ResponseEntity.ok(new ResponseDto(retorno.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}