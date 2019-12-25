package personal.jeremyxu.archrevolutition.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.jeremyxu.archrevolutition.demo1.model.User;

/**
 * Created by jeremyxu on 2019/3/26.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
