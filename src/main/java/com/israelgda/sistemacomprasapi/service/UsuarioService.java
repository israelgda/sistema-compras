package com.israelgda.sistemacomprasapi.service;

import com.israelgda.sistemacomprasapi.entities.Usuario;
import com.israelgda.sistemacomprasapi.repositories.RoleRepository;
import com.israelgda.sistemacomprasapi.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioService(BCryptPasswordEncoder bCryptPasswordEncoder, UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            logger.error("User not found: " + email);
            throw new UsernameNotFoundException("Email not found!");
        }
        logger.info("User found: " + email);
        return usuario;
    }
}
