package com.example.jpamultipledataresource.repositories.db1;

import com.example.jpamultipledataresource.dbModel.db1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
