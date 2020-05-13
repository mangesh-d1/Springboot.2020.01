package com.mangesh.location.util;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface ReportUtil {
	void generatePichart(String path,List<Object[]> data);

}
