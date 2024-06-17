package com.wrkspot.assessment.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;

import java.util.List;

@RequiredArgsConstructor
public class PageModelGenerator<T> {
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public PagedModel<T> getPageModel(Page<T> page) {

        return pagedResourcesAssembler.toModel(page);

    }

    public PagedModel<T> getPageModel(List<T> list, Pageable pageable, long totalCount) {
        Page<T> page = new PageImpl<>(list, pageable, totalCount);
        return pagedResourcesAssembler.toModel(page);
    }


}
