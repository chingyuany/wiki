package com.alanyang.wiki.controller;

import com.alanyang.wiki.req.DocQueryReq;
import com.alanyang.wiki.req.DocSaveReq;
import com.alanyang.wiki.resp.DocQueryResp;
import com.alanyang.wiki.resp.CommonResp;
import com.alanyang.wiki.resp.PageResp;
import com.alanyang.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

//返回字串 or json
@RestController
//下面所有的get post 的接口路徑都會加上這個前綴doc
@RequestMapping("/doc")
public class DocController {


    @Resource
    private DocService docService;


    @GetMapping("/list")
//    @valid  開啟驗證規則 在pagereq 裡面
    public CommonResp list(@Valid DocQueryReq req){
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();
        PageResp<DocQueryResp> list = docService.list(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/all")
    public CommonResp all(){
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all();
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
//    commonresp 回傳True而已 new出來一定是true
//    如果是Json格式post 一定要加上@RequestBody,如果是form post 就不需要
    public CommonResp save(@Valid @RequestBody DocSaveReq req){
        CommonResp resp = new CommonResp<>();
        docService.save(req);

        return resp;
    }
//    {}是指動態  pathvariable 可以獲取url上的參數
    @DeleteMapping ("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        docService.delete(id);
        return resp;
    }
}