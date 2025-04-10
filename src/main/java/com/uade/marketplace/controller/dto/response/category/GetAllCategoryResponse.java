package com.uade.marketplace.controller.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.uade.marketplace.models.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoryResponse {
    List<Category> categories;
}
