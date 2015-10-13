package com.newleader.nlsite.common;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRCoder {

	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;
	public BufferedImage genBinImg(String url, int hight, int wide) throws WriterException {
		BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, hight, wide);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 给二维码图片添加Logo
	 * 
	 * @param qrPic
	 * @param logoPic
	 */
	public void addLogo_QRCode(File qrPic, File logoPic, LogoConfig logoConfig, String picDir) {
		try {
			if (!qrPic.isFile() || !logoPic.isFile()) {
				System.out.print("file not find !");
				return;
			}
			
			/**
			 * 读取二维码图片，并构建绘图对象
			 */
			BufferedImage image = ImageIO.read(qrPic);
			Graphics2D g = image.createGraphics();

			/**
			 * 读取Logo图片
			 */
			BufferedImage logo = ImageIO.read(logoPic);
			int widthLogo = logo.getWidth(), heightLogo = logo.getHeight();

			// 计算图片放置位置
			int x = (image.getWidth() - widthLogo*3) / 2;
			int y = (image.getHeight() - heightLogo*3) / 2;

			// 开始绘制图片
			g.drawImage(logo, x, y, widthLogo*3, heightLogo*3, null);
			//g.drawRoundRect(x, y, widthLogo, heightLogo, 150, 150);
			g.setStroke(new BasicStroke(logoConfig.getBorder()));
			g.setColor(logoConfig.getBorderColor());
			//g.drawRect(x, y, widthLogo, heightLogo);

			g.dispose();

//			ImageIO.write(image, "png", new File("c:/z-nl/pure.png"));
			ImageIO.write(image, "png", new File(picDir));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws WriterException,
			FileNotFoundException, IOException {

		QRCoder qrCoder = new QRCoder();

//		BufferedImage img = qrCoder.genBinImg("http://weixin.qq.com/q/dEx3DU-lfDQFROlDnmTZ");
//		File pureCode = new File("c:/z-nl/pure.png");
//		ImageIO.write(img, "png", pureCode);
//		qrCoder.addLogo_QRCode(pureCode, new File("c:/z-nl/logo.png"), new LogoConfig(), "c:/z-nl/pure.png");
		
		createQrCode("http://weixin.qq.com/q/dEx3DU-lfDQFROlDnmTZ", "c:/z-nl/logo.png", "c:/z-nl/code.png");
	}
	
	/**
	 * 生成二维码
	 * @param codeUrl  二维码内容
	 * @param logoPic  logo图片地址
	 * @param codePicDir 二维码存放地址
	 */
	public static void createQrCode(String codeContent, String logoPic, String codePicDir) {
		try {
			QRCoder qrCoder = new QRCoder();
			BufferedImage  img = qrCoder.genBinImg(codeContent, 2000, 2000);
			File pureCode = new File(codePicDir);
			ImageIO.write(img, "png", pureCode);
			qrCoder.addLogo_QRCode(pureCode, new File(logoPic), new LogoConfig(), codePicDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class LogoConfig {
	// logo默认边框颜色
	public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
	// logo默认边框宽度
	public static final int DEFAULT_BORDER = 2;
	// logo大小默认为照片的1/5
	public static final int DEFAULT_LOGOPART = 5;

	private final int border = DEFAULT_BORDER;
	private final Color borderColor;
	private final int logoPart;

	/**
	 * Creates a default config with on color {@link #BLACK} and off color
	 * {@link #WHITE}, generating normal black-on-white barcodes.
	 */
	public LogoConfig() {
		this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
	}

	public LogoConfig(Color borderColor, int logoPart) {
		this.borderColor = borderColor;
		this.logoPart = logoPart;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public int getBorder() {
		return border;
	}

	public int getLogoPart() {
		return logoPart;
	}
}
