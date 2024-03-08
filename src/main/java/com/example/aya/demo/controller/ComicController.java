package com.example.aya.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.aya.demo.common.GlobalConstants;
import com.example.aya.demo.dao.*;
import com.example.aya.demo.service.*;
import com.google.gson.JsonObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Aya
 */
@Controller
@RequestMapping("/comic")
public class ComicController {
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
    private ComicCollectService comicCollectService;
    @Autowired
    private ComicHistoryService comicHistoryService;


    @RequestMapping("/toComicShow")
    public String toComicShow(Model model,Long comicId){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        //comicId = 2L;
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        Comic comic = comicService.findComicById(comicId);
        Comic comicCopy = new Comic();
        BeanUtils.copyProperties(comic,comicCopy);
        ComicCollect comicCollect = comicCollectService.findComicCollectByUserIdAndComicId(userId, comicId);
        comicCopy.setAddress(addressService.findByIdReturnName(Long.valueOf(comic.getAddress())));
        comicCopy.setProgress(progressService.findByIdReturnName(Long.valueOf(comic.getProgress())));
        comicCopy.setClassfiy(classfiyService.findByIdReturnName(Long.valueOf(comic.getClassfiy())));
        ComicHistory comicHistory = comicHistoryService.findComicHistoryByUserIdAndComicId(userId, comicId);
        comicHistory = this.checkComicHistory(comicHistory,comic,userId);
        comicHistoryService.saveComicHistory(comicHistory);
        model.addAttribute("comic",comicCopy);
        model.addAttribute("comicCollect",comicCollect);
        model.addAttribute("comicHistory",comicHistory);

        return "/comicMainPage/comicShow";
    }
    @RequestMapping("/toClassfiy")
    public String toClassfiy(Model model,
     @RequestParam(value = "classfiyId",defaultValue = "0") Long classfiyId,
     @RequestParam(value = "addressId",defaultValue = "0") Long addressId,
     @RequestParam(value = "progressId",defaultValue = "0") Long progressId,
     @RequestParam(value = "pageNumber",defaultValue = "0") Integer pageNumber ){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        this.getAllClassifyAddressProgress(model);
        this.checkSelectedTagAndAdd(model,classfiyId,addressId,progressId);
        Comic comic = new Comic();
        comic.setClassfiy(Long.toString(classfiyId));
        comic.setAddress(Long.toString(addressId));
        comic.setProgress(Long.toString(progressId));
        Page<Comic> comicPage;
        if(pageNumber > 0){
            comicPage = comicService.findByCondition(pageNumber-1, comic);
        }else {
            comicPage = comicService.findByCondition(0, comic);
        }
        List<Comic> comicList = comicPage.getContent();
        model.addAttribute("comicList",comicList);
        model.addAttribute("page",comicPage);
        model.addAttribute("currentPage",pageNumber);
        return "/comicMainPage/classify";
    }
    @RequestMapping("/toSearch")
    public  String toSearch(Model model,String searchInfo,
    @RequestParam(value = "classfiyId",defaultValue = "0") Long classfiyId,
    @RequestParam(value = "addressId",defaultValue = "0") Long addressId,
    @RequestParam(value = "progressId",defaultValue = "0") Long progressId,
    @RequestParam(value = "pageNumber",defaultValue = "0") Integer pageNumber){
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        this.getAllClassifyAddressProgress(model);
        this.checkSelectedTagAndAdd(model,classfiyId,addressId,progressId);
        Comic comic = new Comic();
        if(searchInfo != null && !"".equals(searchInfo)){
            comic.setTitle(searchInfo);
        }
        comic.setClassfiy(Long.toString(classfiyId));
        comic.setAddress(Long.toString(addressId));
        comic.setProgress(Long.toString(progressId));
        Page<Comic> comicPage;
        if(pageNumber > 0){
            comicPage = comicService.findByCondition(pageNumber-1, comic);
        }else {
            comicPage = comicService.findByCondition(0, comic);
        }
        List<Comic> comicList = comicPage.getContent();
        model.addAttribute("searchInfo", searchInfo);
        model.addAttribute("comicList",comicList);
        model.addAttribute("page",comicPage);
        model.addAttribute("currentPage",pageNumber);
        return "/comicMainPage/search";
    }

