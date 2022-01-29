package com.example.jpamultipledataresource.repositories.db2;

import com.example.jpamultipledataresource.dbModel.db1.User;
import com.example.jpamultipledataresource.dbModel.db2.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {
}
