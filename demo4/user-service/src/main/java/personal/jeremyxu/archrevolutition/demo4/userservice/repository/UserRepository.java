package personal.jeremyxu.archrevolutition.demo4.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.jeremyxu.archrevolutition.demo4.userservice.model.User;

/**
 * Created by jeremyxu on 2019/3/26.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
