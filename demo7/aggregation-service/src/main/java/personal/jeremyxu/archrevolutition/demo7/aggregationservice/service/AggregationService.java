package personal.jeremyxu.archrevolutition.demo7.aggregationservice.service;

import personal.jeremyxu.archrevolutition.demo7.aggregationservice.dto.BlogDTO;

import java.util.List;

public interface AggregationService {
    void deleteUser(Integer userId);

    List<BlogDTO> getBlogs();

    BlogDTO getBlog(Integer blogId);
}
