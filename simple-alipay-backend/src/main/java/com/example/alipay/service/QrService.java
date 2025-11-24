package com.example.alipay.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QrService {

    /**
     * 解析 MultipartFile 图片中的二维码
     */
    public String decodeQrFromImage(MultipartFile file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                throw new RuntimeException("无法读取图片文件");
            }

            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();

        } catch (NotFoundException e) {
            throw new RuntimeException("未检测到二维码");
        } catch (IOException e) {
            throw new RuntimeException("图片读取失败");
        }
    }

    /**
     * 解析摄像头的 BufferedImage
     */
    public String decodeBufferedImage(BufferedImage image) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();

        } catch (NotFoundException e) {
            throw new RuntimeException("二维码识别失败");
        }
    }

    /**
     * 生成二维码 Base64（用于收款码、出行码、付款码等）
     */
    public String generateQrBase64(String content, int width, int height) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(content, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);

            String base64 = Base64.getEncoder().encodeToString(out.toByteArray());

            return "data:image/png;base64," + base64;

        } catch (WriterException | IOException e) {
            throw new RuntimeException("二维码生成失败", e);
        }
    }
    /**
     * 从字节数组解析二维码（用于前端上传 Base64/字节流 场景）
     */
    public String parseQrFromBytes(byte[] bytes) {
        try {
            BufferedImage image = ImageIO.read(new java.io.ByteArrayInputStream(bytes));
            if (image == null) {
                throw new RuntimeException("二维码图片格式不正确或无法读取");
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();

        } catch (NotFoundException e) {
            throw new RuntimeException("未识别到二维码内容");
        } catch (IOException e) {
            throw new RuntimeException("二维码字节流解析失败");
        }
    }

}
