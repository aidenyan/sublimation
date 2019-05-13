package com.jimmy.sublimation.controller;

import com.jimmy.sublimation.dto.TreasureInfoDto;
import com.jimmy.sublimation.validate.anno.NotEmpty;
import com.jimmy.sublimation.validate.anno.NotNull;
import com.jimmy.sublimation.validate.anno.ParamValidate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/5/10/010.
 */
@Api(value = "test", tags = "TestController", description = "��������")
@Controller
public class TestController {

    @PostMapping("/set_info")
    @ResponseBody
    @ApiOperation("����������Ϣ����")
    @ParamValidate
    public String set(@NotNull("dsfdsfdssfds") @RequestBody TreasureInfoDto treasureInfoDto) {
        return "set_info";
    }

    @PostMapping("/send_sms")
    @ResponseBody
    @ApiOperation("���͵�¼�Ķ�����Ϣ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "�ֻ�����", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "source", value = "��Դ", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "deviceId", value = "������", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sysToken", value = "sysToken", paramType = "header", required = true, dataType = "String")
    })
    @ParamValidate
    public String sendMobile(@NotEmpty("sdsadsa") String[] mobile, String source, String deviceId, @RequestHeader(value = "sysToken") String sysToken) {
        return "set_info";

    }
}
