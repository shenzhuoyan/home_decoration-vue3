package com.home_decoration.back_end.baiguo.controller;

import com.home_decoration.back_end.baiguo.config.Result;
import com.home_decoration.back_end.baiguo.domain.Notice;
import com.home_decoration.back_end.baiguo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/noticeList")
    public Result notice(Integer page) {
        if (page == null) return Result.fail(400, "页码为空");
        if (page > 0)
            return Result.success(noticeService.getAll(page));
        return Result.fail(400, "页码不能为负数");
    }

    @RequestMapping("/noticeList/page")
    public Result noticePage() {
        return Result.success(noticeService.pageAll());
    }

    @RequestMapping("/admin/noticeList/add")
    public Result add(@RequestBody Notice notice) {
        return noticeService.add(notice) ? Result.success() : Result.fail(400, "失败");
    }

    @RequestMapping("/admin/noticeList/del")
    public Result del(Integer id) {
        if (id == null) return Result.fail(400, "id为空");
        return noticeService.del(id) ? Result.success() : Result.fail(400, "失败");
    }

    @RequestMapping("/admin/noticeList/important")
    public Result important(Integer id) {
        if (id == null) return Result.fail(400, "id为空");
        return noticeService.setUp(id) ? Result.success() : Result.fail(400, "失败");
    }

    @RequestMapping("/admin/noticeList/top")
    public Result top(Integer id) {
        if (id == null) return Result.fail(400, "id为空");
        return noticeService.setTop(id) ? Result.success() : Result.fail(400, "失败");
    }

    @RequestMapping("/admin/noticeList/resume")
    public Result resume(Integer id) {
        if (id == null) return Result.fail(400, "id为空");
        return noticeService.resume(id) ? Result.success() : Result.fail(400, "失败");
    }

//    @RequestMapping("/admin/noticeList/deletedPage")
//    public Result delPage() {
//        return Result.success(noticeService.pageDel());
//    }

    //    @RequestMapping("/admin/noticeList/deleted")
//    public Result deleted(int page) {
//        return Result.success(noticeService.getDel(page));
//    }
    @RequestMapping("/admin/noticeList/page")
    public Result adminPageNumber() {
        return Result.success(noticeService.adminGetPageNumber());
    }

    @RequestMapping("/admin/noticeList/all")
    public Result adminAll(Integer page) {
        if (page == null) return Result.fail(400, "页码为空");
        return Result.success(noticeService.adminGetAll(page));
    }

    @RequestMapping("/noticeDetail/get")
    public Result get(Integer id) {
        if (id == null) return Result.fail(400, "id为空");
        Notice notice = noticeService.get(id);
        return notice.getStatus() > 0 ? Result.success(notice) : Result.fail(400, "失败");
    }

    @RequestMapping("/noticeList/important")
    public Result important() {
        return Result.success(noticeService.important());
    }

    @RequestMapping("/noticeList/newest")
    public Result newest() {
        return Result.success(noticeService.getNew());
    }
}
