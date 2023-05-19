package com.practica.springdatasecurity.models.services;

import com.practica.springdatasecurity.models.dao.IUsuarioDao;
import com.practica.springdatasecurity.models.entity.Role;
import com.practica.springdatasecurity.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUserDatailsService implements UserDetailsService {

    @Autowired
    private IUsuarioDao usuarioDao;

    private Logger logger= LoggerFactory.getLogger(JpaUserDatailsService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario=  usuarioDao.findByUsername(username);

        if(usuario==null){
            logger.error("upps no existe el usuario ".concat(username));
            throw new UsernameNotFoundException(username.concat(" no existe en el sistema"));

        }

        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();

        for(Role role: usuario.getRoles()){

            logger.info("Role: ".concat(role.getAuthority()));

            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        if(authorities.isEmpty()){
            logger.error("Error en login, el usuario ".concat(username).concat(" no posee roles"));
            throw new UsernameNotFoundException("Error en login, el usuario ".concat(username).concat(" no posee roles"));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);

    }
}
