package tech.yukino.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.yukino.community.dto.PaginationDTO;
import tech.yukino.community.dto.QuestionDTO;
import tech.yukino.community.mapper.QuestionMapper;
import tech.yukino.community.mapper.UserMapper;
import tech.yukino.community.model.Question;
import tech.yukino.community.model.User;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Resource
    QuestionMapper questionMapper;
    @Resource
    UserMapper userMapper;
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();//问题总数
        paginationDTO.setPagination(totalCount, page, size);
        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        //offset = size * (page - 1)
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions ) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //拷贝属性
            //巧妙不使用外键的方法
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);

        return paginationDTO;
    }
}
