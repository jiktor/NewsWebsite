package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.Entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
	public UserEntity findByUsername(String username);
}
