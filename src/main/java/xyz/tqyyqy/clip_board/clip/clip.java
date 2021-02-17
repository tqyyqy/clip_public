package xyz.tqyyqy.clip_board.clip;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.tqyyqy.clip_board.entity.TextHistory;
import xyz.tqyyqy.clip_board.mapper.TextHistoryMapper;
import xyz.tqyyqy.clip_board.utils.IpUtil;
import xyz.tqyyqy.clip_board.utils.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class clip {
    @Autowired
    TextHistoryMapper textHistoryMapper;

//
//    /**
//     * 登录跳转
//     *
//     * @param model
//     * @return
//     */
//    @GetMapping("/login")
//    public String loginGet(Model model) {
//        return "login";
//    }
//    @GetMapping("/")
//    public String webHome(Model model) {
//        return "redirect:/login";
//    }

//    /**
//     * 退出登陆
//     *
//     * @param model
//     * @return
//     */
//    @GetMapping("/loginout")
//    public String loginoutGet(Model model, HttpSession httpSession) {
//        httpSession.removeAttribute("admin");
//        return "redirect:/login";
//
//    }
//    /**
//     * 登录
//     *
//     * @param backUser
//     * @param model
//     * @param httpSession
//     * @return
//     */
//    @PostMapping("/login")
//    public String loginPost(HttpServletRequest request, BackUser backUser, Model model, HttpSession httpSession) {
//        //获取IP地址
//        System.out.println("开始获取ip地址");
//        String ipAddress = IpUtil.getIpAddr(request);
//        System.out.println(TimeUtil.stampToDatewx(System.currentTimeMillis()+"")+"后台尝试登陆的IP:" + ipAddress);
//
//
//        BackUser userRes = backUserMapper.findByNameAndPassword(backUser);
//        if (userRes != null) {
//            System.out.println("登陆成功");
//            httpSession.setAttribute("admin", userRes);
//            return "redirect:/backpage/mainpage";
//        } else {
//            model.addAttribute("error", "用户名或密码错误，请重新登录！");
//            return "login";
//        }
//    }
    /**
     * 首页
     *
     * @param model
     * @return
     */
    @ResponseBody
    @GetMapping("clip_api")
    public String clip_api(Model model){
        List<TextHistory> TextHistoryList = textHistoryMapper.selectList(new QueryWrapper<TextHistory>().orderByDesc().eq("1",1));
        String str = "null";
        for (TextHistory t:TextHistoryList) {
            System.out.println(t);
            str = t.getText();
        }

        return str;
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String clip_home(Model model){
        return "redirect:/clip";
    }
    @GetMapping("clip")
    public String clip(Model model){
        List<TextHistory> TextHistoryList = textHistoryMapper.selectList(new QueryWrapper<TextHistory>().orderByDesc().eq("1",1));
        String str = "null";
        for (TextHistory t:TextHistoryList) {
            System.out.println(t);
            str = t.getText();
        }
        int rd = (int) (Math.random() * 4+1);
        String bg = "bg"+rd+".jpg";
//        System.out.println(textHistoryMapper.getMaxId());
        model.addAttribute("text",str);
        model.addAttribute("bg",bg);
        return "clip";
    }

    /**
     * 首页-手机
     *
     * @param model
     * @return
     */
    @GetMapping("clip_phone")
    public String clip_phone(Model model){
        List<TextHistory> TextHistoryList = textHistoryMapper.selectList(new QueryWrapper<TextHistory>().orderByDesc().eq("1",1));
        String str = "null";
        for (TextHistory t:TextHistoryList) {
            System.out.println(t);
            str = t.getText();
        }
        int rd = (int) (Math.random() * 4+5);
        String bg = "bg"+rd+".jpg";
//        System.out.println(textHistoryMapper.getMaxId());
        model.addAttribute("text",str);
        model.addAttribute("bg",bg);
        return "clip";
    }
    /**
     * 首页
     *
     * @return
     */
    @PostMapping("clip")
    public String submitClip(HttpServletRequest request,TextHistory textHistory){
        textHistory.setPutting(TimeUtil.stampToDate(System.currentTimeMillis()+""));
        String ipAddress = IpUtil.getIpAddr(request);
        textHistory.setIp(ipAddress);
        textHistoryMapper.insert(textHistory);
        return "redirect:/clip";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {
        return "clipIndex";
    }
}
