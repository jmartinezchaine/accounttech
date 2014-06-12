package com.soup.accounttech.core.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.soup.accounttech.repository.model.business.Movement;
import com.soup.accounttech.repository.model.metadata.CellMetadata;
import com.soup.accounttech.repository.model.metadata.ParserMetadata;

public class ExcelParser {

	public List<Movement> parseXlsFile(String path, ParserMetadata metadata) {
		List<Movement> movements = new LinkedList<Movement>();

		try {

			FileInputStream file = new FileInputStream(new File(path));

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			movements = parseFile(sheet, metadata);

			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ParseException e) {
			e.printStackTrace();
		}
		return movements;

	}

	public List<Movement> parseXlsxFile(String path, ParserMetadata metadata) throws ParseException {
		List<Movement> movements = new LinkedList<Movement>();

		try {

			FileInputStream file = new FileInputStream(new File(path));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			movements = parseFile(sheet, metadata);

			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movements;

	}

	private List<Movement> parseFile(Sheet sheet, ParserMetadata metadata)
			throws ParseException {
		List<Movement> movements = new LinkedList<Movement>();

		// Iterate through each rows from first sheet
		int count = sheet.getLastRowNum();

		for (int rowNumber = metadata.getFirstRow(); rowNumber <= count; rowNumber++) {

			Row row = sheet.getRow(rowNumber);

			// DATE
			Cell dateCell = row.getCell(metadata.getDateColumn().getColumn());
			Date date;
			if (dateCell.getCellType() == Cell.CELL_TYPE_STRING) {
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
						"dd/MM/yyyy");
				date = formatoDelTexto.parse(dateCell.getStringCellValue());
			} else {
				date = dateCell.getDateCellValue();
			}

			// DESCRIPTION
			String description = null;
			for (CellMetadata cellMetadata : metadata.getDescriptionColumns()) {
				Cell descriptionCell = row.getCell(cellMetadata.getColumn());
				if (descriptionCell != null
						&& descriptionCell.getCellType() != Cell.CELL_TYPE_BLANK) {
					if (StringUtils.isEmpty(description)) {
						description = descriptionCell.getStringCellValue();
					} else {
						description = description + " | "
								+ descriptionCell.getStringCellValue();
					}
				}
			}

			// DEBIT
			BigDecimal debitAmount = null;
			Cell debitCell = row.getCell(metadata.getDebitAmountColumn()
					.getColumn());
			if (debitCell != null
					&& debitCell.getCellType() != Cell.CELL_TYPE_BLANK) {
				double value = debitCell.getNumericCellValue();
				debitAmount = BigDecimal.valueOf(value);
			}
			
			// CREDIT
			BigDecimal creditAmount = null;
			Cell creditCell = row.getCell(metadata.getCreditAmountColumn()
					.getColumn());
			if (creditCell != null
					&& creditCell.getCellType() != Cell.CELL_TYPE_BLANK) {
				double value = creditCell.getNumericCellValue();
				creditAmount = BigDecimal.valueOf(value);
			}
			
			Movement movement = new Movement();
			movement.setCreditAmount(creditAmount);
			movement.setDebitAmount(debitAmount);
			movement.setDate(date);
			movement.setDescription(description);
			
			movements.add(movement);
		}

		return movements;

	}

}
