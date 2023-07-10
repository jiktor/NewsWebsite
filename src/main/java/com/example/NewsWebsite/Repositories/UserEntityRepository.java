package com.example.NewsWebsite.Repositories;

import com.example.NewsWebsite.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {
	public Optional<UserEntity> findByUsername(String username);
}
