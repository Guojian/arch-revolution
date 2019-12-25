package personal.jeremyxu.archrevolutition.demo4.aggregationservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.jeremyxu.archrevolutition.demo4.aggregationservice.dto.Blog;
import personal.jeremyxu.archrevolutition.demo4.aggregationservice.dto.BlogDTO;
import personal.jeremyxu.archrevolutition.demo4.aggregationservice.dto.UserDTO;
import personal.jeremyxu.archrevolutition.demo4.aggregationservice.service.AggregationService;
import personal.jeremyxu.archrevolutition.demo4.aggregationservice.service.BlogProxyService;
import personal.jeremyxu.archrevolutition.demo4.aggregationservice.service.UserFeignService;

import java.util.LinkedList;
import java.util.List;

@Service
public class AggregationServiceImpl implements AggregationService {
    @Autowired
    UserFeignService userFeignService;

    @Autowired
    BlogProxyService blogProxyService;

    @Override
    public void deleteUser(Integer userId) {
        // 先删除该用户的博文
        blogProxyService.deleteBlogsByUserId(userId);
        // 再删除该用户
        userFeignService.deleteUser(userId);
    }

    @Override
    public List<BlogDTO> getBlogs() {
        List<BlogDTO> result = new LinkedList<>();
        // 先取到博文列表
        List<Blog> blogs = blogProxyService.getBlogs();
        // 再补充其它关联字段
        for(Blog b : blogs){
            BlogDTO blog = convertToBlogDTO(b);
            result.add(blog);
        }
        return result;
    }

    @Override
    public BlogDTO getBlog(Integer blogId) {
        Blog b = blogProxyService.getBlog(blogId);
        BlogDTO blog = convertToBlogDTO(b);
        return blog;
    }

    private BlogDTO convertToBlogDTO(Blog b) {
        BlogDTO blog = new BlogDTO();
        BeanUtils.copyProperties(b, blog);
        UserDTO user = userFeignService.getUser(blog.getUserId());
        blog.setNickname(user.getNickname());
        blog.setFirstName(user.getFirstName());
        blog.setLastName(user.getLastName());
        return blog;
    }
}
