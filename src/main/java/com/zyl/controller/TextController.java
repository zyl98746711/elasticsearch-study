package com.zyl.controller;

import com.zyl.dto.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

/**
 * 文章控制器
 */
@RestController
@RequestMapping("text")
public class TextController {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @PostMapping
    public String save(@RequestBody Text text) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId("1").withObject(text).build();
        return elasticsearchOperations.index(indexQuery, elasticsearchOperations.getIndexCoordinatesFor(Text.class));
    }

    @GetMapping
    public Text get(@RequestParam("param") String text) {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("text", text);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(termQueryBuilder);
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        SearchHits<Text> search = elasticsearchOperations.search(searchQuery, Text.class);
        return search.get().findFirst().map(SearchHit::getContent).orElse(null);
    }
}
