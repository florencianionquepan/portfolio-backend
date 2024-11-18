package com.flower.portfolio.repository;

import com.flower.portfolio.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface IImageRepository extends CrudRepository<Image, Long> {
}
