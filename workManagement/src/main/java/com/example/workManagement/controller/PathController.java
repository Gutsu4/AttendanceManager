package com.example.workManagement.controller;


import com.example.workManagement.model.Attendance;
import com.example.workManagement.model.User;
import com.example.workManagement.repository.AttendanceManager;
import com.example.workManagement.repository.UserManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
public class PathController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AttendanceManager attendanceManager;

    @Autowired
    private UserManager userManager;

    @GetMapping("/")
    public String someMethod(@RequestParam(name = "continue", required = false) String continueParam,
                             @RequestParam(name = "error", required = false) String errorParam, Model model) {
        return "login";
    }

    @GetMapping("/login")
    public String index(Model model){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Model model,HttpServletRequest request){
        // セッションを無効にする
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "logout";
    }

    @GetMapping("/user/mainmenu")
    public String mainmenu(Model model, HttpServletRequest request){
       HttpSession session = request.getSession();
       int userId = (int) session.getAttribute("userId");

        // ModelにユーザIDを追加
        if (userId != 0) {
            model.addAttribute("user_id", session.getAttribute("userId"));
        }

        return "user/mainmenu";
    }

    @GetMapping("/user/attendance_in")
    public String attendance_in(Model model){
        return "user/attendance_in";
    }

    @GetMapping("/user/workplace_regist")
    public String workplace_regist(Model model){
        return "user/workplace_regist";
    }

    @GetMapping("/user/contact_regist")
    public String contact_regist(Model model){
        return "user/contact_regist";
    }

    @GetMapping("/user/attendance_all_view")
    public String attendance_all_view(Model model,HttpServletRequest request){
        //all workを実行
        List<Attendance> allwork = attendanceManager.getAllAttendance();
        model.addAttribute("allWork",allwork);
        return "user/attendance_all_view";
    }

    @GetMapping("/user/attendance_history_view")
    public String attendance_history_view(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("userId");

        if (userId != 0) {
            List<Attendance> idWork = attendanceManager.getAttendanceById(userId);
            model.addAttribute("idWork",idWork);
        }
        return "user/attendance_history_view";
    }

    @GetMapping("/admin/admin_menu")
    public String adminMainMenu(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        // ModelにユーザIDを追加
        if (userId != null) {
            model.addAttribute("user_id", session.getAttribute("userId"));
        }
        return "admin/admin_menu";
    }



    @GetMapping("/admin/admin_user_manager")
    public String admin_user_manager(Model model,HttpServletRequest request){
        List<User> users = userManager.getAllUser();
        model.addAttribute("users",users);
        return "admin/admin_user_manager";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") String user_id, Model model){
        User user = userManager.getUserById(user_id);
        model.addAttribute("user",user);
        return "admin/user_management/edit";
    }

    @PostMapping("/admin/add")
    public String addUserPost(Model model,@ModelAttribute("user") User user){

        return "admin/admin_menu";
    }

    @PostMapping("/admin/edit/{id}")
    public String editUserPost(@PathVariable("id") String user_id, Model model){
        return "admin/user_management/edit";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model){
        return "admin/user_management/add";
    }

    @GetMapping("/admin/admin_workplace_manager")
    public String admin_workplace_manager(Model model,HttpServletRequest request){
        return "admin/admin_workplace_manager";
    }

    @GetMapping("/admin/admin_attendance_total_hours")
    public String admin_attendance_total_hours(Model model,HttpServletRequest request){
        return "admin/admin_attendance_total_hours";
    }
}
