package org.maksymov.demo.db.repository;

import org.maksymov.demo.db.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByLoginIgnoreCase(String login);

}
