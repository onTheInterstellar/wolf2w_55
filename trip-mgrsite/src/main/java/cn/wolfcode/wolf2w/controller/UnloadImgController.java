package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.service.IStrategyService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@ConfigurationProperties(prefix = "image")

public class UnloadImgController {
    private String path;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Object uploadImg(MultipartFile pic) throws IOException {


        String filename = pic.getResource().getFilename();
        InputStream inputStream = pic.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(this.path + filename);
        IOUtils.copy(inputStream, outputStream);

        return "/images/upload/" + filename;
    }

}
