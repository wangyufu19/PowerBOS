package com.framework.common.file;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Random;

import com.framework.common.servlet.http.RequestHash;
import com.framework.common.util.SysConstants;
import com.powerbosframework.util.StringUtil;
/**
 * 页面验证码类
 * @author wangyf
 * @version 1.0
 */
public class RandomImage {
	RequestHash reh;

	public RandomImage(RequestHash reh) {
		this.reh = reh;
	}

	public void checkImage() {
//		设置页面不缓存		
		reh.getResponse().setHeader("Pragma", "No-cache");
		reh.getResponse().setHeader("Cache-Control", "no-cache");
		reh.getResponse().setDateHeader("Expires", 0L);
//		在内存中创建图象
		int width = 60;
		int height = 20;
		BufferedImage image = new BufferedImage(width, height, 1);
//	     获取图形上下文
		Graphics g = image.getGraphics();
//		生成随机类
		Random random = new Random();
//		设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
//		设定字体
		g.setFont(new Font("Times New Roman", 0, 18));
//		画边框
//		g.setColor(new Color());
//		g.drawRect(0,0,width-1,height-1);

//		随机产生155条干扰线，使图象中的验证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
//		取随机产生的验证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;
//			将验证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
//			调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
//		将验证码存入Session
		reh.getSession().setAttribute(SysConstants.img_key, sRand);
//		图象生效
		g.dispose();
//	    输出图象到页面
		try {

			if(SysConstants.runtime_server.equals("weblogic")){
				String fileName=StringUtil.getTimeNum();
				File file=new File(SysConstants.public_path+File.separator+"SYSTEM"+File.separator+"checkimg"+File.separator+fileName);
				ImageIO.write(image, "JPEG", file);				
				reh.getResponse().sendRedirect(reh.getReqeust().getContextPath()+"/SYSTEM/checkimg/"+fileName);
			}else if(SysConstants.runtime_server.equals("tomcat")){
				ImageIO.write(image, "JPEG", reh.getResponse()
						.getOutputStream());
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);

	}

}
