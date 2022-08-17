package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(OutgoingCategoryController.OUTGOING_CATEGORY_CONTROLLER)
public interface OutgoingCategoryController {

    String OUTGOING_CATEGORY_CONTROLLER = AppConstant.BASE_PATH + "/outgoingCategory";

}
