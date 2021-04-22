package com.zyl.controller;

import com.zyl.dto.Text;
import com.zyl.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章控制器
 */
@RestController
@RequestMapping("text")
public class TextController {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private TextRepository textRepository;

    @PostMapping
    public String save(@RequestBody Text text) {
        textRepository.save(text);
        return "success";
    }

    @GetMapping
    public Text get(@RequestParam("param") String text) {
        List<Text> textList = textRepository.findByText(text);
        if (CollectionUtils.isEmpty(textList)) {
            return null;
        }
        return textList.get(0);
    }
}
