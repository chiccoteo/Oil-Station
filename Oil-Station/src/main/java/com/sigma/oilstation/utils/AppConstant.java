package com.sigma.oilstation.utils;

public interface AppConstant {

    String BASE_PATH = "/api/oil/station";

    String POST_PATH = "/add";
    String GET_BY_ID_PATH = "/getById{id}";
    String GET_ALL_PATH = "/getAll";
    String PUT_PATH = "/update{id}";
    String DELETE_PATH = "/delete{id}";

    String DEFAULT_PAGE = "0";

    String DEFAULT_SIZE = "10";

    Integer MAX_PAGE_SIZE = 20;

}
