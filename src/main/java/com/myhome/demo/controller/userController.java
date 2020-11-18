package com.myhome.demo.controller;

import com.myhome.demo.domain.*;
import com.myhome.demo.service.*;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private DormTotalService dormTotalService;

    @Autowired
    private StudentService stuService;
    @Autowired
    private DormBedInfoService dormBedInfoService;
    @Autowired
    private StuBedService stuBedService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping({"", "/login"})
    public String home() {
        return "login";
    }

    @RequestMapping({"/logout"})
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping("/toLoginPage")
    public String index(Model model) {
        List<DormTotalInfo> dormTotalInfoList = dormTotalService.searchAllJdbcDormTotal();
//        for (DormTotalInfo d : dormTotalInfoList)
//            System.out.println(d.getDormId());
        model.addAttribute("dormList", dormTotalInfoList);
        return "index";
    }



    @PostMapping(value = "/dologin")
    public String dologin(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map,
                          HttpSession session, Model model) {
//
//        //验证用户名和密码，输入正确，跳转到index.html
        String sql = "SELECT user,pwd,role_id FROM users WHERE user = ?";


        // 通过jdbcTemplate查询数据库

        Map<String, Object> userList = jdbcTemplate.queryForMap(sql, new Object[]{username});
//        System.out.println(userList);
//        System.out.println(userList.get("role_id"));
        if (userList.size() > 0 && userList.get("pwd").equals(password)) {
            //登陆成功
            session.setAttribute("username", new User(username, password, (int)(long)userList.get("role_id")));
            System.out.println("--- " + username + " login ---");

            return "redirect:/toLoginPage";
        } else {
            session.invalidate();
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }

    //显示用户
    @RequestMapping(value = "/memberList")
    public String memberList(Model model) {
        String sql = "SELECT user,pwd,role_id FROM users ORDER BY role_id";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUser(rs.getString("user"));
                user.setPwd(rs.getString("pwd"));
                user.setRole_id(rs.getInt("role_id"));
                return user;
            }
        });
