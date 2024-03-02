package com.example.backendphotobook.services;

import com.example.backendphotobook.dtos.request.CadastroUsuarioRequest;
import com.example.backendphotobook.entities.UsuariosEntity;
import com.example.backendphotobook.enums.RoleEnum;
import com.example.backendphotobook.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    private final static String DATE_FORMAT = "dd-MM-yyyy";

    public Optional<UsuariosEntity> findByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    @Transactional
    public void cadastrarNovoUsuario(CadastroUsuarioRequest cadastroUsuarioRequest) {

        validarSeEmailExiste(cadastroUsuarioRequest.getEmail());

        UsuariosEntity usuariosEntity = new UsuariosEntity();
        BeanUtils.copyProperties(cadastroUsuarioRequest, usuariosEntity);

        usuariosEntity.setSenha(new BCryptPasswordEncoder().encode(cadastroUsuarioRequest.getSenha()));

        LocalDate dataDeCadastro = LocalDate.now();

        usuariosEntity.setDataDeCadastro(dataDeCadastro);
        usuariosEntity.setRole(RoleEnum.ROLE_USUARIO);
        usuariosEntity.setUsuarioAtivo(true);

        usuariosRepository.save(usuariosEntity);
    }

    private void validarSeEmailExiste(String email) {
        Optional<UsuariosEntity> usuariosEntity = findByEmail(email);

        if (usuariosEntity.isPresent()) {
            throw new RuntimeException("Email ja cadastrado");
        }
    }

    public UsuariosEntity findUsuariosById(Long usuarioId) {
        Optional<UsuariosEntity> entity = usuariosRepository.findById(usuarioId);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }
}
