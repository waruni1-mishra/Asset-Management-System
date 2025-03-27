package com.codewithwaruni.asset.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithwaruni.asset.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User , Integer>{
	

}
