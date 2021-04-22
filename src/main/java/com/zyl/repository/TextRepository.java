package com.zyl.repository;

import com.zyl.dto.Text;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TextRepository extends CrudRepository<Text, Integer> {

    List<Text> findByText(String text);
}
