package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.dao.*;
import com.example.aya.demo.service.*;
import com.example.aya.demo.util.CheckUtil;
import com.example.aya.demo.util.QiNiuUtil;
import com.google.gson.JsonObject;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aya
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ClassfiyService classfiyService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProgressService progressService;
    @Autowired
    private ComicService comicService;
    @Autowired
    private ComicDetailService comicDetailService;
    @Autowired
    private UpComicService upComicService;
    @Autowired
    private ComicCollectService comicCollectService;
    @Autowired
    private ComicHistoryService comicHistoryService;
    @Autowired
    private UserService userService;


    @RequestMapping("/toUploadImgFile")
    public String toUploadImgFile(Model model, Long comicId) {
        if (!checkIsLogin()) {
            return "redirect:/user/toLogin";
        }
        Comic comicById = new Comic();
        if (comicId != null) {
            comicById = comicService.findComicById(comicId);
        }
        model.addAttribute("comic",comicById);
        this.getAllClassifyAddressProgress(model);
        return "/userManage/comicUpload";
    }
    @RequestMapping("/toUploadManage")
    public String toUploadManage(Model model,Integer pageNumber){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Pageable pageable=null ;
        if(pageNumber != null){
            pageable = PageRequest.of(pageNumber - 1, 1, Sort.by(Sort.Direction.ASC, "id"));
            model.addAttribute("currentPage",pageNumber);
        }else {
            model.addAttribute("currentPage",1);
            pageable = PageRequest.of(0,1, Sort.by(Sort.Direction.ASC,"id"));
        }
        Page<UpComic> upComicPage = upComicService.findUpComicByUserId(userId, pageable);
        List<UpComic> upComicList = upComicPage.getContent();
        List<Comic> comicList = new ArrayList<>();
        if(upComicList != null && upComicList.size()>0){
            for (UpComic upComic : upComicList) {
                Long comicId = upComic.getComicId();
                Comic comicById = comicService.findComicById(comicId);
                comicList.add(comicById);
            }
        }
        model.addAttribute("comicList",comicList);
        model.addAttribute("page",upComicPage);
        return "/userManage/comicUploadManage";
    }
    @RequestMapping("/toComicCollect")
    public String toComicCollect(Model model,@RequestParam(value = "pageNumber",defaultValue = "0") Integer pageNumber){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Page<ComicCollect> comicCollect =null;
        if(pageNumber > 0){
            comicCollect = comicCollectService.findByUserId(pageNumber-1, userId);
        }else {
            comicCollect = comicCollectService.findByUserId(0, userId);
        }
        List<ComicCollect> comicCollectList = comicCollect.getContent();
        List<Comic> comicList = new ArrayList<>();
        if (comicCollectList!=null&&comicCollectList.size()>0){
            for (ComicCollect collect:comicCollectList){
                Long comicId = collect.getComicId();
                Comic comicById = comicService.findComicById(comicId);
                comicList.add(comicById);
            }
        }
        this.getAllClassifyAddressProgress(model);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("comicList",comicList);
        model.addAttribute("page",comicCollect);
        return "/userManage/comicCollect";
    }
    @RequestMapping("/toComicHistory")
    public String toComicHistory(Model model,@RequestParam(value = "pageNumber",defaultValue = "0") Integer pageNumber){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Page<ComicHistory> comicHistory =null;
        if(pageNumber > 0){
            comicHistory = comicHistoryService.findByUserId(userId,pageNumber-1);
        }else {
            comicHistory = comicHistoryService.findByUserId(userId,0);
        }
        List<ComicHistory> comicHistoryList = comicHistory.getContent();
        List<Comic> comicList = new ArrayList<>();
        if (comicHistoryList!=null&&comicHistoryList.size()>0){
            for (ComicHistory history:comicHistoryList){
                Long comicId = history.getComicId();
                Comic comicById = comicService.findComicById(comicId);
                comicList.add(comicById);
            }
        }
        this.getAllClassifyAddressProgress(model);
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("comicList",comicList);
        model.addAttribute("page",comicHistory);
        return "/userManage/comicHistory";
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo(Model model) {
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        User user = userService.findUserById(userId);
        model.addAttribute("user",user);
        return "/userManage/userInfo";
    }
    @ResponseBody
    @RequestMapping(value = "/modifyUserInfo")
    public String modifyUserInfo(Model model,String email,String phone){
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        if (userId!=null){
        }else {
            JSONObject result = new JSONObject();
            result.put("status","error");
            result.put("msg","无法获取用户id");
            return result.toJSONString();
        }
        User user = userService.findUserById(userId);
        if (!CheckUtil.isMobileNO(phone)){
            JSONObject result = new JSONObject();
            result.put("status","error");
            result.put("msg","非法手机号");
            return result.toJSONString();
        }
        user.setPhone(phone);
        user.setEmail(email);
        userService.modify(user);
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("msg","修改成功");
        return result.toJSONString();
    }
    @ResponseBody
    @RequestMapping("/deleteUpComic")
    public String deleteUpComic(Long comicId){
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        if (userId!=null){
        }else {
            JSONObject result = new JSONObject();
            result.put("status","error");
            result.put("msg","无法获取用户id");
            return result.toJSONString();
        }
        if (comicId!=null){
        }else {
            JSONObject result = new JSONObject();
            result.put("status","error");
            result.put("msg","无法获取漫画id");
            return result.toJSONString();
        }
        upComicService.deleteUpComic(comicId,userId);
        Comic comic = comicService.findComicById(comicId);
        comicDetailService.deleteComicDetailByComicId(comic);
        comicHistoryService.deleteCOmicHistoryByComicId(comicId);
        comicCollectService.deleteComicCollectByComicId(comicId);
        comicService.deleteComicById(comicId);
        JSONObject result = new JSONObject();
        result.put("status","success");
        result.put("msg","success");
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/upFile",method= RequestMethod.POST)
    public String upFile(@RequestParam("file_data") MultipartFile[] files)throws Exception{
        if (files!=null && files.length>0){}
        else {
            return "";
        }
        String fileId = request.getParameter("fileId");
        List<String> urls = new ArrayList<>();
        for (MultipartFile file:files) {
            byte[] bytes = file.getBytes();
            String fileUrl = QiNiuUtil.upload(bytes);
            urls.add(fileUrl);
        }
        JSONObject result = new JSONObject();
        result.put("msg","success");
        result.put("fileUrls",JSONObject.toJSONString(urls));
        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/addComic", method = RequestMethod.POST)
    public String addComic(Comic comic, String comicJson) {
        //漫画添加
        boolean isNew = true;
        if(comic.getId()!= null){
            isNew =false;
            Comic comicById = comicService.findComicById(comic.getId());
            if (comicById!= null){
                comic.setCreateTime(comicById.getCreateTime());
            }else {
                comic.setCreateTime(new Date());
            }
        }else {
            comic.setCreateTime(new Date());
        }
        comic.setUpdateTime(new Date());
        Comic savedComic = comicService.saveComic(comic);
        System.out.println(savedComic.getId());
        //漫画详细添加
        JSONArray comicJsonList = JSONObject.parseArray(comicJson);
        for (int i = 0; i < comicJsonList.size(); i++) {
            ComicDetail comicDetail = comicJsonList.getObject(i, ComicDetail.class);
            if (comicDetail.getId()!=null){
                ComicDetail comicDetailByIdAndComicId = comicDetailService.findComicDetailByIdAndComicId(comic.getId(), comic);
                if (comicDetailByIdAndComicId != null ){
                    comicDetail.setCreateTime(comicDetailByIdAndComicId.getCreateTime());
                }
            }else {
                comicDetail.setCreateTime(new Date());
            }
            comicDetail.setComicId(savedComic);
            comicDetailService.saveComicDetail(comicDetail);
        }
        //up漫画联系添加
        if(!isNew){
        }else {
            HttpSession session = request.getSession();
            Long userId = (Long)session.getAttribute("userId");
            Long comicId = savedComic.getId();
            UpComic upComic = new UpComic(userId,comicId);
            upComicService.saveUpComicService(upComic);
        }
        JSONObject result = new JSONObject();
        result.put("msg", "上传成功");
        result.put("data", "ajaxReturn");
        return result.toJSONString();
    }
    @ResponseBody
    @RequestMapping(value = "/deleteComicDetail", method = RequestMethod.POST)
    public String  deleteComicDetail(Long comicDetailId){
        if (comicDetailId != null){
            comicDetailService.deleteComicDetailById(comicDetailId);
        }
        JSONObject result = new JSONObject();
        result.put("msg", "删除成功");
        result.put("data", "ajaxReturn");
        return result.toJSONString();
    }

    public void getAllClassifyAddressProgress(Model model){
        List<Classfiy> classfiyList = classfiyService.findAll();
        List<Address> addressList = addressService.findAll();
        List<Progress> progressesList = progressService.findAll();
        model.addAttribute("classfiyList",classfiyList);
        model.addAttribute("addressList",addressList);
        model.addAttribute("progressesList",progressesList);
    }

    public boolean checkIsLogin(){
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        Long id = (Long) userId;
        if(id!=null){
            return true;
        }
        return false;
    }
}
