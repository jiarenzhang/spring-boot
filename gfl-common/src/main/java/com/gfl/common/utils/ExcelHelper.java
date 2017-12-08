package com.gfl.common.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

	private static Logger LOGGER = LoggerFactory.getLogger(ExcelHelper.class);

	private static final int DEFAULT_COLUMN_WIDTH = 18;

	public HSSFWorkbook workbook;

	private HSSFSheet sheet;

	private String sheetName;

	private String[] titleNames;

	public ExcelHelper(String sheetName, String[] titleNames) {
		this.sheetName = sheetName;
		this.titleNames = titleNames;
		init();
	}

	private void init() {
		if (titleNames == null || titleNames.length == 0) {
			LOGGER.error("Excel titleNames is null");
			throw new RuntimeException("Excel titleNames is null");
		}
		if (workbook == null) {
			workbook = new HSSFWorkbook();
		}
		sheet = workbook.createSheet(sheetName);
		sheet.setDefaultColumnWidth(ExcelHelper.DEFAULT_COLUMN_WIDTH);
		createTitle(sheet);

	}

	private void createTitle(HSSFSheet sheet) {
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCellStyle titleStyle = getTitleStyle();
		for (int i = 0; i < titleNames.length; i++) {
			HSSFCell titleCell = titleRow.createCell(i);
			titleCell.setCellValue(titleNames[i]);
			titleCell.setCellStyle(titleStyle);
		}
	}

	private HSSFCellStyle getTitleStyle() {
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setBold(true);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		// 设置背景色
		// style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		// style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		return style;
	}

	public void getHSSFWorkbook(Integer start, JsonArray data) {
		for (int i = 0; i < data.size(); i++) {
			JsonObject dataObj = (JsonObject) data.get(i);
			HSSFRow dataRow = sheet.createRow(start + i);
			int colIndex = 0;
			for (String key : dataObj.keySet()) {
				HSSFCell dataCell = dataRow.createCell(colIndex);
				JsonElement element = dataObj.get(key);
				if (element != null && !element.isJsonNull()) {
					dataCell.setCellValue(element.getAsString());
				} else {
					dataCell.setCellValue("");
				}
				colIndex++;
			}
		}
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}
}
