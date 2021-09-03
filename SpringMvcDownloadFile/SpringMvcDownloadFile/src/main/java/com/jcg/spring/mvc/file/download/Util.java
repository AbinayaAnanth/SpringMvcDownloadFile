package com.jcg.spring.mvc.file.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	
	public static String getFilePath(HttpServletRequest req) throws FileNotFoundException {
		String appPath = "", fullPath = "", downloadPath = "downloads";

		
		appPath = req.getSession().getServletContext().getRealPath("");	
		fullPath = appPath + File.separator + downloadPath;
		logger.info("Destination Location For The File Is?= " + fullPath);
		return fullPath;
	}

	
	public static int getColumnCount(ResultSet res) throws SQLException {
		int totalColumns = res.getMetaData().getColumnCount();		
		return totalColumns;
	}

	
	public static void downloadFileProperties(HttpServletRequest req,HttpServletResponse resp, String toBeDownloadedFile, File downloadFile) {
		try {

			
			String mimeType = req.getSession().getServletContext().getMimeType(toBeDownloadedFile);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			
			resp.setContentType(mimeType);
			resp.setContentLength((int) downloadFile.length());

			
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			resp.setHeader(headerKey, headerValue);

			
			OutputStream outStream = resp.getOutputStream();
			FileInputStream inputStream = new FileInputStream(downloadFile);
			byte[] buffer = new byte[IConstants.BUFFER_SIZE];
			int bytesRead = -1;

			
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();
		} catch(IOException ioExObj) {
			logger.error("Exception While Performing The I/O Operation?= " + ioExObj);
		}
	}
}