package com.framework.controller;

import com.framework.utils.R;
import com.framework.utils.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.ketansoft.dms.entity.MonthEntity;
import com.ketansoft.dms.service.MonthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ketansoft.dms.utils.addMonth;
/**
 * 登录相关
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
public class SysLoginController {
	@Autowired
	private Producer producer;

	@Autowired
	private  MonthService monthService;

	static Date date = new Date();

	static SimpleDateFormat formatter= new SimpleDateFormat("yyyy");

	static int nowYear =Integer.parseInt(formatter.format(date));


	/**
	 * 处理验证码
	 */
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

//		// 生成文字验证码
		String text = producer.createText();
//		String text = "666666";
//		 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public R login(String username, String password, String captcha) throws IOException {

		//获取在session中的验证码
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if (!captcha.equalsIgnoreCase(kaptcha)) {
			return R.error("验证码不正确");
		}

		try {
			Subject subject = ShiroUtils.getSubject();
			// 使用sha256加密密码
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//			token.setRememberMe(rememberMe);
			subject.login(token);
		} catch (UnknownAccountException e){
			return R.error(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return R.error(e.getMessage());
		} catch (LockedAccountException e) {
			return R.error(e.getMessage());
		} catch (AuthenticationException e) {
			return R.error("账户验证失败");
		}
		//判断是否自动新增月份
		Map<String,Object>map=new HashMap<>();
		map.put("yName",nowYear);
		List<MonthEntity>monthEntityList =monthService.queryByYear(map);
		System.out.println(monthEntityList.size());
		if(monthEntityList==null){
			addMonthByAtuo(monthEntityList,nowYear);
		}else if(monthEntityList.size()==0){
			addMonthByAtuo(monthEntityList,nowYear);
		}
		return R.ok();
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtils.logout();
		return "redirect:login.html";
	}

	public String addMonthByAtuo(List list, int year) {
		Map<String,Object> map = new HashMap<>(2);
		map.put("yName",year);
		System.out.println(year);
		if(list!=null){
			if(list.size()==0){
				addMonthBegin(year);
			}else{
				return "ERROR";
			}
		}else{
			addMonthBegin(year);
		}

		System.out.println("+++++++++------------------新增完成！");
		return "OK";
	}

	public int getMaxDay(int year,int month){
		Calendar calendar =Calendar.getInstance();
		calendar.set(Calendar.YEAR,year-1);
		calendar.set(Calendar.MONTH,month);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	public  void addMonthBegin(int nowYaer){
		for(int i = 1 ;i <= 12 ; i++){
			MonthEntity monthEntity =new MonthEntity();
			int maxDay = getMaxDay(nowYaer,i);
			String mBgtime = nowYaer+"-"+i+"-01"+" "+"00:00:00";
			String mEndtime = nowYaer+"-"+i+"-"+maxDay+" "+"00:00:00";
			monthEntity.setMBgtime(mBgtime);
			monthEntity.setMEndtime(mEndtime);
			monthEntity.setMName(i);
			monthEntity.setYName(nowYaer);
			monthService.save(monthEntity);
		}
	}
}
