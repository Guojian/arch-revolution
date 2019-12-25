package personal.jeremyxu.archrevolutition.demo5.aggregationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.dto.BlogDTO;
import personal.jeremyxu.archrevolutition.demo5.aggregationservice.service.AggregationService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/aggregation")
public class AggregationController {

    @Autowired
    AggregationService aggregationService;

    @GetMapping("/blogs")
    public List<BlogDTO> getBlogs(){
        return aggregationService.getBlogs();
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity getBlog(@PathVariable("id") Integer blogId){
        BlogDTO blog = aggregationService.getBlog(blogId);
        return new ResponseEntity(blog, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Integer userId){
        aggregationService.deleteUser(userId);
        return new ResponseEntity(userId, HttpStatus.OK);
    }

}
