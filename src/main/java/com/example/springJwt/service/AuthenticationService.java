//
//
//package com.example.springJwt.service;
//
//import com.example.springJwt.model.AuthenticationResponse;
//import com.example.springJwt.model.User;
//import com.example.springJwt.repo.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtService = jwtService;
//        this.authenticationManager = authenticationManager;
//    }
//
//    public AuthenticationResponse register(User request) {
//        User user = new User();
////        user.setFirstName(request.getFirstName());
////        user.setLastName(request.getLastName());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        user.setRole(request.getRole());
//        user = userRepository.save(user);
//        String token = jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
//    }
//    public AuthenticationResponse authenticate(User request) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            request.getUsername(),
//                            request.getPassword()
//                    )
//            );
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid credentials");
//        }
//
//        User user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        String token = jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
//    }
//
//}

package com.example.springJwt.service;

import com.example.springJwt.model.AuthenticationResponse;
import com.example.springJwt.model.User;
import com.example.springJwt.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