//        for (User u : userList) {
//            System.out.println(u.getUser());
//            System.out.println(u.getPwd());
//            System.out.println(u.getRole_id());
//        }
        model.addAttribute("userList", userList);
        model.addAttribute("num", userList.size());
        return "member-list";
    }

    //访问修改密码页面
    @RequestMapping(value = "/pwdChangePage")
    public String pwdChange() {
        return "member-password";
    }


    //访问添加用户页面
    @RequestMapping(value = "/memberAddPage")
    public String memberAddPage() {
        return "member-add";
    }    //访问添加用户页面

    @RequestMapping(value = "/stuAddPage")
    public String stuAddPage() {
        return "student-add";
    }


    //访问删除用户页面
    @RequestMapping(value = "/memberDelPage")
    public String memberDelPage() {
        return "member-del";
    }

    //访问添加用户页面
    @RequestMapping(value = "/memberLevelPage")
    public String memberLevelPage() {
        return "member-level";
    }

    //访问添加用户页面
    @RequestMapping(value = "/memberViewPage")
    public String memberViewPage() {
        return "member-view";
    }    //访问添加用户页面


    //修改密码
    @PostMapping(value = "/pwdChange")
    public String pwdChange(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
                            Model model, HttpSession session) {
        String res = "", result = "1";
        String username = (String) session.getAttribute("username");
        User u = userService.searchJdbcUser(username);
        if (u.getPwd() == oldpass) {
//            System.out.println(username + " " + oldpass + " " + newpass);
            userService.updateJdbcUser(username, newpass);
            res = "修改成功！";
            result = "1";
        } else {
            res = "密码错误！";
            result = "0";
        }
        model.addAttribute("resultMsg", res);
        model.addAttribute("result", result);
        return "errorPage";
    }

    //添加用户
    @PostMapping(value = "/memberAdd")
    public String memberAdd(@RequestParam("username") String username, @RequestParam("pwd") String pwd, @RequestParam("role") String role,
                            Model model) {
        String res = "", result = "";
        User user = userService.searchJdbcUser(username);
//        System.out.println("-----" + user.getPwd());
        if (user.getPwd() != "") {

            res = "不允许重名用户";
            result = "3";
            model.addAttribute("resultMsg", res);
            model.addAttribute("result", result);
            return "errorPage";
        }
        res = "添加成功";
        result = "3";
        model.addAttribute("resultMsg", res);
        model.addAttribute("result", result);
        userService.insertJdbcUser(username, pwd);
        return "errorPage";
    }


    //编辑用户页面
    @RequestMapping(value = "/memberEditPage")
    public String memberEditPage(Model model) {


        return "member-edit";
    }

    //修改用户信息
    @PostMapping(value = "/memberEdit")
    public String memberEdit(@RequestParam("role") int role_id, @RequestParam("username") String username,
                             @RequestParam("new_pwd") String pwd, Model model) {
        System.out.println(username + " " + pwd + " " + role_id + " update");
        userService.updateJdbcUser(username, pwd, role_id);
        return "member-edit";
    }

    //删除用户
    @PostMapping(value = "/memberDel")
    public String memberDel(@RequestParam("username") String username, Model model) {
        System.out.println(username + " delete");
        userService.deleteJdbcUser(username);
        return "redirect:/memberList";
    }

    //显示用户
    @RequestMapping(value = "/studentList")
    public String stuList(Model model) {
        List<Student> stuList = stuService.searchAllJdbcStu();
//        for (Student u : stuList) {
//            System.out.println(u.getStu_id() + " ");
//        }

        model.addAttribute("stuList", stuList);
        model.addAttribute("num", stuList.size());
        return "student-list";
    }


    //添加用户
    @PostMapping(value = "/stuAdd")
    public String stuAdd(@RequestParam("stuId") int stuId, @RequestParam("pwd") String pwd, Model model) {
        String res = "", result = "";
//        System.out.println("stuId: " + stuId);
        Student stu = stuService.searchJdbcStu(stuId);
//        System.out.println("-----" + stu.getStu_id());
        if (stu.getStu_id() != -1) {
            res = "该学生已在系统中";
            result = "4";
            model.addAttribute("resultMsg", res);
            model.addAttribute("result", result);
            return "errorPage";
        }
        res = "添加成功";
        result = "4";
        model.addAttribute("resultMsg", res);
        model.addAttribute("result", result);

        stuService.insertJdbcStu(stuId, pwd);
        return "errorPage";
    }

    //删除学生
    @PostMapping(value = "/stuDel")
    public String stuDel(@RequestParam("stu_id") int stu_id, Model model) {
        System.out.println(stu_id + " delete");

        stuService.deleteJdbcStu(stu_id);
        return "redirect:/studentList";
    }

    //修改学生信息
    @PostMapping(value = "/stuEdit")
    public String memberEdit(@RequestParam("stu_id") int stu_id,
                             @RequestParam("new_pwd") String pwd, Model model) {
        System.out.println(stu_id + " " + pwd + " " + " update");
        stuService.updateJdbcStu(stu_id, pwd);
        return "student-edit";
    }

    //显示宿舍情况
    @RequestMapping(value = "/dormListPage")
    public String dormListPage(Model model) {
        List<DormTotalInfo> dormTotalInfoList = dormTotalService.searchAllJdbcDormTotal();
//        for (DormTotalInfo d : dormTotalInfoList)
//            System.out.println(d.getDormId());
        model.addAttribute("dormList", dormTotalInfoList);
        model.addAttribute("numOfDorm", dormTotalInfoList.size());
        return "dorm-list";
    }


    //显示宿舍情况
    @RequestMapping(value = "/dormAddPage")
    public String dormAddPage(Model model) {
        return "dorm-add";
    }

    //删除宿舍
    @PostMapping(value = "/dormDel")
    public String dormDel(@RequestParam("dorm_id") int dorm_id, Model model) {
        System.out.println(dorm_id + " delete");

        dormTotalService.deleteJdbcDormTotal(dorm_id);
//        dormBedInfoService.deleteJdbcDormBedTotal(dorm_id);
        return "redirect:/dormListPage";
    }

    //添加宿舍楼
    @PostMapping(value = "/dormAdd")
    public String dormAdd(@RequestParam("dorm_id") int dorm_id, @RequestParam("layer") int layer,
                          @RequestParam("roomPerLayer") int roomPerLayer, @RequestParam("bedPerRoom") int bedPerRoom,
                          Model model) {
        String res = "", result = "";

        DormTotalInfo dorm = dormTotalService.searchJdbcDormTotal(dorm_id);

        if (dorm.getLayers() != -1) {
            res = "该宿舍楼已在系统中";
            result = "5";
            model.addAttribute("resultMsg", res);
            model.addAttribute("result", result);
            return "errorPage";
        }
        res = "添加成功";
        result = "5";
        model.addAttribute("resultMsg", res);
        model.addAttribute("result", result);

        dormTotalService.insertJdbcDormTotal(dorm_id, layer, roomPerLayer, bedPerRoom, 0);

        return "errorPage";
    }


    //显示寝室情况
    @RequestMapping(value = "/bedAllocationList")
    public String bedAllocationList(Model model) {
        List<Student> stuList = stuService.searchAllJdbcStu();
        List<StudentBed> stuBedList = new ArrayList<StudentBed>();
        List<StuWithBedIno> stuWithBedInfoList = new ArrayList<>();
        int n = 0;
        for(Student student: stuList) {
            StudentBed temp = stuBedService.searchJdbcStuBed(student.getStu_id());
            if(temp.getBed_id() == -1)//不在里面代表没宿舍
                stuWithBedInfoList.add(new StuWithBedIno(student.getStu_id(), -1,-1,-1,-1));
            else {
                DormBedInfo dormBedInfo = dormBedInfoService.searchJdbcDormBed(temp.getBed_id());
                System.out.println(dormBedInfo.getBed_num());
                stuWithBedInfoList.add(new StuWithBedIno(student.getStu_id(), dormBedInfo.getBed_num(),
                        dormBedInfo.getDorm_id(), dormBedInfo.getLayer_id(), dormBedInfo.getRoom_id()));
                n++;
            }

        }
        model.addAttribute("stuWithBedInfoList", stuWithBedInfoList);
        model.addAttribute("numHasBed", n);
        return "bed-allocation-list";
    }

    //退宿
    @PostMapping(value = "/stuBedDel")
    public String stuBedDel(@RequestParam("stu_id") int stu_id, Model model) {
        System.out.println(stu_id + " delete");
        StudentBed studentBed = stuBedService.searchJdbcStuBed(stu_id);
        stuBedService.deleteJdbcStuBed(stu_id);
        int bed_id = studentBed.getBed_id();
        dormTotalService.updateDelJdbcDormTotal(dormBedInfoService.searchJdbcDormBed(bed_id).getDorm_id());
        dormBedInfoService.deleteJdbcDormBed(bed_id);
        return "redirect:/bedAllocationList";
    }

    @RequestMapping(value = "/stuBedEditPage")
    public String stuBedEditPage(){
        return "bed-allocation-edit";
    }

    //选择床位
    @PostMapping(value = "/stuBedEdit")
    public String stuBedEdit(@RequestParam("stu_id") int stu_id, @RequestParam("dorm_id") int dorm_id,
                             @RequestParam("layer_id") int layer_id, @RequestParam("room_id") int room_id,
                             @RequestParam("bed_num") int bed_num,
                             Model model, HttpSession session) {
        System.out.println(stu_id + " " + " update");
        //判断
        DormTotalInfo dormTotalInfo = dormTotalService.searchJdbcDormTotal(dorm_id);
        session.setAttribute("stu_id", stu_id);
        if(dormTotalInfo.getDormId() == -1 || layer_id > dormTotalInfo.getLayers() || room_id > dormTotalInfo.getRoomPerLayer()
            || bed_num > dormTotalInfo.getBedPerRoom()){
            model.addAttribute("resultMsg", "不存在此床位");
            model.addAttribute("result", "6");
            return "errorPage";
        }
        DormBedInfo dormBed = dormBedInfoService.searchJdbcDormBed(dorm_id, layer_id, room_id, bed_num);
        if(dormBed.getDorm_id() != -1){
            model.addAttribute("resultMsg", "此床位已被选");
            model.addAttribute("result", "6");
            return "errorPage";
        }

        model.addAttribute("resultMsg", "修改成功");
        model.addAttribute("result", "6");
        int bed_id = 1;
        while(true){
            StudentBed studentBed = stuBedService.searchJdbcStuBedByBedId(bed_id);
            if(studentBed.getBed_id() == -1) break;
            bed_id++;
        }
        StudentBed studentBed = stuBedService.searchJdbcStuBed(stu_id);
        if(studentBed.getStu_id() != -1) {//存在床位
            stuBedService.updateJdbcStuBed(stu_id, bed_id);
            dormBedInfoService.deleteJdbcDormBedTotal(studentBed.getBed_id());
        }
        else
            stuBedService.insertJdbcStuBed(stu_id, bed_id);
        dormBedInfoService.insertJdbcDormBed(bed_id, dorm_id, layer_id, room_id, bed_num);
        dormTotalService.updateAddJdbcDormTotal(dorm_id);
        return "errorPage";
    }
}
