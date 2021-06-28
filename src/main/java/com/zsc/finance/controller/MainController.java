package com.zsc.finance.controller;

import com.zsc.finance.entity.Admin;
import com.zsc.finance.entity.News;
import com.zsc.finance.entity.User;
import com.zsc.finance.service.AdminService;
import com.zsc.finance.service.NewsService;
import com.zsc.finance.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    NewsService newsService;

    /**
     * 404页面
     */
    @GetMapping(value = "/error/404")
    public String error_404() {
        return "error/404";
    }

    /**
     * 500页面
     */
    @GetMapping(value = "/error/500")
    public String error_500() {
        return "error/500";
    }

    /**
     * 首页
     * @param session
     * @return
     */
    @GetMapping("/")
    public String Index(HttpSession session){
        //TODO (用户和管理员同时登陆)
        if (session.getAttribute("loginUser") !=null &&session.getAttribute("loginAdmin")!=null){
            return "redirect:/logout";
        }

        if (session.getAttribute("loginUser")!=null){
            return "redirect:/user";
        }
        if (session.getAttribute("loginAdmin")!=null){
            return "redirect:/admin";
        }
        return "redirect:/login";
    }

    /**
     * 错误界面返回
     * @param session
     * @return
     */
    @GetMapping("/index")
    public String toIndex(HttpSession session){

        //TODO (用户和管理员同时登陆)
        if (session.getAttribute("loginUser") !=null &&session.getAttribute("loginAdmin")!=null){
            return "redirect:/logout";
        }

        if (session.getAttribute("loginUser")!=null){
            return "redirect:/user";
        }
        if (session.getAttribute("loginAdmin")!=null){
            return "redirect:/admin";
        }
        return "redirect:/login";
    }

    /**
     * 管理员首页
     * @param model
     * @return
     */
    @GetMapping("/admin")
    public String toAdminIndex(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               Model model){
        // 引入PageHelper插件，在查询之前调用startPage方法，传入页码以及每页大小
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userService.selectUserByStatusDesc();
        // 使用PageInfo包装查询后的结果，并交给页面处理
        // PageInfo封装了详细的分页信息，包括我们查询出来的数据，还可以传入连续显示的页数（5）
        PageInfo<User> pageInfo = new PageInfo<User>(list, 5);
        model.addAttribute("userPageInfo",pageInfo);
        model.addAttribute("userList",list);

        model.addAttribute("pageTopBarInfo","系统首页");
        model.addAttribute("activeUrl","indexActive");
        return "admin/main";
    }

    /**
     * 用户首页
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String toUserIndex(Model model){
        List<News> list = newsService.selectAllNews();

        model.addAttribute("newsList",list);
        model.addAttribute("pageTopBarInfo","系统首页");
        model.addAttribute("activeUrl","indexActive");
        return "user/main";
    }
}