    @RequestMapping("/toComicMain")
    public String toComicMain(Model model) {
        if (!checkIsLogin()){
            return "redirect:/user/toLogin";
        }
        List<Long> recomendComicId = JSON.parseArray(GlobalConstants.RECOMMEND_COMIC,Long.class);
        List<Long> carouselRecomendComicId = JSON.parseArray(GlobalConstants.CAROUSEL_RECOMMEND_COMIC,Long.class);
        List<Comic> recomendComic = new ArrayList<>();
        List<Comic> carouselRecomendComic = new ArrayList<>();
        List<Comic> rankComic = new ArrayList<>();
        for (Long id : recomendComicId){
            Comic comic = new Comic();
            comic = comicService.findComicById(id);
            recomendComic.add(comic);
        }
        for (Long id : carouselRecomendComicId){
            Comic comic = new Comic();
            comic = comicService.findComicById(id);
            carouselRecomendComic.add(comic);
        }
        List<Long> comicRankId = comicCollectService.getComicRank();
        for (Long id : comicRankId){
            Comic comic = new Comic();
            comic = comicService.findComicById(id);
            rankComic.add(comic);
        }
        model.addAttribute("comicList",recomendComic);
        model.addAttribute("carouselComicList",carouselRecomendComic);
        model.addAttribute("rankComicList",rankComic);
        this.getAllClassifyAddressProgress(model);
        return "/comicMainPage/comicMain";
    }

    @RequestMapping("/addComicCollect")
    @ResponseBody
    public String addComicCollect(Long comicId){
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute("userId");
        ComicCollect comicCollect = comicCollectService.findComicCollectByUserIdAndComicId(userId, comicId);
        JSONObject result = new JSONObject();
        if(comicCollect != null){
            comicCollectService.deleteComicCollectById(comicCollect.getId());
            result.put("msg", "取消收藏");
            result.put("isCollect", "收藏");
        }else {
            comicCollect = new ComicCollect();
            comicCollect.setComicId(comicId);
            comicCollect.setUserId(userId);
            comicCollectService.saveComicCollect(comicCollect);
            result.put("msg", "收藏成功");
            result.put("isCollect", "已收藏");
        }
        return result.toJSONString();
    }

    @RequestMapping("/updateHistory")
    @ResponseBody
    public String updateHistory(Long comicId, Long comicDetailId) {
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        ComicHistory comicHistory = null;
        Long userId = (Long)session.getAttribute("userId");
        if (comicId != null){
            comicHistory = comicHistoryService.findComicHistoryByUserIdAndComicId(userId, comicId);
            Comic comic = new Comic();
            comic.setId(comicId);
            if (comicDetailId != null){
                ComicDetail comicDetail = comicDetailService.findComicDetailByIdAndComicId(comicDetailId, comic);
                if (comicDetail!=null){
                    comicHistory.setUpdateTime(new Date());
                    comicHistory.setComicDetailId(comicDetailId);
                    result.put("msg", "历史添加成功");
                    result.put("comicHistory", comicHistory);
                    comicHistoryService.saveComicHistory(comicHistory);
                    return result.toJSONString();
                }
            }
        }
        result.put("msg", "历史添加失败");
        result.put("comicHistory", comicHistory);
        comicHistoryService.saveComicHistory(comicHistory);
        return result.toJSONString();
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
    public ComicHistory checkComicHistory(ComicHistory comicHistory,Comic comic,Long userId){
        if(comicHistory != null){
            comicHistory.setUpdateTime(new Date());
        }else {
            comicHistory = new ComicHistory(userId,comic.getId(),new Date());
            comicHistory.setUpdateTime(new Date());
        }
        Long comicDetailId = comicHistory.getComicDetailId();
        if (comicDetailId != null) {
            ComicDetail comicDetailByIdAndComicId = comicDetailService.findComicDetailByIdAndComicId(comicDetailId, comic);
            if (comicDetailByIdAndComicId != null){
                return comicHistory;
            }
        }
        List<ComicDetail> comicDetailList = comic.getComicDetailList();
        Long detailId = null;
        if (comicDetailList != null && comicDetailList.size()>0){
            detailId=comicDetailList.get(0).getId();
        }
        comicHistory.setComicDetailId(detailId);
        return comicHistory;
    }
    public void getAllClassifyAddressProgress(Model model){
        List<Classfiy> classfiyList = classfiyService.findAll();
        List<Address> addressList = addressService.findAll();
        List<Progress> progressesList = progressService.findAll();

        model.addAttribute("classfiyList",classfiyList);
        model.addAttribute("addressList",addressList);
        model.addAttribute("progressesList",progressesList);
    }
    public void checkSelectedTagAndAdd(Model model,Long classId,Long addressId,Long progressId){
        model.addAttribute("classId",classId);
        model.addAttribute("addressId",addressId);
        model.addAttribute("progressId",progressId);

    }
}
