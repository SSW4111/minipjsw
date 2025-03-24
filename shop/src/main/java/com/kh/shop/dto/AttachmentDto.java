package com.kh.shop.dto;

import java.text.DecimalFormat;
import java.text.Format;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AttachmentDto {
	private int attachmentNo;
	private String attachmentName;
	private String attachmentType;
	private long attachmentSize;
	
	public String getAttachmentSizeString() {
		Format fmt = new DecimalFormat("#,##0.00");
		if(attachmentSize >= 1024L * 1024L * 1024L) {//GB단위로 표현해야 한다면...
			return fmt.format((double)attachmentSize / (1024L * 1024L * 1024L)) + " GB";
		}
		else if(attachmentSize >= 1024L * 1024L) {//MB단위로 표현해야 한다면...
			return fmt.format((double)attachmentSize / (1024L * 1024L)) + " MB";
		}
		else if(attachmentSize >= 1024L) {//KB단위로 표현해야 한다면...
			return fmt.format((double)attachmentSize / 1024L) + " KB";
		}
		else {
			return fmt.format(attachmentSize) + " Byte";
		}
	}
}
