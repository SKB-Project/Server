package com.project.skb.user.domain;

import com.project.skb.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);
}
