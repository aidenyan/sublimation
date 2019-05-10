package com.jimmy.controller;

import com.jimmy.dto.TreasureInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/5/10/010.
 */
@Api(value = "test", tags = "TestController", description = "测试类型")
@Controller
public class TestController {

    @PostMapping("/set_info")
    @ResponseBody
    @ApiOperation("宝藏类型信息设置")
    public String set(@RequestBody TreasureInfoDto treasureInfoDto) {
        return "set_info";
    }
}
