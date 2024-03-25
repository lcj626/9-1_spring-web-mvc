package com.ohgiraffers.fileupload;

import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileuploadController {

    @Resource
    private ResourceLoader resourceLoader;

    @RequestMapping(value = {"/", "main"})
    public String index(){
        return "main";
    }

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model) throws IOException {
        System.out.println("single file : " + singleFile);
        System.out.println("원본 파일 이름 : " + singleFile.getOriginalFilename());
        System.out.println("파일 이름(input name) : " + singleFile.getName());
        //System.out.println("원본 파일 객체 : " + singleFile.getBytes());
        System.out.println("원본 파일 사이즈 : " + singleFile.getSize());
        System.out.println("singleFileDescription : " + singleFileDescription);

        //파일을 저장할 겨올 설정
//        String root = "c:/upload-files";
//        String filePath = root + "/single";

        String filePath = resourceLoader.getResource("classpath:static/img/").getFile().getAbsolutePath();

        File dir = new File(filePath);
        //System.out.println(dir.getAbsolutePath());

        if(!dir.exists()){
            dir.mkdirs(); // 경로가 존재하지 않는다면 이 경로에다 만들겠다
        }

        String originFileName = singleFile.getOriginalFilename();// 파일 원본 이름 가져옴
        String ext = originFileName.substring(originFileName.lastIndexOf("."));// 마지막 인덱스 "." 기준으로 나눔(ex) xx.ext xx와 ext(확장자 분리)
        String savedName = UUID.randomUUID().toString().replace("-","")+ext;//UUID 랜덤된 값을 중복되지 않게(파일이름저장할때) 저장하기 위함, "-"를 빈공간""로 대체

        try{
            System.out.println("filePath ================================ " + filePath);
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message", "파일 업로드 성공");
            model.addAttribute("img", "static/img/" + savedName); // 이걸 이제 db에 저장
        }catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message", "파일 업로드 실패");
        }

        return "result";
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multipartFiles, String multiFileDescription, Model model){
        System.out.println("multiFiles : " + multipartFiles);
        System.out.println("multiFileDescription : " + multiFileDescription);

        String root = "c:/upload-files";
        String filePath = root + "/multi";

        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs(); // 해당 경로에 폴더가 없으면 폴더를 만들어 줘라 -> 폴더를 다수 만들 경우 s 붙이고 상위 폴더가 존재하는 경우 dir.mkdir();
        }

        List<FileDTO> files = new ArrayList<>();


            try {
                for (MultipartFile file: multipartFiles) {
                    String originFileName = file.getOriginalFilename();
                    String ext = originFileName.substring(originFileName.lastIndexOf("."));
                    String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
                    files.add(new FileDTO(originFileName, savedName, filePath, multiFileDescription));
                    file.transferTo(new File(filePath, "/" + savedName));
                }
                model.addAttribute("message", "다중 파일 업로드 성공");
            } catch (IOException e) {
                e.printStackTrace();

                for (FileDTO file: files) {
                    new File(filePath + "/" + file.getSavedName()).delete();
                }
                model.addAttribute("message", "다중 파일 업로드 실패");
            }
            return "result";
        }
    }


