package personal.jeremyxu.archrevolutition.demo7.blogservice.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.jeremyxu.archrevolutition.demo7.blogservice.model.Blog;
import personal.jeremyxu.archrevolutition.demo7.blogservice.service.BlogService;

import java.util.List;

/**
 * Created by jeremyxu on 2019/3/26.
 */
@RestController
@RequestMapping(value = "/api/v1")
public class BlogController {
    @Autowired
    BlogService blogService;

    // 查看所有博文
    @GetMapping(value = "/blogs")
    public List<Blog> getBlogs() {
        // 查询blog表中所有记录
        return blogService.findAll();
    }


    // 添加博文
    @PostMapping(value = "/blogs")
    public ResponseEntity addBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity(blog, HttpStatus.OK);
    }

    // 查看博文详情，默认使用GET方法时，method可以缺省
    @GetMapping("/blogs/{id}")
    public ResponseEntity getBlog(@PathVariable("id") Integer blogId) {
        Blog blog = blogService.findById(blogId);
        if (blog == null) {
            return new ResponseEntity("No Blog found for ID " + blogId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(blog, HttpStatus.OK);
    }

    // 修改博文内容，页面
    @PutMapping("/blogs/{id}")
    public ResponseEntity updateBlog(@PathVariable("id") Integer blogId, @RequestBody Blog blog) {
        // 是不是和上面那个方法很像
        Blog oldBlog = blogService.findById(blogId);

        if (null == oldBlog) {
            return new ResponseEntity("No Blog found for ID " + blogId, HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(blog, oldBlog);
        blogService.save(oldBlog);

        return new ResponseEntity(oldBlog, HttpStatus.OK);
    }

    // 删除博客文章
    @DeleteMapping("/blogs/{id}")
    public ResponseEntity deleteBlog(@PathVariable("id") int blogId) {
        // 删除id为userId的用户
        blogService.deleteById(blogId);

        return new ResponseEntity(blogId, HttpStatus.OK);
    }

    @DeleteMapping("/blogs/by_userId/{userId}")
    public ResponseEntity deleteBlogsByUserId(@PathVariable("userId") int userId){
        blogService.deleteByUserId(userId);
        return new ResponseEntity(userId, HttpStatus.OK);
    }
}
