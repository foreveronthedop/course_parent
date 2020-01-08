package com.llm.demo.util;




import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class FileDownOrUpload {

	public static String upload(MultipartFile  file,HttpServletRequest request) throws IOException {
				//鍒ゆ柇涓婁紶鏂囦欢鏄惁涓虹┖
				if(file.getSize()>0) {
					//鑾峰彇鏈嶅姟鍣ㄧ殑缁濆璺緞
					String path = request.getSession().getServletContext().getRealPath("");
					//閫氳繃UUID浜х敓涓�涓枃浠跺悕绉帮紙鏂板悕绉帮級
					String newFileName=UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					//鏍规嵁鏈嶅姟鍣ㄧ殑璺粡鍜屾柊鍚嶇О鍒涘缓file瀵硅薄
					File file2 = new File(path+"/addr/upload/"+newFileName);
					//鍒ゆ柇鏂版枃浠跺璞℃槸鍚﹀瓨鍦�
					if(!file2.exists()) {
						//鍒涘缓鏂版枃浠跺璞＄殑鐖剁骇鐩綍锛坲pload鏂囦欢澶癸級
						file2.getParentFile().mkdirs();
						//鍒涘缓鏂版枃浠跺璞�
						file2.createNewFile();
					}
					try {
						//灏嗕笂浼犵殑鏂囦欢瀵硅薄璧嬪�煎埌鏂版枃浠跺璞′腑
						file.transferTo(file2);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					return "/addr/upload/"+newFileName;
				}else {
					return "";
				}
				
	}

	//@ResponseBody
	//@RequestMapping("/upload")
	//public void upload(@RequestParam(name = "filedata") MultipartFile file, HttpServletRequest request) throws Exception {
	//	String uploadFile = FileDownOrUpload.upload(file, request);
	//	request.getSession().setAttribute("url", uploadFile);
	//}
	//@ResponseBody
	//@RequestMapping("/updatePhoto")
//	public boolean updatePhoto(Integer id, HttpServletRequest request) throws Exception {
	//	String url = (String) request.getSession().getAttribute("url");
	//	return inservice.updatePhoto(id, url);
	//}

	public static String download(String filepath, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;

		try {
			// 濡傛灉鏄粠鏈嶅姟鍣ㄤ笂鍙栧氨鐢ㄨ繖涓幏寰楃郴缁熺殑缁濆璺緞鏂规硶銆�
			// String filepath = request.getRealPath(filepatha);//鏂规硶杩囨椂浜�
			String filepathall = request.getSession().getServletContext().getRealPath(filepath);

			File uploadFile = new File(filepathall);

			// 鍥剧墖瀵硅薄娴�
			fis = new FileInputStream(uploadFile);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);

			// 寰楀埌鏂囦欢鍚�
			String filename = filepath.substring(filepath.lastIndexOf("\\") + 1);

			// 杩欎釜灏卞氨鏄脊鍑轰笅杞藉璇濇鐨勫叧閿唬鐮�
			response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));

			int bytesRead = 0;
			// 鐢ㄨ緭鍑烘祦鍘诲啓锛岀紦鍐茶緭鍏ヨ緭鍑烘祦
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.flush();
				}
				if (fis != null) {
					fis.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}