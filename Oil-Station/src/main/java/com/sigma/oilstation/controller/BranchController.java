package com.sigma.oilstation.controller;

import com.sigma.oilstation.utils.AppConstant;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(BranchController.BRANCH_CONTROLLER)
public interface BranchController {

    String BRANCH_CONTROLLER = AppConstant.BASE_PATH + "/branch";

}
