//package com.example.springJwt.repo;
//
//import com.example.springJwt.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User,Integer> {
//
//    Optional<User> findByUsername(String username);
//}

package com.example.springJwt.repo;

import com.example.springJwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
