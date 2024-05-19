package com.example.demo.iservices.services;

import com.example.demo.dtos.response.JwtResponse;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.iservices.IUserService;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenProvider;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Override
    public User insert(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email " + user.getEmail() + " already exists");
        }
        String bcryptPassword = bcrypt.encode(user.getPassword());
        user.setPassword(bcryptPassword);
        var roles = roleRepository.findByNameRole(Role.NameRole.DONANTE);
        user.setRoles((Collections.singleton(roles)));
        return userRepository.save(user);
    }
    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public User update(User user) throws Exception {
        searchId(user.getId());
        return userRepository.save(user);
    }
    @Override
    public User searchId(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }
    @Override
    public void delete(Long id) throws Exception{
        User userinactive = searchId(id);
        userinactive.setEnabled(false);
        userRepository.save(userinactive);
    }
    @Override
    public JwtResponse login(String email, String password) {
        try {
            authenticate(email, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        final String token = jwtTokenProvider.generateToken(userDetails);
        return new JwtResponse(token);
    }
    @Override
    public void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @Override
    public List<User> findByRoleDonante() {
        return userRepository.findByRoleDonante();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public Optional<User> findDonorById(Long id) {
        return userRepository.findDonorById(id);
    }
}
