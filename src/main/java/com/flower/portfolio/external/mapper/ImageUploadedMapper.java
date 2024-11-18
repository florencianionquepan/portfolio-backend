package com.flower.portfolio.external.mapper;

import com.flower.portfolio.model.Image;
import com.flower.portfolio.model.WebProject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ImageUploadedMapper {

    public Image mapToImage(Map<String, Object> result, WebProject project){
        Image image=new Image();
        image.setUrl((String)(result.get("url")));
        image.setImageId((String) result.get("public_id"));
        image.setName((String) result.get("original_filename"));
        image.setProject(project);
        return image;
    }

    public List<Image> mapToListImage(List<Map<String, Object>> results, WebProject project){
        return results.stream()
                .map(result -> mapToImage(result, project))
                .collect(Collectors.toList());
    }
}
