package com.digital.userprofile.repository;

import com.digital.userprofile.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<User, String> {



}
