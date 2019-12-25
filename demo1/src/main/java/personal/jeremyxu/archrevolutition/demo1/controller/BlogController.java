package personal.jeremyxu.archrevolutition.demo1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import personal.jeremyxu.archrevolutition.demo1.model.Blog;
import personal.jeremyxu.archrevolutition.demo1.model.User;
import personal.jeremyxu.archrevolutition.demo1.service.BlogService;
import personal.jeremyxu.archrevolutition.demo1.service.UserService;

import java.util.List;

/**
 * Created by jeremyxu on 2019/3/26.
 */
@Controller
public class BlogController {
    @Autowired
    UserService userService;

    @Autowired
    BlogService blogService;

    // 查看所有博文
    @RequestMapping(value = "/admin/blogs", method = RequestMethod.GET)
    public String showBlogs(ModelMap modelMap) {
        List<Blog> blogList = blogService.findAll();
        modelMap.addAttribute("blogList", blogList);
        return "admin/blogs";
    }

    // 添加博文
    @RequestMapping(value = "/admin/blogs/add", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap) {
        List<User> userList = userService.findAll();
        // 向jsp注入用户列表
        modelMap.addAttribute("userList", userList);
        return "admin/addBlog";
    }

    // 添加博文，POST请求，重定向为查看博客页面
    @RequestMapping(value = "/admin/blogs/addP", method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blog") Blog blog) {
        // 打印博客标题
        System.out.println(blog.getTitle());
        // 打印博客作者
        System.out.println(blog.getUserByUserId().getNickname());
        // 存库
        blogService.save(blog);
        // 重定向地址
        return "redirect:/admin/blogs";
    }

    // 查看博文详情，默认使用GET方法时，method可以缺省
    @RequestMapping("/admin/blogs/show/{id}")
    public String showBlog(@PathVariable("id") int id, ModelMap modelMap) {
        Blog blog = blogService.findById(id);
        modelMap.addAttribute("blog", blog);
        return "admin/blogDetail";
    }

    // 修改博文内容，页面
    @RequestMapping("/admin/blogs/update/{id}")
    public String updateBlog(@PathVariable("id") int id, ModelMap modelMap) {
        // 是不是和上面那个方法很像
        Blog blog = blogService.findById(id);
        List<User> userList = userService.findAll();
        modelMap.addAttribute("blog", blog);
        modelMap.addAttribute("userList", userList);
        return "admin/updateBlog";
    }

    // 修改博客内容，POST请求
    @RequestMapping(value = "/admin/blogs/updateP", method = RequestMethod.POST)
    public String updateBlogP(@ModelAttribute("blogP") Blog blog) {
        // 更新博客信息
        System.out.println(blog.getTitle());
        blogService.save(blog);
        return "redirect:/admin/blogs";
    }

    // 删除博客文章
    @RequestMapping("/admin/blogs/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
        blogService.deleteById(id);
        return "redirect:/admin/blogs";
    }
}
