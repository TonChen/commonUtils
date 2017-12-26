package com.genaral.captcha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码 CreateBy rsh on 2016-09-22 Servlet implementation class CaptchaImgServlet
 */
public class CaptchaImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String charsLong  = "0123456789";
    private static String charsShort = "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKMNPQRSTUVWXYZ";
    private static String chars      = charsShort;
	
    private final static Logger logger = LoggerFactory.getLogger(CaptchaImgServlet.class);
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CaptchaImgServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			logger.info("获取验证码....");
			int charsLength = chars.length();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			int width = 110, height = 35;
			String widthStr = request.getParameter("width");
			String heightStr = request.getParameter("height");
			try {
				if(widthStr!=null)
					width = Integer.valueOf(widthStr);
				if(heightStr!=null)
					height = Integer.valueOf(heightStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			Random random = new Random();
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("宋体", Font.BOLD, height));
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 10; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
			StringBuilder sRand = new StringBuilder();
			String[] fontNames = { "Times New Roman", "Arial", "Book antiqua", "宋体" };
			for (int i = 0; i < 4; i++) {
				g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height - 5));
				char rand = chars.charAt(random.nextInt(charsLength));
				sRand.append(rand);
				g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
				//绘制验证码
				int x = 22 * i + random.nextInt(6) + 15;
				int y = height - random.nextInt(5) - 5;
				g.drawString(String.valueOf(rand), x, y);
			}
			g.setColor(getRandColor(160, 200));
			// 绘制干扰线
			for (int i = 0; i < 30; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(width);
				int yl = random.nextInt(width);
				g.drawLine(x, y, x + xl, y + yl);
			}
			// 把生成的验证码存放在session作用域中
			HttpSession seesion = request.getSession();
			seesion.setAttribute("Captcha_Img_Code", sRand.toString());
			logger.info("验证码========="+sRand.toString());
			
			g.dispose();
			try {
				Thread.sleep(100);
			} catch (Exception ex) {
			}
			OutputStream os = response.getOutputStream();
			ImageIO.write(image, "JPEG", os);
			os.flush();
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 获取颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
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
