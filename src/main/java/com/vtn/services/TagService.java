package com.vtn.services;

import com.vtn.dto.tag.TagResponse;
import com.vtn.pojo.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {

    List<Tag> findByProductId(Long productId);

    Tag findById(Long id);

    void save(Tag tag);

    void update(Tag tag);

    void delete(Long id);

    Long count();

    List<Tag> findAllWithFilter(Map<String, String> params);

    TagResponse getTagResponse(Tag tag);

    List<TagResponse> getAllTagResponse(Map<String, String> params);
}
