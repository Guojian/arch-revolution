package personal.jeremyxu.archrevolutition.demo1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo1.model.Blog;
import personal.jeremyxu.archrevolutition.demo1.repository.BlogRepository;
import personal.jeremyxu.archrevolutition.demo1.service.BlogService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void save(Blog blog) {
        blogRepository.saveAndFlush(blog);
    }

    @Override
    public Blog findById(int blogId) {
        return blogRepository.findById(blogId).get();
    }

    @Override
    public void deleteById(int blogId) {
        blogRepository.deleteById(blogId);
        blogRepository.flush();
    }

    @Override
    public void deleteByUserId(Integer userId) {
        blogRepository.deleteBlogByUserId(userId);
        blogRepository.flush();
    }
}
