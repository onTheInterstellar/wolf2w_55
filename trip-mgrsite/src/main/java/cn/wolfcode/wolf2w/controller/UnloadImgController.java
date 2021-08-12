package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@ConfigurationProperties(prefix = "image")

public class UnloadImgController {
    private String path;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Object uploadImg(MultipartFile pic) throws Exception {
        return UploadUtil.uploadAli(pic);
    }

    @RequestMapping("/uploadImg_ck")
    @ResponseBody
    public Object uploadImg_ck(MultipartFile upload) throws Exception {

        /*    "uploaded": 1,
              "fileName": "foo.jpg",
              "url": "/files/foo.jpg"*/

        Map<String, Object> info = new HashMap();
        info.put("uploaded", 1);
        info.put("fileName", null);
        info.put("url", UploadUtil.uploadAli(upload));

        return info;
    }

}
