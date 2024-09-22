package com.fczx.tq.controller;

import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.fczx.tq.common.BaseResponse;
import com.fczx.tq.common.ErrorCode;
import com.fczx.tq.common.ResultUtils;
import com.fczx.tq.exception.BusinessException;
import com.fczx.tq.model.entity.User;
import com.fczx.tq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/file")
@Slf4j
public class UploadController {
    @Value("${file.request}")
    private String prefix;
    @Value("${file.uploadFolder}")
    private String basePath;
    @Resource
    UserService userService;
    @PostMapping("/upload")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"图片为空");
        }

        if (request==null)
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"数据为空");
        }
        long size = file.getSize();
        long two_MB=2*1024*1024L;
        if (size>two_MB)
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"图片不能超过2M");
        }

        User userVO = userService.getLoginUser(request);
        if (userVO==null){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"用户不存在");
        }
        Long id = userVO.getId();
        String originalFilename = file.getOriginalFilename();//获取文件名称
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());//生成文件名称前缀
        assert originalFilename != null;
        String suffix = FileUtil.getSuffix(originalFilename);
        List<String>  vaildSuffix= Arrays.asList("jpg","png");
        if (!vaildSuffix.contains(suffix))
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"上传图片类型不符合");
        }
        String fileName = fileNamePrefix +"."+ suffix;//文件名
        String url=prefix+fileName;
        File imageFolder = new File(basePath);
        File f = new File(imageFolder, fileName);
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("id",id);
            User user =new User();

            user.setUserAvatar(url);
            boolean update = userService.update(user, queryWrapper);
            if (!update)
            {
                throw new BusinessException(ErrorCode.OPERATION_ERROR,"上传失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtils.error(ErrorCode.OPERATION_ERROR,"上传失败");
        }


        return ResultUtils.success(url);
    }

}
