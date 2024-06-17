package com.wrkspot.assessment.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomPageableRequest implements Pageable {

    private int page;
    private int size;
    private Sort sort;

    public CustomPageableRequest(int page, int size, Sort sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return (long) page * size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new CustomPageableRequest(page + 1, size, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        return page == 0 ? this : new CustomPageableRequest(page - 1, size, sort);
    }

    @Override
    public Pageable first() {
        return new CustomPageableRequest(0, size, sort);
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    @Override
    public Pageable withPage(int page) {
        return new CustomPageableRequest(page, size, sort);
    }
}

