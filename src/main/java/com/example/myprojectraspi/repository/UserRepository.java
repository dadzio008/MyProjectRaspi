//package com.example.myprojectraspi.repository;
//
//import com.example.myprojectraspi.model.User;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//public interface UserRepository extends CrudRepository<User, Long> {
//
//    @Query("SELECT u FROM User u WHERE u.login = : login")
//    public User getUserByLogin(@Param("login") String login);
//}
