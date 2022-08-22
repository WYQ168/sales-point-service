package com.sales.file.utils;

import cn.hutool.core.util.IdUtil;
import com.sales.common.security.utils.SecurityUtils;
import com.sales.file.domain.FileInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author 作者 owen 
 * @version 创建时间：2017年11月12日 上午22:57:51
 * 文件工具类
*/
@Slf4j
public class FileUtil {

	public static FileInfo getFileInfo(MultipartFile file) throws Exception {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileId(IdUtil.simpleUUID());
		fileInfo.setFileName(file.getOriginalFilename());
		fileInfo.setCreateBy(SecurityUtils.getUserId());
		fileInfo.setCreateTime(new Date());
		return fileInfo;
	}

	/**
	 * 文件的md5
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String fileMd5(InputStream inputStream) {
		try {
			return DigestUtils.md5Hex(inputStream);
		} catch (IOException e) {
			log.error("FileUtil->fileMd5:{}" ,e.getMessage());
		}

		return null;
	}

	public static String saveFile(MultipartFile file, String path) {
		try {
			File targetFile = new File(path);
			if (targetFile.exists()) {
				return path;
			}

			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			file.transferTo(targetFile);

			return path;
		} catch (Exception e) {
			log.error("FileUtil->saveFile:{}" ,e.getMessage());
		}

		return null;
	}

	public static boolean deleteFile(String pathname) {
		File file = new File(pathname);
		if (file.exists()) {
			boolean flag = file.delete();

			if (flag) {
				File[] files = file.getParentFile().listFiles();
				if (files == null || files.length == 0) {
					file.getParentFile().delete();
				}
			}

			return flag;
		}

		return false;
	}



	/**
	 * java如果目录不存在则创建目录
	 * @param destDirName
	 * @return
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		//创建目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	/**
	 * 判断文件是否存在，不存在就创建
	 * @param file
	 */
	public static void createFile(File file) {
		if (file.exists()) {
			System.out.println("File exists");
		} else {
			System.out.println("File not exists, create it ...");
			//getParentFile() 获取上级目录(包含文件名时无法直接创建目录的)
			if (!file.getParentFile().exists()) {
				System.out.println("not exists");
				//创建上级目录
				file.getParentFile().mkdirs();
			}
			try {
				//在上级目录里创建文件
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		String dirName = "I:/work/";
		createDir(dirName);
	}

}
