package edu.whut.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/upload")
public class UploadController {

    // 从配置文件中读取上传目录
    @Value("${upload.directory}")
    private String uploadDirectory;

    @PostMapping("/image")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        // 判断文件是否为空
        if (file.isEmpty()) {
            response.put("status", "error");
            response.put("message", "上传的文件为空");
            return response;
        }

        // 获取文件名并生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        // 目标文件
        File destinationFile = new File(uploadDirectory + File.separator + uniqueFileName);

        try {
            // 创建目录（如果不存在）
            destinationFile.getParentFile().mkdirs();

            // 保存文件
            file.transferTo(destinationFile);

            // 返回响应，图片通过 `http://localhost:8080/uploads/<文件名>` 访问
            response.put("status", "success");
            response.put("message", "图片上传成功");
            response.put("url", "/uploads/" + uniqueFileName);
        } catch (IOException e) {
            response.put("status", "error");
            response.put("message", "图片上传失败：" + e.getMessage());
        }

        return response;
    }
}
