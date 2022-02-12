package br.edu.iftm.orquestra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.iftm.orquestra.model.Usuario;
import br.edu.iftm.orquestra.repository.UsuarioRepository;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscaPorUsuario(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(usuario);
    }
}