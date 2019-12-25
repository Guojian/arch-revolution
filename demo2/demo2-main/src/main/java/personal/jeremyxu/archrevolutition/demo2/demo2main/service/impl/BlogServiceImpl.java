package personal.jeremyxu.archrevolutition.demo2.demo2main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo2.demo2main.service.BlogService;
import personal.jeremyxu.archrevolutition.demo2.demo2main.model.Blog;
import personal.jeremyxu.archrevolutition.demo2.demo2main.repository.BlogRepository;
import personal.jeremyxu.archrevolutition.demo2.demo2main.service.UserService;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = blogRepository.findAll();
        for(Blog blog : blogs) {
            blog.setUserByUserId(userService.findUserById(blog.getUserId()));
        }
        return blogs;
    }

    @Override
    public void save(Blog blog) {
        blogRepository.saveAndFlush(blog);
    }

    @Override
    public Blog findById(int blogId) {
        Blog blog = blogRepository.findById(blogId).get();
        blog.setUserByUserId(userService.findUserById(blog.getUserId()));
        return blog;
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
