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

import java.util.Date;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    UsuariosRepository usuariosRepository;

    public Optional<UsuariosEntity> findByEmail(String email) {
        return usuariosRepository.findByEmail(email);
    }

    @Transactional
    public void cadastrarNovoUsuario(CadastroUsuarioRequest cadastroUsuarioRequest) {

        validarSeEmailExiste(cadastroUsuarioRequest.getEmail());

        UsuariosEntity usuariosEntity = new UsuariosEntity();
        BeanUtils.copyProperties(cadastroUsuarioRequest, usuariosEntity);

        usuariosEntity.setSenha(new BCryptPasswordEncoder().encode(cadastroUsuarioRequest.getSenha()));

        Date dataDeCadastro = new Date();

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

    public UsuariosEntity findById(long usuarioId) {
        Optional<UsuariosEntity> usuariosEntity = usuariosRepository.findById(usuarioId);

        if (usuariosEntity.isPresent()) {
            return usuariosEntity.get();
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
    }
}
