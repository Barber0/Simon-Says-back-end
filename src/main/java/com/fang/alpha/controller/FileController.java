package com.fang.alpha.controller;

import com.alibaba.fastjson.JSON;
import com.fang.alpha.bean.Response;
import com.fang.alpha.dao.User;
import com.fang.alpha.dao.Video;
import com.fang.alpha.dao.VideoSort;
import com.fang.alpha.exception.FileException;
import com.fang.alpha.repository.UserRepository;
import com.fang.alpha.repository.VideoRepository;
import com.fang.alpha.repository.VideoSortRepository;
import com.fang.alpha.utils.Encrypt;
import com.fang.alpha.utils.FileUtil;
import com.fang.alpha.utils.VideoBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/file/")
@CrossOrigin
public class FileController {
    @Value("${project.storePath}")
    private String storePath;

    @Value("${project.keyName}")
    private String keyName;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoSortRepository videoSortRepository;

    @RequestMapping(value = "/upload/{type}/")
    public Response upload(
            @PathVariable("type") String fileType,
            @RequestParam("file")MultipartFile file){
        int type = -1;
        if (fileType.equals(FileUtil.Img)) type = FileUtil.ImgType;
        else if (fileType.equals(FileUtil.Video)) type = FileUtil.VideoType;

        String location = FileUtil.upload(file,(new File("").getAbsolutePath())+storePath,type);
//        String location = FileUtil.upload(file,temp+"/static/",type);

        if (location.equals("")) throw new FileException(FileException.UploadFailed,"文件上传失败");
        else return new Response(200,location,"suc");
    }

    @GetMapping(value = "/video/{id}")
    public Response video(@PathVariable("id") String id){
        Object[] result = new Object[2];
        VideoSort videoSort = videoSortRepository.findById(Integer.parseInt(id));
        videoRepository.watchVideo(Integer.parseInt(id));
        User user = userRepository.fetchUserById(videoSort.getUid());
        result[0] = videoSort;
        result[1] = user;
        return new Response(200,result,"suc");
    }

    @GetMapping(value = "/video/all/{type}")
    public Response vdList(
            @PathVariable("type") String type,
            @RequestParam("page") int page,
            @RequestParam("size") int size){
        Page<VideoSort> videoSorts = videoSortRepository.findAll(PageRequest.of(page-1,size, Sort.Direction.DESC,type));
        return new Response(200,videoSorts,"suc");
    }

    @GetMapping(value = "/vdpage/")
    public Response vdpage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            HttpServletRequest request){
        HttpSession session = request.getSession();
        String uname = Encrypt.getUnameFromKey((String)session.getAttribute(keyName));
        User user = userRepository.fetchUserByUsername(uname);
        Page<VideoSort> videoSorts = videoSortRepository.findByUid(user.getId(), PageRequest.of(page-1,size, Sort.Direction.DESC,"createAt"));
        if (videoSorts==null) throw new FileException(FileException.NoSuchResult,"找不到相应资源");
        return new Response(200,videoSorts,"suc");
    }

    @PutMapping(value = "/view/{id}")
    public Response view(@PathVariable("id") String id){
        int row = videoRepository.watchVideo(Integer.parseInt(id));
        if (row>0) return new Response(200,"统计成功");
        return new Response(-666,"更改失败");
    }

    @PostMapping(value = "/video/")
    public Response addVideo(@RequestBody Video videoBody,HttpServletRequest request){
        Video video = new Video();
        video.setName(videoBody.getName());
        video.setDescription(videoBody.getDescription());

        video.setUrl(videoBody.getUrl());
        video.setCover(videoBody.getCover());
//        video.setUrl("/static"+videoBody.getUrl());
//        video.setCover("/static"+videoBody.getCover());
        video.setView(0);

        HttpSession session = request.getSession();
        User user = userRepository.findByUsername(Encrypt.getUnameFromKey((String) session.getAttribute(keyName)));
        video.setUpper(user.getId());
        video.setSort(videoBody.getSort());
        video.setCreateAt(new Timestamp(System.currentTimeMillis()));
        if (videoRepository.save(video)==null) return new Response(-666,"添加失败");
        return new Response(200,video,"suc");
    }

    @PutMapping(value = "/video/")
    public Response updateVideo(@RequestBody Video video){
        int row = videoRepository.updateVideo(video.getId(),video.getName(),video.getDescription(),video.getSort());
        if (row>0) return new Response(200,video,"suc");
            else throw new FileException(FileException.UpdateFailed,"无法更改视频信息");
    }

    @DeleteMapping(value = "/video/")
    public Response deleteVideo(@RequestParam("id") int id){
        int row = videoRepository.deleteById(id);
        if (row>0){
            return new Response(200,"删除成功");
        }
        return new Response(FileException.DeleteFailed,row,"删除失败");
    }
}
