package personal.jeremyxu.archrevolutition.demo4.blogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.jeremyxu.archrevolutition.demo4.blogservice.model.Blog;

/**
 * Created by jeremyxu on 2019/3/26.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

    // 修改博文操作
    @Modifying
    @Transactional
    @Query("delete from Blog blog where blog.userId=:qUserId")
    void deleteBlogByUserId(@Param("qUserId") int userId);
}
