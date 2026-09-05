package com.rohit.ecommerce_backend.dto;

import lombok.Builder;
import java.util.List;

@Builder
public class PagedResponse<T>{
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;
}
